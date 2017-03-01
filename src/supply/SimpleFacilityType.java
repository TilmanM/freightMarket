package supply;

public class SimpleFacilityType implements FacilityType {

	private String facilityType;
	private String name;
	private long capacity;
	private double fixed;
	private double linear;
	private long increment;
	private double capacityCosts;
	private double incrementCosts;
	
	public SimpleFacilityType(){
		this.linear = 0;
	}
	
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	public long getCapacity() {
		return capacity;
	}
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	public double getFixed() {
		return fixed;
	}
	public void setFixed(double fixed) {
		this.fixed = fixed;
	}
	public double getLinear() {
		return linear;
	}
	public void setLinear(double linear) {
		this.linear = linear;
	}
	public long getIncrement() {
		return increment;
	}
	public void setIncrement(long increment) {
		this.increment = increment;
	}

	public double getCapacityCosts() {
		return capacityCosts;
	}

	public void setCapacityCosts(double capacityCosts) {
		this.capacityCosts = capacityCosts;
	}

	public double getIncrementCosts() {
		return incrementCosts;
	}

	public void setIncrementCosts(double incrementCosts) {
		this.incrementCosts = incrementCosts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

