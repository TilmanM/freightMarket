package supply;

import scenario.Region;


public class TSPTariffTableEntryImpl implements TSPTariffTableEntry {
	private String id;
	private Region fromRegion;
	private Region toRegion;
	private double constant;
	private double linear;
	
	public static String makeIdString(String fromRegionString, String toRegionString){
		return "Tariff from region " + fromRegionString + " Tariff to region "	+ toRegionString;
	}
	
		
	public TSPTariffTableEntryImpl (MarketTariffTableEntry entry){
		this.fromRegion = entry.getFromRegion();
		this.toRegion = entry.getToRegion();
		this.id = entry.getId();
		this.constant = entry.getConstant();
		this.linear = entry.getLinear();
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
