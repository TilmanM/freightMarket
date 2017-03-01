package supply;

public interface FacilityType {
	
	public String getFacilityType();
	
	public void setFacilityType(String facilityType);
	
	public String getName();
	
	public void setName(String name);
	
	public long getCapacity();
	
	public void setCapacity(long capacity);
	
	public double getFixed();
	
	public void setFixed(double fixed);
	
	public double getLinear();
	
	public void setLinear(double linear);
	
	public long getIncrement();
	
	public void setIncrement(long increment);
	
	public void setCapacityCosts(double capacityCosts);
	
	public double  getCapacityCosts();
	
	public void setIncrementCosts(double incrementCosts);
	
	public double  getIncrementCosts();

	
}

