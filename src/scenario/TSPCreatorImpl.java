
package scenario;

import org.apache.log4j.Logger;

import supply.EuclideanFacilityDistanceAssigner;
import supply.Facility;
import supply.FacilityImpl;
import supply.MarketTariffTableEntry;
import supply.SpatialCostDrivers;
import supply.TSP;
import supply.TSPDirection;
import supply.TSPDirectionCostDrivers;
import supply.TSPImpl;
import supply.TSPLane;
import supply.TSPLaneImpl;
import supply.TSPRegion;
import supply.TSPRegionImpl;
import supply.TSPTariffTable;
import supply.TSPTariffTableEntry;
import supply.TSPTariffTableEntryImpl;
import supply.TSPTariffTableImpl;



public class TSPCreatorImpl implements TSPCreator {
	
	
	private static final Logger logger = Logger.getLogger(TSPCreatorImpl.class.getName());
	
	private TSP tsp;
	private MarketScenario scenario;
	
	public TSP createTSP(MarketScenario scenario){
		this.scenario = scenario;
		this.tsp = new TSPImpl();
		tsp.setId((scenario.getTsps().size()+1)+"");
		tsp.setType(scenario.getTspTypes().get(0));
		logger.trace("Start creating new TSP with number: " + scenario.getTsps().size()+1 + " and type: " + tsp.getType());
		createAndAssignTSPLanes(tsp);
		createAndAssignTSPTariffTable(tsp);
		assignRegions(tsp);
		createOrUpdateFacilities(tsp);
		assignFacilityDistances(tsp);
		logger.trace("TSP successfully created");
		logger.trace("TSP successfully created");
		return tsp;
	}

	private void createAndAssignTSPLanes(TSP tsp){
		logger.trace("Start assigning TSP " + tsp.getId() + " to market lanes");
		for(MarketLane marketLane : scenario.getMarketLanes()){
			if(marketLane.isOpen()){
				TSPLane tspLane = new TSPLaneImpl(marketLane.getId());
				TSPRegion firstRegion = new TSPRegionImpl();
				firstRegion.setRegion(marketLane.getFirstRegion());
				tspLane.setFirstRegion(firstRegion);
				TSPRegion secondRegion = new TSPRegionImpl();
				secondRegion.setRegion(marketLane.getSecondRegion());
				tspLane.setSecondRegion(secondRegion);
				tspLane.setMarketLane(marketLane);
				tspLane.createDirections();
				tsp.getLanes().add(tspLane);
				logger.trace("Lane " + tspLane.getMarketLane().getId() + " assigned to TSP " + tsp.getId());
			}
		}	
		logger.trace("Market lanes successfully added to TSP " + tsp.getId());
	}

	private void createAndAssignTSPTariffTable(TSP tsp){
		logger.trace("Start creating initial tariff table of TSP " + tsp.getId());
		TSPTariffTable table = new TSPTariffTableImpl();
		for(TSPLane lane : tsp.getLanes()){
			Region forwardFrom =  lane.getForwardDirection().getFromRegion();
			Region forwardTo =  lane.getForwardDirection().getToRegion();
			MarketTariffTableEntry marketEntry = scenario.getTariffTable().getTariff(forwardFrom, forwardTo);
			TSPTariffTableEntry tspEntry = new TSPTariffTableEntryImpl(marketEntry);
			table.addEntry(tspEntry);
			Region backwardFrom =  lane.getBackwardDirection().getFromRegion();
			Region backwardTo =  lane.getBackwardDirection().getToRegion();
			marketEntry = scenario.getTariffTable().getTariff(backwardFrom, backwardTo);
			tspEntry = new TSPTariffTableEntryImpl(marketEntry);
			table.addEntry(tspEntry);	
			logger.trace("Entry " + tspEntry.getId() + " added to tariff table of TSP " + tsp.getId());
		}
		tsp.setTariffTable(table);
		logger.trace("Initial tariff table of TSP " + tsp.getId() + " successfully created");
	}
	
	
	private void assignRegions(TSP tsp){
		logger.trace("Assign references to regions to TSP " + tsp.getId());
		for(TSPLane lane : tsp.getLanes()){
			if(!tsp.getTspRegions().containsKey(lane.getFirstRegion())){
				TSPRegion region = new TSPRegionImpl();
				region.setRegion(lane.getFirstRegion());
				tsp.getTspRegions().put(region.getRegion().getId(), region);
			}
			if (!tsp.getTspRegions().containsKey(lane.getSecondRegion())){
				TSPRegion region = new TSPRegionImpl();
				region.setRegion(lane.getSecondRegion());
				tsp.getTspRegions().put(region.getRegion().getId(), region);
			}
		
		}
		logger.trace("Reference to regions succesfully assigned to TSP " + tsp.getId());
	}
	
