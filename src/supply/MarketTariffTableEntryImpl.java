package supply;

import scenario.Region;


public class MarketTariffTableEntryImpl implements MarketTariffTableEntry {

	private String id;
	private Region fromRegion;
	private Region toRegion;
	private double constant;
	private double linear;
	
		
	public String getIdString(MarketTariffTableEntryImpl entry){
		return "Tariff from region " + entry.getFromRegion().getId() + " Tariff to region "	+ entry.getToRegion().getId();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Region getFromRegion() {
		return fromRegion;
	}
	public void setFromRegion(Region fromRegion) {
		this.fromRegion = fromRegion;
	}
	public Region getToRegion() {
		return toRegion;
	}
	public void setToRegion(Region toRegion) {
		this.toRegion = toRegion;
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
	
	
	
}
