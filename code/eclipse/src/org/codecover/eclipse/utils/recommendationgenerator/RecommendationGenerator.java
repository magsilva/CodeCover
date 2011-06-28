package org.codecover.eclipse.utils.recommendationgenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codecover.eclipse.utils.EclipseMASTLinkage;
import org.codecover.eclipse.utils.EclipseMASTLinkage.MAST;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.CCPriorityDataCollector;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.CodeBlockTypeDataCollector;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.CodeFileLineDataCollector;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.FileAgeDataCollector;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.FindBugsDataCollector;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.LengthOfPredicateDataCollector;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.UncBranchLineCountDataCollector;
import org.codecover.eclipse.utils.recommendationgenerator.datacollectors.PassingTestCaseCountDataCollector;
import org.codecover.eclipse.views.PackageFilterMode;
import org.codecover.model.TestCase;
import org.codecover.model.TestSessionContainer;
import org.codecover.model.exceptions.FileLoadException;
import org.codecover.model.mast.HierarchyLevel;
import org.codecover.model.mast.Location;
import org.codecover.model.mast.LocationList;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.PackageFragment;

public class RecommendationGenerator {

	private TestSessionContainer testSessionContainer;


	private List<ErrorDataSource> errorDataSources = new ArrayList<ErrorDataSource>();
	private ErrorDataSource codeErrorDataSource;
	private ErrorDataSource versionErrorDataSource;
	private ErrorDataSource ccErrorDataSource;
	private ErrorDataSource expertErrorDataSource;
	private ErrorDataSource qualityErrorDataSource;
	private ErrorDataSource processDataSource;

	private BaseRecommendationListCreator baseRecommendationListCreator;

	private ArrayList<UncoveredBranch> sortedList;

	private HashMap<HierarchyLevel, IResource> hLevToFileCache = new HashMap<HierarchyLevel, IResource>();
	private HashMap<HierarchyLevel, IJavaElement> hLevToPackageCache = new HashMap<HierarchyLevel, IJavaElement>();
	
	private Comparator<UncoveredBranch> weightSorter = new Comparator<UncoveredBranch>() {
		public int compare(UncoveredBranch o1, UncoveredBranch o2) {
			return o2.score.compareTo(o1.score);
		}
	};

	private HashSet<String> filterPackages = new HashSet<String>();

	private PackageFilterMode packageFilterMode;
	private List<BranchType> filterBranches = Arrays.asList(BranchType.values());
	private List<String> excludeClassList = new ArrayList<String>();

	public HashMap<HierarchyLevel, ICompilationUnit> hLevToCompUnitCache = new HashMap<HierarchyLevel, ICompilationUnit>();


	private HashSet<IResource> relevantFiles;
	
	private static HashMap<IResource, List<TestCase>> fileToTestCaseMap = new HashMap<IResource, List<TestCase>>();
	
	public RecommendationGenerator(TestSessionContainer testSessionContainer, File testCaseListFile) {
		super();
		this.testSessionContainer = testSessionContainer;

		initErrorDataSources();

		this.baseRecommendationListCreator = new BaseRecommendationListCreator(testSessionContainer);
		
	}

	private void initErrorDataSources() {
		errorDataSources = new ArrayList<ErrorDataSource>();

		initCodeDataSource();

		initVersionDataSource();

		initCCDataSource();

		initExpertErrorDataSource();

		initQAErrorDataSource();

		initProcessDataSource();

	}

	private void initProcessDataSource() {
		this.processDataSource = new ErrorDataSource("Prozess");
		errorDataSources.add(this.processDataSource);
	}

	private void initQAErrorDataSource() {
		this.qualityErrorDataSource = new ErrorDataSource("QS-Maßnahmen");
		errorDataSources.add(this.qualityErrorDataSource);
	}

	private void initExpertErrorDataSource() {
		this.expertErrorDataSource = new ErrorDataSource("Expertenwissen");
		errorDataSources.add(this.expertErrorDataSource);
		
		ErrorIndicator indi = new ErrorIndicator("Anfälligste Module", "20% des Codes mit der höchsten ");
		indi.addParameter(new Parameter("file", "Datei, die die Informationen enthält.", new File("."), false, true));
		this.expertErrorDataSource.addErrorIndicator(indi);
		
		indi = new ErrorIndicator("Architekturprobleme", "Klassen mit Architekturproblemen.");
		indi.addParameter(new Parameter("file", "Datei, die die Informationen enthält.", new File("."), false, true));
		this.expertErrorDataSource.addErrorIndicator(indi);
		
	}

