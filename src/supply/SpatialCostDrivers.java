package supply;

import scenario.Region;

/* 
Eignet sich auch für ein Modell mit Netzwerk. In dem Fall sind die Parameter halt Null und die Distanzen kommen direkt aus dem Modell. 

*/


public class SpatialCostDrivers {

	private Region region;
	private double localCostParameter;
	private double connectionCostParameter;
	private double vehicleUtilizationParameter;
	private double collectionLocalCostParameter;
	private double distributionLocalCostParameter;
	private double collectionConnectionCostParameter;
	private double distributionConnectionCostParameter;
	private double collectionVehicleUtilizationParameter;
	private double distributionVehicleUtilizationParameter;
	private double collectionLocalDistance;
	private double distributionLocalDistance;
	private double collectionApproachDistance;
	private double distributionApproachDistance;

	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public double getLocalCostParameter() {
		return localCostParameter;
	}
	public void setLocalCostParameter(double localCostParameter) {
		this.localCostParameter = localCostParameter;
	}
	public double getConnectionCostParameter() {
		return connectionCostParameter;
	}
	public void setConnectionCostParameter(double connectionCostParameter) {
		this.connectionCostParameter = connectionCostParameter;
	}
	public double getVehicleUtilizationParameter() {
		return vehicleUtilizationParameter;
	}
	public void setVehicleUtilizationParameter(double vehicleUtilizationParameter) {
		this.vehicleUtilizationParameter = vehicleUtilizationParameter;
	}
	
	public double getCollectionLocalDistance() {
		return collectionLocalDistance;
	}
	public void setCollectionLocalDistance(double collectionLocalDistance) {
		this.collectionLocalDistance = collectionLocalDistance;
	}
	public double getDistributionLocalDistance() {
		return distributionLocalDistance;
	}
	public void setDistributionLocalDistance(double distributionLocalDistance) {
		this.distributionLocalDistance = distributionLocalDistance;
	}
	public double getCollectionApproachDistance() {
		return collectionApproachDistance;
	}
	public void setCollectionApproachDistance(double collectionApproachDistance) {
		this.collectionApproachDistance = collectionApproachDistance;
	}
	public double getDistributionApproachDistance() {
		return distributionApproachDistance;
	}
	public void setDistributionApproachDistance(double distributionApproachDistance) {
		this.distributionApproachDistance = distributionApproachDistance;
	}
	public double getCollectionLocalCostParameter() {
		return collectionLocalCostParameter;
	}
	public void setCollectionLocalCostParameter(double collectionLocalCostParameter) {
		this.collectionLocalCostParameter = collectionLocalCostParameter;
	}
	public double getDistributionLocalCostParameter() {
		return distributionLocalCostParameter;
	}
	public void setDistributionLocalCostParameter(double distributionLocalCostParameter) {
		this.distributionLocalCostParameter = distributionLocalCostParameter;
	}
	public double getCollectionConnectionCostParameter() {
		return collectionConnectionCostParameter;
	}
	public void setCollectionConnectionCostParameter(double collectionConnectionCostParameter) {
		this.collectionConnectionCostParameter = collectionConnectionCostParameter;
	}
	public double getDistributionConnectionCostParameter() {
		return distributionConnectionCostParameter;
	}
	public void setDistributionConnectionCostParameter(double distributionConnectionCostParameter) {
		this.distributionConnectionCostParameter = distributionConnectionCostParameter;
	}
	public double getCollectionVehicleUtilizationParameter() {
		return collectionVehicleUtilizationParameter;
	}
	public void setCollectionVehicleUtilizationParameter(double collectionVehicleUtilizationParameter) {
		this.collectionVehicleUtilizationParameter = collectionVehicleUtilizationParameter;
	}
	public double getDistributionVehicleUtilizationParameter() {
		return distributionVehicleUtilizationParameter;
	}
	public void setDistributionVehicleUtilizationParameter(double distributionVehicleUtilizationParameter) {
		this.distributionVehicleUtilizationParameter = distributionVehicleUtilizationParameter;
	}
	
	
	
	

}