	private void createOrUpdateFacilities(TSP tsp){
		logger.trace("Start creating or updating facilities of TSP " + tsp.getId());
		for(TSPLane lane : tsp.getLanes()){
			Region firstRegion = lane.getFirstRegion();
			Region secondRegion = lane.getSecondRegion();
			
			if(tsp.getTspRegions().get(firstRegion.getId()).getFacility() != null){
				tsp.getTspRegions().get(firstRegion.getId()).getFacility().increaseCapacity();	
				logger.trace("Increase capacity of existing facility in region " + firstRegion.getId());
				tsp.getTspRegions().get(firstRegion.getId()).getFacility().getUtilizationInformation().getLanes().add(lane);
				logger.trace("Add lane " + lane.getMarketLane().getId() + " to facility " + lane.getFirstFacility());
				if(lane.getMainRunFacility()==null){
					lane.setMainRunFacility(tsp.getTspRegions().get(firstRegion.getId()).getFacility());
				}
			}
			else{
				Facility  facility = new FacilityImpl(firstRegion, tsp); 
				facility.setType(tsp.getType().getResources().getFacilityType());
				facility.setTsp(tsp);
				tsp.getTspRegions().get(firstRegion.getId()).setFacility(facility);
				tsp.getTspRegions().get(firstRegion.getId()).getFacility().getUtilizationInformation().getLanes().add(lane);
				logger.trace("Create new facility in region " + firstRegion.getId());
				logger.trace("Add lane " + lane.getMarketLane().getId() + " to facility " + lane.getFirstFacility());
				if(lane.getMainRunFacility()==null){				
					lane.setMainRunFacility(facility);
				}
			}
			if(tsp.getTspRegions().get(secondRegion.getId()).getFacility() != null){
				tsp.getTspRegions().get(secondRegion.getId()).getFacility().increaseCapacity();	
				logger.trace("Increase capacity of existing facility in region " + secondRegion.getId());
				tsp.getTspRegions().get(secondRegion.getId()).getFacility().getUtilizationInformation().getLanes().add(lane);
				logger.trace("Add lane " + lane.getMarketLane().getId() + " to facility " + lane.getSecondFacility());
				if(lane.getMainRunFacility()==null){				
					lane.setMainRunFacility(tsp.getTspRegions().get(secondRegion.getId()).getFacility());
				}					
			}
			else{
				Facility  facility = new FacilityImpl(secondRegion, tsp); 
				facility.setType(tsp.getType().getResources().getFacilityType());
				tsp.getTspRegions().get(secondRegion.getId()).setFacility(facility);
				tsp.getTspRegions().get(secondRegion.getId()).getFacility().getUtilizationInformation().getLanes().add(lane);
				logger.trace("Create new facility in region " + secondRegion.getId());
				logger.trace("Add lane " + lane.getMarketLane().getId() + " to facility " + lane.getSecondFacility());
				if(lane.getMainRunFacility()==null){
					lane.setMainRunFacility(tsp.getTspRegions().get(secondRegion.getId()).getFacility());
				}	
			}		
		}
	}


