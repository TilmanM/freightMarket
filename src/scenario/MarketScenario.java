
package scenario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.log4j.Logger;
import demand.Company;
import demand.DemandBehaviorParameters;
import demand.Flow;
import io.CompanyReaderImpl;
import io.FlowParameterReaderImpl;
import io.FlowReaderImpl;
import io.InitialTariffTableReaderImpl;
import io.LanesReaderImpl;
import io.MainRunCostDriverReaderImpl;
import io.RegionsReaderImpl;
import io.SourceReaderImpl;
import io.SpatialCostDriverReaderImpl;
import io.TSPTypeReaderImpl;
import supply.MarketTariffTable;
import supply.MarketTariffTableCreatorImpl;
import supply.SpatialCostDrivers;
import supply.TSP;
import supply.TSPDirectionCostDrivers;
import supply.TSPType;


public class MarketScenario {
	
	private static final Logger logger = Logger.getLogger(MarketScenario.class.getName());
	
	private Collection<MarketLane> marketLanes;
	private Collection<Region> regions;
	private Collection<Company> companies;
	private Collection<Flow> flows;
	private MarketTariffTable tariffTable;
	private ArrayList<TSPType> tspTypes;
	private Collection<TSP> tsps;
	private TSP newcomer;
	private DemandBehaviorParameters flowParameters;
	private TSPCreator tspCreator;
	private Collection<SpatialCostDrivers> spatialDrivers;
	private Collection<TSPDirectionCostDrivers> mainRunDrivers;
	
	private static MarketScenario uniqueScenario;
	
	private MarketScenario(){
		logger.trace("Start creating market scenario");
		this.marketLanes = new ArrayList<MarketLane>();
		this.regions = new ArrayList<Region>();
		this.flows = new ArrayList<Flow>();
		this.companies = new ArrayList<Company>();
		this.tspTypes = new ArrayList<TSPType>();
		this.tspCreator = new TSPCreatorImpl();
		this.tsps = new ArrayList<TSP>();
		this.spatialDrivers = new ArrayList<SpatialCostDrivers>();
		this.mainRunDrivers = new ArrayList<TSPDirectionCostDrivers>();
		logger.trace("Empty market scenario created");
	}
	
	public static MarketScenario getInstance(){
		if(uniqueScenario == null){
			uniqueScenario = new MarketScenario();
			return uniqueScenario;
		}
		else{
			return uniqueScenario;
		}
	}
	
	

	public Collection<MarketLane> getMarketLanes() {
		return marketLanes;
	}

	public void setMarketLanes(Collection<MarketLane> marketLanes) {
		this.marketLanes = marketLanes;
	}

	public Collection<Region> getRegions() {
		return regions;
	}

	public void setRegions(Collection<Region> regions) {
		this.regions = regions;
	}

