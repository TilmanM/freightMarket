package supply;

public class RegressionCostDriverUpdater implements CostDriverUpdater {
	private TSP tsp;
	
	public RegressionCostDriverUpdater(TSP tsp){
		this.tsp = tsp;
	}
	
	public void updateCostDrivers(){
		for(Facility facility : tsp.getFacilities()){
			updateSpatialCostDrivers(facility);
			updateMainRunCostDrivers(facility);
		}
	}
	
	private void updateSpatialCostDrivers(Facility facility){
		double collectionLocalDistance = facility.getCostDrivers().getSpatialCostDrivers().getLocalCostParameter() *
				Math.sqrt(facility.getUtilizationInformation().getNumberOfOutgoingFlows() *
						Math.pow(facility.getCostDrivers().getSpatialCostDrivers().getRegion().getRadius(), 2)*
						Math.PI);
		facility.getCostDrivers().getSpatialCostDrivers().setCollectionLocalDistance(collectionLocalDistance);
		
		double distributionLocalDistance = facility.getCostDrivers().getSpatialCostDrivers().getLocalCostParameter() *
				Math.sqrt(facility.getUtilizationInformation().getNumberOfIncomingFlows() *
						Math.pow(facility.getCostDrivers().getSpatialCostDrivers().getRegion().getRadius(), 2)*
						Math.PI);
		facility.getCostDrivers().getSpatialCostDrivers().setDistributionLocalDistance(distributionLocalDistance);
		
		double collectionApproachDistance = facility.getCostDrivers().getSpatialCostDrivers().getConnectionCostParameter()*
				facility.getUtilizationInformation().getNumberOfOutgoingFlows() * 
				Math.ceil(facility.getUtilizationInformation().getVolumeOfOutgoingFlows()/tsp.getType().getResources().getLocalVehicleType().getCapacity())
				* (facility.getRegion().getRadius()/Math.sqrt(2));
		facility.getCostDrivers().getSpatialCostDrivers().setCollectionApproachDistance(collectionApproachDistance);
		
		double distributionApproachDistance = facility.getCostDrivers().getSpatialCostDrivers().getConnectionCostParameter()*
				facility.getUtilizationInformation().getNumberOfIncomingFlows() * 
				Math.ceil(facility.getUtilizationInformation().getVolumeOfIncomingFlows()/tsp.getType().getResources().getLocalVehicleType().getCapacity())
				* (facility.getRegion().getRadius()/Math.sqrt(2));
		facility.getCostDrivers().getSpatialCostDrivers().setDistributionApproachDistance(distributionApproachDistance);
	}
	
	private void updateMainRunCostDrivers(Facility facility){
		//Methodensignatur eigentliche Methode ist in diesem Fall unnötig.
	}
	
}