	private void assignSpatialCostDrivers(TSP tsp){
		logger.trace("Start assigning spatial cost drivers to the facilities of TSP " + tsp.getId());
		for(Facility facility : tsp.getFacilities()){
			logger.trace("Assign spatial cost drivers to facilities " + facility.getId());
			for(SpatialCostDrivers drivers : scenario.getSpatialDrivers()){
				if(drivers.getRegion() == facility.getRegion()){
				double distributionConnectionCostParameter = drivers.getDistributionConnectionCostParameter();
				facility.getCostDrivers().getSpatialCostDrivers().setDistributionConnectionCostParameter(distributionConnectionCostParameter);
				logger.trace("distributionConnectionCostParameter=" + drivers.getDistributionConnectionCostParameter());
				double distributionLocalCostParameter = drivers.getDistributionLocalCostParameter();
				facility.getCostDrivers().getSpatialCostDrivers().setDistributionLocalCostParameter(distributionLocalCostParameter);
				logger.trace("distributionLocalCostParameter=" + drivers.getDistributionLocalCostParameter());
				double distributionVehicleUtilizationParameter = drivers.getDistributionVehicleUtilizationParameter();
				facility.getCostDrivers().getSpatialCostDrivers().setDistributionVehicleUtilizationParameter(distributionVehicleUtilizationParameter);
				logger.trace("distributionVehicleUtilizationParameter" + drivers.getDistributionVehicleUtilizationParameter());
				double collectionConnectionCostParameter = drivers.getCollectionConnectionCostParameter();
				facility.getCostDrivers().getSpatialCostDrivers().setCollectionConnectionCostParameter(collectionConnectionCostParameter);
				logger.trace("collectionConnectionCostParameter=" + drivers.getCollectionConnectionCostParameter());
				double collectionLocalCostParameter = drivers.getCollectionLocalCostParameter();
				facility.getCostDrivers().getSpatialCostDrivers().setCollectionLocalCostParameter(collectionLocalCostParameter);
				logger.trace("collectionLocalCostParameter=" + drivers.getCollectionLocalCostParameter());
				double collectionVehicleUtilizationParameter = drivers.getCollectionVehicleUtilizationParameter();
				facility.getCostDrivers().getSpatialCostDrivers().setCollectionVehicleUtilizationParameter(collectionVehicleUtilizationParameter);
				logger.trace("collectionVehicleUtilizationParameter=" + drivers.getCollectionVehicleUtilizationParameter());
				}
			}
		}
	}
	
	private void assignMainRunCostDrivers(TSP tsp){
		logger.trace("Start assigning spatial cost drivers to the facilities of TSP " + tsp.getId());
		for(Facility facility : tsp.getFacilities()){
			for(TSPLane lane : facility.getTSPLanes()){
				if(lane.getMainRunFacility() == facility){
					TSPDirection forwardDirection = lane.getForwardDirection();
					TSPDirection backwardDirection = lane.getBackwardDirection();
					for(TSPDirectionCostDrivers drivers : scenario.getMainRunDrivers()){
						if((drivers.getFromRegion()== forwardDirection.getFromRegion())&&
								(drivers.getToRegion()== forwardDirection.getToRegion())){
							facility.getCostDrivers().getMainRunCostDrivers().put(forwardDirection, drivers);
							logger.trace("Main run cost drivers for the forward direction of lane " + forwardDirection.getId() + " assigned");
						}
						if((drivers.getFromRegion()== backwardDirection.getFromRegion())&&
								(drivers.getToRegion()== backwardDirection.getToRegion())){
							facility.getCostDrivers().getMainRunCostDrivers().put(backwardDirection, drivers);
							logger.trace("Main run cost drivers for the backward direction of lane " + backwardDirection.getId() + " assigned");
						}							
					}
				}
			}
		}	
	}
	

	private void assignFacilityDistances(TSP tsp){
		logger.trace("Start assigning distances between facilities of TSP " + tsp.getId());
		for(Facility facility : tsp.getFacilities()){
			for(TSPLane lane : facility.getUtilizationInformation().getLanes()){
				if(lane.getMainRunFacility() == facility){
					EuclideanFacilityDistanceAssigner assigner = new EuclideanFacilityDistanceAssigner();
					double distance = assigner.assignDistance(lane.getFirstFacility(), lane.getSecondFacility());
					TSPDirectionCostDrivers forwardDrivers = new TSPDirectionCostDrivers();
					forwardDrivers.setMainRunDistance(distance);
					forwardDrivers.setFromRegion(lane.getForwardDirection().getFromRegion());
					forwardDrivers.setToRegion(lane.getForwardDirection().getToRegion());
					facility.getCostDrivers().getMainRunCostDrivers().put(lane.getForwardDirection(), forwardDrivers);
					logger.trace("Distance on direction " + lane.getForwardDirection().getId() + " " + lane.getForwardDirection().getCostDrivers().getMainRunDistance());
					TSPDirectionCostDrivers backwardDrivers = new TSPDirectionCostDrivers();
					backwardDrivers.setMainRunDistance(distance);
					backwardDrivers.setFromRegion(lane.getBackwardDirection().getFromRegion());
					backwardDrivers.setToRegion(lane.getBackwardDirection().getToRegion());
					facility.getCostDrivers().getMainRunCostDrivers().put(lane.getBackwardDirection(), backwardDrivers);
					logger.trace("Distance on direction " + lane.getBackwardDirection().getId() + " " + lane.getBackwardDirection().getCostDrivers().getMainRunDistance());
				}
			}
		}
	}
}

