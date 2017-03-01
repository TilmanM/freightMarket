package supply;

import demand.Shipment;

public class LinearOffer implements Offer{ 

	private Shipment shipment;
	private double constant;
	private double linear;
	private static final String type = "LINEAR OFFER";
	private double normalizedPrice;
	private TSP tsp;
	
	public Shipment getShipment() {
		return shipment;
	}
	public LinearOffer(Shipment shipment) {
		this.shipment = shipment;

	}
	public double getConstant() {
		return constant;
	}
	public void setConstant(double constant) {
		this.constant = constant;

	}
	public double getLinear() {
		return linear;
	}
	public void setLinear(double linear) {
		this.linear = linear;
	}
	
	public String getType() {		
		return this.type;
	}
	
	public double getPrice(){
		return this.constant + this.linear * this.shipment.getQuantity();
	}
	
	public void setNormalizedPrice(double referencePrice){
		this.normalizedPrice = this.getPrice()/referencePrice;
	}
	
	public double getNormalizedPrice(){
		return this.normalizedPrice;
	}
	public TSP getTsp() {
		return tsp;
	}
	public void setTsp(TSP tsp) {
		this.tsp = tsp;
	}

	
}
