
package scenario;

import java.util.Collection;

import demand.Flow;
import supply.TSP;

public interface MarketLane {

	public String getId();
	
	public void setId(String id);
	
	public Region getFirstRegion();
	
	public void setFirstRegion(Region firstRegion);
	
	public Region getSecondRegion();
	
	public void setSecondRegion(Region secondRegion);

	public Collection<Flow> getFlows();
	
	public void closeLane();
	
	public boolean isOpen();
	
	public TSP getNewcomer();

	public void setNewcomer(TSP newcomer);
	
	public void removeNewcomer();

	public Collection<TSP> getTSPs();
	
	public MarketDirection getForwardDirection();
	
	public MarketDirection getBackwardDirection();
	
}

