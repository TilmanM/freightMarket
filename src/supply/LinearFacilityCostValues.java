package supply;

import java.util.HashMap;

class LinearFacilityCostValues implements FacilityCostValues {

	private double distributionCostsPerShipmentFixed;
	private double distributionCostsPerShipmentLinear;
	private double collectionCostsPerShipmentFixed;
	private double collectionCostsPerShipmentLinear;
	private HashMap<TSPDirection,Double> transshipmentCostsPerShipmentLinear;
	private HashMap<TSPDirection,Double> transshipmentCostsPerShipmentFixed;
	private HashMap<TSPDirection,Double> mainRunCostsPerShipmentFixed;
	private HashMap<TSPDirection,Double> mainRunCostsPerShipmentLinear;
	
	public double getDistributionCostsPerShipmentFixed() {
		return distributionCostsPerShipmentFixed;
	}
	public void setDistributionCostsPerShipmentFixed(
			double distributionCostsPerShipmentFixed) {
		this.distributionCostsPerShipmentFixed = distributionCostsPerShipmentFixed;
	}
	public double getDistributionCostsPerShipmentLinear() {
		return distributionCostsPerShipmentLinear;
	}
	public void setDistributionCostsPerShipmentLinear(
			double distributionCostsPerShipmentLinear) {
		this.distributionCostsPerShipmentLinear = distributionCostsPerShipmentLinear;
	}
	public double getCollectionCostsPerShipmentFixed() {
		return collectionCostsPerShipmentFixed;
	}
	public void setCollectionCostsPerShipmentFixed(
			double collectionCostsPerShipmentFixed) {
		this.collectionCostsPerShipmentFixed = collectionCostsPerShipmentFixed;
	}
	public double getCollectionCostsPerShipmentLinear() {
		return collectionCostsPerShipmentLinear;
	}
	public void setCollectionCostsPerShipmentLinear(
			double collectionCostsPerShipmentLinear) {
		this.collectionCostsPerShipmentLinear = collectionCostsPerShipmentLinear;
	}
	public HashMap<TSPDirection, Double> getTransshipmentCostsPerShipmentLinear() {
		return transshipmentCostsPerShipmentLinear;
	}
	public void setTransshipmentCostsPerShipmentLinear(
			HashMap<TSPDirection, Double> transshipmentCostsPerShipmentLinear) {
		this.transshipmentCostsPerShipmentLinear = transshipmentCostsPerShipmentLinear;
	}
	public HashMap<TSPDirection, Double> getTransshipmentCostsPerShipmentFixed() {
		return transshipmentCostsPerShipmentFixed;
	}
	public void setTransshipmentCostsPerShipmentFixed(HashMap<TSPDirection, Double> transshipmentCostsPerShipmentFixed) {
		this.transshipmentCostsPerShipmentFixed = transshipmentCostsPerShipmentFixed;
	}
	public HashMap<TSPDirection, Double> getMainRunCostsPerShipmentFixed() {
		return mainRunCostsPerShipmentFixed;
	}
	public void setMainRunCostsPerShipmentFixed(HashMap<TSPDirection, Double> mainRunCostsPerShipmentFixed) {
		this.mainRunCostsPerShipmentFixed = mainRunCostsPerShipmentFixed;
	}
	public HashMap<TSPDirection, Double> getMainRunCostsPerShipmentLinear() {
		return mainRunCostsPerShipmentLinear;
	}
	public void setMainRunCostsPerShipmentLinear(HashMap<TSPDirection, Double> mainRunCostsPerShipmentLinear) {
		this.mainRunCostsPerShipmentLinear = mainRunCostsPerShipmentLinear;
	}
}