	private void initCCDataSource() {
		this.ccErrorDataSource = new ErrorDataSource("CC-Test", "Fehlerindikatoren, die sich direkt aus der " +
				"Überdeckungsmessung von CodeCover ergeben.");
		errorDataSources.add(this.ccErrorDataSource);
		
		ErrorIndicator priorityErrorIndicator = new ErrorIndicator("Priorität des Testfalls", "Die Priorität des " +
				"zugrundeliegenden Testfalls gibt Auskunft über die Wichtigkeit eines unüberdeckten Blocks.", new CCPriorityDataCollector());
		priorityErrorIndicator.addParameter(new Parameter("priorityFile", "Datei, die Prioritätsinformationen zu den Testfällen enthält.", new File("."), true, false));
		this.ccErrorDataSource.addErrorIndicator(priorityErrorIndicator);
		
		ErrorIndicator blockTypeErrorIndicator = new ErrorIndicator("Typ des unüberdeckten Codeblocks", "Die Art des " +
				"Codeblocks (if, while, catch, ...) ist ein Indiz für die Wichtigkeit des enthaltenen Codes.", new CodeBlockTypeDataCollector(), true);
		blockTypeErrorIndicator.addParameter(new Parameter("Faktor", "Faktor der Bewertung dieses Indikators", new Integer(3), new Integer(2), true, true));
		this.ccErrorDataSource.addErrorIndicator(blockTypeErrorIndicator);
		
		ErrorIndicator lineCountErrorIndicator = new ErrorIndicator("Anzahl der unüberdeckten Zeilen", "Je mehr Zeilen überdeckt werden, " +
				"desto höher die Wahrscheinlichkeit, einen Fehler zu finden", new UncBranchLineCountDataCollector(), true);
		this.ccErrorDataSource.addErrorIndicator(lineCountErrorIndicator);
		
		ErrorIndicator passingTestCaseCountErrorIndicator = new ErrorIndicator("Anzahl der tangierenden Testfälle", "Je mehr Testfälle hier " +
				"vorbeikommen, desto unspezifischer ist der Code. Kommen nur wenige vorbei, ist der Codeblock interessander.", new PassingTestCaseCountDataCollector(), true);
		this.ccErrorDataSource.addErrorIndicator(passingTestCaseCountErrorIndicator);
		
		ErrorIndicator lengthOfPredicateIndicator = new ErrorIndicator("Länge des Prädikats", "Ist eine Bedingung sehr lang und " +
				"kompliziert, enthält sie Möglicherweise Fehler", new LengthOfPredicateDataCollector(), true);
		this.ccErrorDataSource.addErrorIndicator(lengthOfPredicateIndicator);
		
	}

	private void initVersionDataSource() {
		ErrorIndicator errorIndicator;
		this.versionErrorDataSource = new ErrorDataSource("Versionsgeschichte",
				"Fehlerindikatoren, die sich aus der Versionsgeschichte der Dateien ableiten lassen");
		errorDataSources.add(this.versionErrorDataSource);

		errorIndicator = new ErrorIndicator("Alter der Datei",
				"Wie lange ist die Datei schon im System? Je älter, desto weniger fehleranfällig dürfte sie sein.", new FileAgeDataCollector());
		errorIndicator.addParameter(new Parameter("Pfad der Quelldatei",
				"Pfad zur Datei, die die Informationen zum Dateialter enthält", new File("."), false, true));
		this.versionErrorDataSource.addErrorIndicator(errorIndicator);

		errorIndicator = new ErrorIndicator("Änderungen seit letztem Release",
				"Wurden viele Änderungen seit dem letzten Release gemacht, spricht dies für Fehler in der Datei.");
		errorIndicator.addParameter(new Parameter("Pfad der Quelldatei",
				"Pfad zur Datei, die die Informationen zu den Änderungen seit dem letzten Release enthält", new File("."),
				false, true));
		errorIndicator.addParameter(new Parameter("Baseline-Revision", "Revision des letzten Releases", new Integer(3),
				new Integer(1), true, false));
		this.versionErrorDataSource.addErrorIndicator(errorIndicator);

		errorIndicator = new ErrorIndicator("Gefundene Fehler im letzten Release",
				"Wurden viele Änderungen seit dem letzten Release gemacht, spricht dies für Fehler in der Datei.");
		errorIndicator.addParameter(new Parameter("Pfad der Quelldatei", "Pfad zur Datei, die die Informationen zu den Fehlern "
				+ "im letzten Release enthält", new File("."), false, true));
		this.versionErrorDataSource.addErrorIndicator(errorIndicator);
	}