	public Collection<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Collection<Company> companies) {
		this.companies = companies;
	}

	public Collection<Flow> getFlows() {
		return flows;
	}

	public void setFlows(Collection<Flow> flows) {
		this.flows = flows;
	}

	public void removeNewcomer(){
		this.newcomer = null;
	}
	
	public DemandBehaviorParameters getFlowParameters() {
		return flowParameters;
	}


	public void setFlowParameters(DemandBehaviorParameters flowParameters) {
		this.flowParameters = flowParameters;
	}


	public MarketTariffTable getTariffTable() {
		return tariffTable;
	}


	public void setTariffTable(MarketTariffTable tariffTable) {
		this.tariffTable = tariffTable;
	}


	public ArrayList<TSPType> getTspTypes() {
		return tspTypes;
	}


	public void setTspTypes(ArrayList<TSPType> tspTypes) {
		this.tspTypes = tspTypes;
	}


	public Collection<TSP> getTsps() {
		return tsps;
	}

	public Collection<SpatialCostDrivers> getSpatialDrivers() {
		return spatialDrivers;
	}

	public Collection<TSPDirectionCostDrivers> getMainRunDrivers() {
		return mainRunDrivers;
	}

	private void assignFlowsToLanes(){
		Iterator<Flow> flowIterator = flows.iterator();
		while(flowIterator.hasNext()){
			Flow flow = flowIterator.next();
			Iterator<MarketLane> lanesIterator = marketLanes.iterator();
			
			while(lanesIterator.hasNext()){
				MarketLane lane = lanesIterator.next();
				if(checkCompanyIsInLane(flow.getShipper(),lane) 
						&& checkCompanyIsInLane(flow.getRecipient(),lane)){
					lane.getFlows().add(flow);
					logger.trace("Flow " + flow.getId() + " assigned to lane " + lane.getId() );
					flow.setMarketLane(lane);
					logger.trace("Lane " + lane.getId() + " entered in flow " + flow.getId());
				}					
				Region forwardFrom = lane.getForwardDirection().getFromRegion();
				if(forwardFrom == flow.getShipper().getRegion()){
					lane.getForwardDirection().getFlows().add(flow);
				}
				else{
					lane.getBackwardDirection().getFlows().add(flow);
				}
			}			
		}
		logger.trace("All flows assigned to their market lanes");
	}

	
	private boolean checkCompanyIsInLane(Company company, MarketLane lane){
		boolean isInLane;
		if(company.getRegion() == lane.getFirstRegion()
				|| company.getRegion() == lane.getSecondRegion()){
			isInLane = true;
		}
		else{
			isInLane = false;
		}
		return isInLane;
	}
	
		
	public void initializeScenario(String sourceFilePath){
		logger.trace("Market scenario started to initialize with data from the source file " + sourceFilePath);
		SourceReaderImpl sourceReader = new SourceReaderImpl(sourceFilePath);
		initializeDemand(sourceReader);
		initializeSupply(sourceReader);
	}
	
	private void initializeDemand(SourceReaderImpl sourceReader){
		logger.trace("Demand side of the scenario started to initialize");
		sourceReader.readSources();
		String regionFilePath = sourceReader.getRegionsFilePath();
		RegionsReaderImpl  regionsReader = new RegionsReaderImpl(regionFilePath);
		this.regions = regionsReader.getRegionsFromFile();
		String lanesFilePath = sourceReader.getLanesFilePath();
		LanesReaderImpl lanesReader = new LanesReaderImpl(lanesFilePath);
		this.marketLanes = lanesReader.getLanesFromFile(regions);
		String companiesFilePath = sourceReader.getCompaniesFilePath();
		CompanyReaderImpl companyReader = new CompanyReaderImpl(companiesFilePath);
		this.companies = companyReader.getCompaniesFromFile(regions);
		String flowParametersFilepath = sourceReader.getFlowParameterFilePath();
		FlowParameterReaderImpl flowParameterReader = new FlowParameterReaderImpl(flowParametersFilepath);
		this.flowParameters = flowParameterReader.getFlowParametersFromFile();
		String flowsFilePath = sourceReader.getFlowsFilePath();
		FlowReaderImpl flowReader = new FlowReaderImpl(flowsFilePath);
		this.flows = flowReader.getFlowsFromFile(companies);
		this.assignFlowsToLanes();
		this.assignParametersToFlows();
		logger.trace("Demand side of the scenario initialized");
	}
	
	private void initializeSupply(SourceReaderImpl sourceReader){
		logger.trace("Supply side of the scenario started to initialize");
		String tariffsFilePath = sourceReader.getInitialTariffTableFilePath();
		InitialTariffTableReaderImpl tariffReader = new InitialTariffTableReaderImpl(tariffsFilePath);
		MarketTariffTableCreatorImpl tableCreator = new MarketTariffTableCreatorImpl();
		this.tariffTable =  tableCreator.createMarketTariffTable(tariffReader.getTariffsFromFile(regions));
		String tsptypesFilePath = sourceReader.getTspTypesFilePath();
		TSPTypeReaderImpl tspTypeReader = new TSPTypeReaderImpl(tsptypesFilePath);
		this.tspTypes = new ArrayList(tspTypeReader.getTSPTypesFromFile());
		String spatialDriversFilePath = sourceReader.getSpatialCostDriversFilePath();
		SpatialCostDriverReaderImpl spatialReader = new SpatialCostDriverReaderImpl(spatialDriversFilePath);
		this.spatialDrivers = spatialReader.getCostDriversFromFile(this.regions);
		String mainRunCostDriversPath = sourceReader.getMainRunCostDriversFilePath();
		MainRunCostDriverReaderImpl mainRunReader = new MainRunCostDriverReaderImpl(mainRunCostDriversPath);
		this.mainRunDrivers = mainRunReader.getCostDriversFromFile(this.regions);
		TSP initialTSP = createTSP();
		this.assignTSPtoMarketLanes(initialTSP);
		this.tsps.add(initialTSP);;
		logger.trace("Supply side of the scenario initialized");

	}
			
	
	public TSP createTSP(){
		return tspCreator.createTSP(this);
	}

	public TSP getNewcomer() {
		return newcomer;
	}

	public void addNewcomer(TSP newcomer) {
		this.newcomer = newcomer;
	}
	
	private void assignParametersToFlows(){
		logger.trace("Start to assign behavior parameters to flows");
		for(Flow flow : this.flows){
			flow.setParameters(this.flowParameters);
			logger.trace("Behavior parameters assigned to flow " + flow.getId());
		}
		logger.trace("Behavior parameters assigned to flows");
	}
	
	private void assignTSPtoMarketLanes(TSP tsp){
		logger.trace("Start to assign the first TSP to the market lanes");
		for(MarketLane lane : marketLanes){
			if(lane.isOpen()){
				lane.getTSPs().add(tsp);
				logger.trace("First TSP assigned to market lane " + lane.getId());
			}
		}
		logger.trace("Assigned the first TSP to the market lanes");
	}
	
	public void clearScenario(){
		logger.trace("Start to clear scenario");
		this.companies.clear();
		this.flows.clear();
		this.mainRunDrivers.clear();
		this.regions.clear();
		this.spatialDrivers.clear();
		this.tsps.clear();
		this.tspTypes.clear();
		logger.trace("Scenario cleared");

	}

	

}

