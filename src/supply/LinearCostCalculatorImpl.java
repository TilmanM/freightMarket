package supply;

import org.apache.log4j.Logger;

public class LinearCostCalculatorImpl implements CostCalculator {

	private static final Logger logger = Logger.getLogger(LinearCostCalculatorImpl.class.getName());
	
	private TSP tsp;
	
	public LinearCostCalculatorImpl (TSP tsp){
		this.tsp = tsp;
	}
	
	public void calculateCosts(){
		for(Facility facility : tsp.getFacilities()){		
			calculateFacilityCosts(facility);
			
			for(TSPLane lane : tsp.getLanes()){
				if(lane.getMainRunFacility() == facility){
					calculateMainRunCosts(facility,lane);
				}
				if(facility.getTSPLanes().contains(lane)){
					calculateTransshipmentCosts(facility,lane);
				}
			}
		}	
	}
	
	private void calculateFacilityCosts(Facility facility){
		logger.trace("Calculating facility costs of " + facility.getId() + facility.getTsp().getId());
		calculateCollectionCostsFixed(facility);
		calculateCollectionCostsLinear(facility);
		calculateDistributionCostsFixed(facility);
		calculateDistributionCostsLinear(facility);
	}
	
	private boolean collectionIsBigger(Facility facility){		
		
		if(getNumberOfCollectionVehicles(facility) >= getNumberOfDistributionVehicles(facility)){
			return true;
		}
		else{
			return false;
		}
	}
	
	private double getNumberOfCollectionVehicles(Facility facility){
		return Math.ceil(facility.getCostDrivers().getSpatialCostDrivers().getCollectionVehicleUtilizationParameter() 
				* Math.ceil(facility.getUtilizationInformation().getVolumeOfIncomingFlows()/tsp.getType().getResources().getLocalVehicleType().getCapacity()));
	}
	
	private double getNumberOfDistributionVehicles(Facility facility){
		return Math.ceil(facility.getCostDrivers().getSpatialCostDrivers().getDistributionVehicleUtilizationParameter() 
				* Math.ceil(facility.getUtilizationInformation().getVolumeOfOutgoingFlows()/tsp.getType().getResources().getLocalVehicleType().getCapacity()));
	}
	
	private void calculateCollectionCostsLinear(Facility facility){
		double shareOfVehicles = calculateShareOfCollectionVehicles(facility);
		double linearVehicleCosts = (shareOfVehicles * tsp.getType().getResources().getLocalVehicleType().getFixed()) / facility.getUtilizationInformation().getVolumeOfIncomingFlows() ;
		double linearDistanceCosts= facility.getCostDrivers().getSpatialCostDrivers().getCollectionApproachDistance();
		double linearCollectionCosts = linearVehicleCosts + linearDistanceCosts;
		facility.getCostValues().setCollectionCostsPerShipmentLinear(linearCollectionCosts);
		logger.trace("Linear collection costs of facility " + facility.getId() + " = " + linearCollectionCosts);
	}
	
	private void calculateCollectionCostsFixed(Facility facility){
		double fixedCollectionCosts= facility.getCostDrivers().getSpatialCostDrivers().getCollectionLocalDistance() / facility.getUtilizationInformation().getNumberOfIncomingFlows();
		facility.getCostValues().setCollectionCostsPerShipmentFixed(fixedCollectionCosts);
		logger.trace("Fixed collection costs of facility " + facility.getId() + " = " + fixedCollectionCosts);
	}
	
	private void calculateDistributionCostsLinear(Facility facility){
		double shareOfVehicles = calculateShareOfDistributionVehicles(facility);
		double linearVehicleCosts = (shareOfVehicles * tsp.getType().getResources().getLocalVehicleType().getFixed()) / facility.getUtilizationInformation().getVolumeOfOutgoingFlows() ;
		double linearDistanceCosts= facility.getCostDrivers().getSpatialCostDrivers().getDistributionApproachDistance() / facility.getUtilizationInformation().getVolumeOfOutgoingFlows() ;
		double linearDistributionCosts = linearVehicleCosts + linearDistanceCosts;
		facility.getCostValues().setDistributionCostsPerShipmentLinear(linearDistributionCosts);
		logger.trace("Linear distribution costs of facility " + facility.getId() + " = " + linearDistributionCosts);
	}
	
	private void calculateDistributionCostsFixed(Facility facility){
		double fixedDistributionCosts= facility.getCostDrivers().getSpatialCostDrivers().getDistributionLocalDistance() / facility.getUtilizationInformation().getNumberOfOutgoingFlows();
		facility.getCostValues().setDistributionCostsPerShipmentFixed(fixedDistributionCosts);
		logger.trace("Fixed distribution costs of facility " + facility.getId() + " = " + fixedDistributionCosts);
	}
		
	private double calculateShareOfCollectionVehicles(Facility facility){
		if(collectionIsBigger(facility)){
			return (getNumberOfCollectionVehicles(facility)-getNumberOfDistributionVehicles(facility))+getNumberOfDistributionVehicles(facility)/2;
		}
		else{
			return getNumberOfCollectionVehicles(facility)/2;
		}	
	}
	
