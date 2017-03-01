package scenario;

import java.util.Collection;

import demand.Flow;

public interface MarketDirection {

	public String getId();
	
	public void setId(String id);
	
	public Region getFromRegion();
	
	public void setFromRegion(Region fromRegion);
	
	public Region getToRegion();
	
	public void setToRegion(Region toRegion);
	
	public Collection<Flow> getFlows();
}
	