	private void initCodeDataSource() {
		this.codeErrorDataSource = new ErrorDataSource("Code", "Fehlerindikatoren, die sich aus dem Quellcode ablesen lassen.");
		errorDataSources.add(this.codeErrorDataSource);

		ErrorIndicator errorIndicator = new ErrorIndicator("LOC der Datei", "Anzahl der Codezeilen in einer Datei", new CodeFileLineDataCollector(), true); //$NON-NLS-1$ //$NON-NLS-2$
		errorIndicator.addParameter(new Parameter("Grenze", "Max. Zeilenanzahl", new Integer(3), new Integer(1000), true, false));
		this.codeErrorDataSource.addErrorIndicator(errorIndicator);

		errorIndicator = new ErrorIndicator(
				"FindBugs", "Ergebnis der statischen Analyse von FindBugs", new FindBugsDataCollector(), true); //$NON-NLS-1$ //$NON-NLS-2$
		errorIndicator.addParameter(new Parameter("LineErrorRatingFactor", 
				"Faktor, mit dem konkrete Fehlerfunde von FindBugs in einer Zeile bewertet werden sollen", 
				new Double(3.0), new Double(10), true, false));
		this.codeErrorDataSource.addErrorIndicator(errorIndicator);
	}


	public List<UncoveredBranch> getUnsortedList() {
		this.baseRecommendationListCreator.createRecommendationList();
		ArrayList<UncoveredBranch> uncoveredBranches = this.baseRecommendationListCreator.getUncoveredBranches();
		// Cache füllen
		for (UncoveredBranch ub : uncoveredBranches) {
			getIResourceOfBranch(ub);
		}
		return uncoveredBranches;
	}

	public List<ErrorDataSource> getErrorDataSources() {
		return errorDataSources;
	}

	public ErrorDataSource getCodeErrorDataSource() {
		return codeErrorDataSource;
	}

	public ErrorDataSource getVersionErrorDataSource() {
		return versionErrorDataSource;
	}

	public ErrorDataSource getCcErrorDataSource() {
		return ccErrorDataSource;
	}

	public ErrorDataSource getExpertErrorDataSource() {
		return expertErrorDataSource;
	}

	public ErrorDataSource getQualityErrorDataSource() {
		return qualityErrorDataSource;
	}

	public ErrorDataSource getProcessDataSource() {
		return processDataSource;
	}

	public Set<IResource> determineRelevantFiles() {
		// Rausfinden, welche Dateien uns überhaupt interessieren
		relevantFiles = new HashSet<IResource>();
		this.baseRecommendationListCreator.reset();
		for (UncoveredBranch ub : this.baseRecommendationListCreator.getUncoveredBranches()) {
			IResource pathOfBranch = getIResourceOfBranch(ub);
			ub.resource = pathOfBranch;
			if (pathOfBranch == null) {
			} else {
				relevantFiles.add(pathOfBranch);
				if (ub.lineFrom == null) {
					LocationList locationList = ub.m_branchInfo.m_hierarchyLevel.getLocation();
					if (locationList != null && locationList.getLocations() != null && locationList.getLocations().size() > 0) {
						Location location = locationList.getLocations().get(0);
						List<Integer> lineNumbers = LineNumberDeterminator.getLineNumbers(pathOfBranch, location.getStartOffset(), location.getEndOffset());
						ub.lineFrom = lineNumbers.get(0);
						ub.lineTo = lineNumbers.get(lineNumbers.size()-1);
					}

				}

			}
		}
		return relevantFiles;
	}
	
