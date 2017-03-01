package supply;

import scenario.Region;


public interface TSPTariffTableEntry {
	public String getId();
	
	public void setId(String id);
	
	public Region getFromRegion();
	
	public void setFromRegion(Region fromRegion);
	
	public Region getToRegion();
	
	public void setToRegion(Region toRegion);
	
	public double getConstant();
	
	public void setConstant(double constant);
	
	public double getLinear();
	
	public void setLinear(double linear);
}