	private double calculateShareOfDistributionVehicles(Facility facility){
		if(collectionIsBigger(facility)){
			return getNumberOfDistributionVehicles(facility)/2;
		}
		else{
			return  (getNumberOfDistributionVehicles(facility) - getNumberOfCollectionVehicles(facility)) + getNumberOfCollectionVehicles(facility)/2;
		}	
	}
	
	private void calculateMainRunCosts(Facility facility, TSPLane tspLane){
		TSPDirection strongerDirection = getStrongerDirection(tspLane);
		TSPDirection weakerDirection = getWeakerDirection(tspLane);
		long mainRunVehicleCapacity = tsp.getType().getResources().getMainRunVehicleType().getCapacity();
		double mainRunVehicleFixed = tsp.getType().getResources().getMainRunVehicleType().getFixed();
		double mainRunVehicleLinear = tsp.getType().getResources().getMainRunVehicleType().getLinear();
		
		int numberOfVehiclesInStrongerDirection = (int) Math.ceil(strongerDirection.getCostDrivers().getVehicleUtilizationParameter() * Math.ceil(strongerDirection.getTotalUtilization()/mainRunVehicleCapacity));
		int numberOfVehiclesInWeakerDirection = (int) Math.ceil(weakerDirection.getCostDrivers().getVehicleUtilizationParameter() * Math.ceil(weakerDirection.getTotalUtilization()/mainRunVehicleCapacity));	
		
		double shareOfVehiclesInStrongerDirection = numberOfVehiclesInStrongerDirection + 0.5  * numberOfVehiclesInWeakerDirection + (numberOfVehiclesInStrongerDirection - numberOfVehiclesInWeakerDirection);
		double shareOfVehiclesInWeakerDirection = 0.5  * numberOfVehiclesInWeakerDirection;
	
		double distanceCostsStrongerDirectionLinear = (strongerDirection.getCostDrivers().getMainRunDistance() * mainRunVehicleLinear)/ strongerDirection.getTotalUtilization();
		double distanceCostsWeakerDirectionLinear = (weakerDirection.getCostDrivers().getMainRunDistance() * mainRunVehicleLinear)/ weakerDirection.getTotalUtilization();
	
		double capacityCostsStrongerDirectionLinear = (shareOfVehiclesInStrongerDirection * mainRunVehicleFixed) / strongerDirection.getTotalUtilization();
		double capacityCostsWeakerDirectionLinear = (shareOfVehiclesInWeakerDirection * mainRunVehicleFixed) / weakerDirection.getTotalUtilization();
		
		facility.getCostValues().getMainRunCostsPerShipmentLinear().put(strongerDirection, (distanceCostsStrongerDirectionLinear + capacityCostsStrongerDirectionLinear));
		logger.trace("Linear main run costs on direction " + strongerDirection.getId() + "="+ (distanceCostsStrongerDirectionLinear + capacityCostsStrongerDirectionLinear));
		facility.getCostValues().getMainRunCostsPerShipmentLinear().put(weakerDirection, (distanceCostsWeakerDirectionLinear + capacityCostsWeakerDirectionLinear));
		logger.trace("Linear main run costs on direction " + weakerDirection.getId() + "=" + (distanceCostsWeakerDirectionLinear + capacityCostsWeakerDirectionLinear));
	}
	
	private TSPDirection  getStrongerDirection(TSPLane tspLane){
		if(tspLane.getForwardDirection().getTotalUtilization() >= tspLane.getBackwardDirection().getTotalUtilization()){
			return tspLane.getForwardDirection();
		}
		else{
			return tspLane.getBackwardDirection();
		}		
	}
	
	private TSPDirection  getWeakerDirection(TSPLane tspLane){
		if(tspLane.getForwardDirection().getTotalUtilization() >= tspLane.getBackwardDirection().getTotalUtilization()){
			return tspLane.getBackwardDirection();
		}
		else{
			return tspLane.getForwardDirection();
		}		
	}
		
	private void calculateTransshipmentCosts(Facility facility, TSPLane tspLane){
		TSPDirection strongerDirection = getStrongerDirection(tspLane);
		TSPDirection weakerDirection = getWeakerDirection(tspLane);
		double transshipmentCostsInStrongerDirectionLinear = (facility.getType().getIncrementCosts()*(strongerDirection.getTotalUtilization()/
															(strongerDirection.getTotalUtilization()+weakerDirection.getTotalUtilization())))/strongerDirection.getTotalUtilization();
		double transshipmentCostsInWeakerDirectionLinear = (facility.getType().getIncrementCosts()*(weakerDirection.getTotalUtilization()/
				(strongerDirection.getTotalUtilization()+weakerDirection.getTotalUtilization())))/weakerDirection.getTotalUtilization();		
		logger.trace("Transshipment costs in direction " + strongerDirection.getId() + "="+ transshipmentCostsInStrongerDirectionLinear);
		facility.getCostValues().getTransshipmentCostsPerShipmentLinear().put(strongerDirection, transshipmentCostsInStrongerDirectionLinear);
		logger.trace("Transshipment costs in direction " + weakerDirection.getId() + "="+ transshipmentCostsInWeakerDirectionLinear);
		facility.getCostValues().getTransshipmentCostsPerShipmentLinear().put(weakerDirection, transshipmentCostsInWeakerDirectionLinear);
	}
}