	public void sort() {
		
		this.hLevToFileCache = new HashMap<HierarchyLevel, IResource>();
		determineRelevantFiles();
		
		List<Thread> threads = new ArrayList<Thread>();
		for (final ErrorDataSource eds : this.errorDataSources) {
			Thread thread = new Thread() {
				public void run() {
					eds.setRelevantFiles(relevantFiles);
					eds.setBranches(baseRecommendationListCreator.getUncoveredBranches());
					eds.invoke();
				};
			};
			threads.add(thread);
			thread.start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		this.sortedList = new ArrayList<UncoveredBranch>(200);
		for (UncoveredBranch ub : this.baseRecommendationListCreator.getUncoveredBranches()) {
			ub.score = 0.0;
			StringBuilder infoMsg = new StringBuilder();
			for (ErrorDataSource eds : this.errorDataSources) {
				infoMsg.append("EDS: "+eds.getName()+"\n");
				Double score = 0.0d;
				if (ub.resource != null) {
					for (ErrorIndicator ei : eds.getErrorIndicators()) {
						double t = ei.getValueFor(ub.resource);
						score += t;
						if (t>0.0) infoMsg.append("EI-file: "+ei.getName()+": "+t+"\n");
					}
					for (int line = ub.lineFrom ; line < ub.lineTo ; line++) {
						for (ErrorIndicator ei : eds.getErrorIndicators()) {
							double t = ei.getValueFor(ub.resource, line) * eds.getWeight();
							score += t;
							if (t>0.0) infoMsg.append("EI-line: "+ei.getName()+" line:"+line+": "+t+"\n");
						}
					}
				}
				for (ErrorIndicator ei : eds.getErrorIndicators()) {
					double t = ei.getValueFor(ub);
					score += t;
					if (t>0.0) infoMsg.append("EI-branch: "+ei.getName()+": "+t+"\n");
				}
				ub.sourceScore.put(eds, score);
				ub.score += score;
				ub.info = infoMsg.toString();
			}
			this.sortedList.add(ub);
		}
		Collections.sort(this.sortedList, this.weightSorter);
		filter(this.sortedList);
	}

	private void filter(ArrayList<UncoveredBranch> sortedList2) {
		if (!(this.filterPackages == null || this.filterPackages.size() == 0 || this.packageFilterMode == PackageFilterMode.NONE)) {
			List<UncoveredBranch> matches = new ArrayList<UncoveredBranch>();
			for (UncoveredBranch uncoveredBranch : sortedList2) {
				HierarchyLevel hLev = MAST.getTopLevelClass(uncoveredBranch.m_branchInfo.m_hierarchyLevel, this.testSessionContainer);
				IJavaElement packagge = null;
				if (hLevToPackageCache.containsKey(hLev)) {
					packagge = hLevToPackageCache.get(hLev);
				} else {
					String fqn = MAST.getFQName(hLev, this.testSessionContainer);
					Set<ICompilationUnit> cuSet = EclipseMASTLinkage.Eclipse.findCompilationUnit(fqn);
					// Scheinbar enthält das Set stets nur eine CompilationUnit...
					for (ICompilationUnit cu : cuSet) {
						IJavaElement parent = cu.getParent();
						hLevToPackageCache.put(hLev, parent);
						packagge = parent;
					}
				}
				if (this.filterPackages.contains(packagge.getElementName())) {
					matches.add(uncoveredBranch);
				}
			}
			if (this.packageFilterMode == PackageFilterMode.BLACKLIST) {
				for (UncoveredBranch uncoveredBranch : matches) {
					sortedList2.remove(uncoveredBranch);
				}
			} else if (this.packageFilterMode == PackageFilterMode.WHITELIST) {
				sortedList2.clear();
				sortedList2.addAll(matches);
			}
		}
		
		// Typ-Filter
		List<UncoveredBranch> matches = new ArrayList<UncoveredBranch>();
		for (UncoveredBranch uncoveredBranch : sortedList2) {
			if (!this.getFilterBranches().contains(uncoveredBranch.m_branchInfo.m_type)) {
				matches.add(uncoveredBranch);
			}
		}
		
		sortedList2.removeAll(matches);
		
		
		List<UncoveredBranch> remove = new ArrayList<UncoveredBranch>();
		for (UncoveredBranch uncoveredBranch : sortedList2) {
			HierarchyLevel hLev = MAST.getTopLevelClass(uncoveredBranch.m_branchInfo.m_hierarchyLevel, this.testSessionContainer);
			if (this.hLevToCompUnitCache.containsKey(hLev)) {
				String name = this.hLevToCompUnitCache.get(hLev).getElementName();
				if (this.excludeClassList.contains(name)) {
					remove.add(uncoveredBranch);
				}
			}
		}
		
		sortedList2.removeAll(remove);
	}
	
	public void excludeClass(UncoveredBranch o) {
		HierarchyLevel hLev = MAST.getTopLevelClass(o.m_branchInfo.m_hierarchyLevel, this.testSessionContainer);
		if (this.hLevToCompUnitCache.containsKey(hLev)) {
			String className = this.hLevToCompUnitCache.get(hLev).getElementName();
			this.excludeClassList.add(className);
		}
	}
	
	public void resetClassFilter() {
		this.excludeClassList.clear();
	}

	public PackageFilterMode getPackageFilterMode() {
		return packageFilterMode;
	}

	private IResource getIResourceOfBranch(UncoveredBranch ub) {
		long a = new Date().getTime();

		HierarchyLevel hLev = MAST.getTopLevelClass(ub.m_branchInfo.m_hierarchyLevel, this.testSessionContainer);

		if (hLevToFileCache.containsKey(hLev)) {
			return hLevToFileCache.get(hLev);
		}
		/* find corresponding compilation unit by class name */
		String fqn = MAST.getFQName(hLev, this.testSessionContainer);
		Set<ICompilationUnit> cuSet;
		cuSet = EclipseMASTLinkage.Eclipse.findCompilationUnit(fqn);
		IResource resource = null;
		// Scheinbar enthält das Set stets nur eine CompilationUnit...
		for (ICompilationUnit cu : cuSet) {
			resource = cu.getResource();
			this.hLevToCompUnitCache.put(hLev, cu);
			fileToTestCaseMap.put(resource, ub.m_testCaseList);
			if (resource != null) {
				this.hLevToFileCache.put(hLev, resource);
			}
			break;
		}
		long b = new Date().getTime();
		return resource;
	}
	
	public static List<TestCase> getTestCasesOfFile(IResource resource) {
		return fileToTestCaseMap.get(resource);
	}

	public List<UncoveredBranch> getSortedList() {
		return this.sortedList;
	}

	/**
	 * Gewichtung geändert, neu berechnen, aber die invoke()-Methoden nicht aufrufen.
	 */
	public void weightChanged() {
		this.sortedList = new ArrayList<UncoveredBranch>(200);
		for (UncoveredBranch ub : this.baseRecommendationListCreator.getUncoveredBranches()) {
			ub.score = 0.0;
			StringBuilder infoMsg = new StringBuilder();
			for (ErrorDataSource eds : this.errorDataSources) {
				infoMsg.append("EDS: "+eds.getName()+"\n");
				Double score = 0.0d;
				if (ub.resource != null) {
					for (ErrorIndicator ei : eds.getErrorIndicators()) {
						double t = ei.getValueFor(ub.resource);
						score += t;
						if (t>0.0) infoMsg.append("EI-file: "+ei.getName()+": "+t+"\n");
					}
					for (int line = ub.lineFrom ; line < ub.lineTo ; line++) {
						for (ErrorIndicator ei : eds.getErrorIndicators()) {
							double t = ei.getValueFor(ub.resource, line) * eds.getWeight();
							score += t;
							if (t>0.0) infoMsg.append("EI-line: "+ei.getName()+" line:"+line+": "+t+"\n");
						}
					}
				}
				for (ErrorIndicator ei : eds.getErrorIndicators()) {
					double t = ei.getValueFor(ub);
					score += t;
					if (t>0.0) infoMsg.append("EI-branch: "+ei.getName()+": "+t+"\n");
				}
				ub.sourceScore.put(eds, score);
				ub.score += score;
				ub.info = infoMsg.toString();
			}
			this.sortedList.add(ub);
		}
		Collections.sort(this.sortedList, this.weightSorter);
		filter(this.sortedList);
	}

	public void setPackageFilter(Object[] result) {
		this.filterPackages = new HashSet<String>();
		if (result != null) {
			for (Object object : result) {
				if (object instanceof org.eclipse.jdt.internal.core.PackageFragment) {
					PackageFragment packagge = (PackageFragment) object;
					System.out.println(packagge.getElementName());
					System.out.println(packagge.getResource().getName());
					this.filterPackages.add(packagge.getElementName());
				}
			}
		} else {
			this.packageFilterMode = PackageFilterMode.NONE;
		}
	}

	public void setPackageFilterMode(PackageFilterMode mode) {
		this.packageFilterMode = mode;
	}

	public List<BranchType> getFilterBranches() {
		return filterBranches;
	}

	public void setFilterBranches(List<BranchType> filterBranches) {
		this.filterBranches = filterBranches;
	}

}
