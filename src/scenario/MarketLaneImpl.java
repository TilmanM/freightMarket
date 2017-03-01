package scenario;

import java.util.ArrayList;
import java.util.Collection;

import demand.Company;
import demand.Flow;
import supply.TSP;

public class MarketLaneImpl implements MarketLane {

	private String Id;
	private Region firstRegion;
	private Region secondRegion;
	
	private Collection<Flow> flows;
	private Collection<TSP> TSPs;
	private MarketDirection forwardDirection;
	private MarketDirection backwardDirection;
	private TSP newcomer;
	
	private boolean isOpen;	
	
	public MarketLaneImpl(){
		this.flows = new ArrayList<Flow>();
		this.isOpen = true;
		this.TSPs = new ArrayList<TSP>();
		this.forwardDirection = new MarketDirectionImpl();
		this.backwardDirection = new MarketDirectionImpl();
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Region getFirstRegion() {
		return firstRegion;
	}
	public void setFirstRegion(Region firstRegion) {
		this.firstRegion = firstRegion;
	}
	public Region getSecondRegion() {
		return secondRegion;
	}
	public void setSecondRegion(Region secondRegion) {
		this.secondRegion = secondRegion;
	}
	public Collection<Flow> getFlows() {
		return flows;
	}
	public void closeLane(){
		this.isOpen=false;
	}
	public boolean isOpen(){
		return this.isOpen;
	}

	public TSP getNewcomer() {
		return newcomer;
	}

	public void setNewcomer(TSP newcomer) {
		this.newcomer = newcomer;
	}
	
	public void removeNewcomer(){
		this.newcomer=null;
	}

	public Collection<TSP> getTSPs() {
		return TSPs;
	}

	public MarketDirection getForwardDirection() {
		return forwardDirection;
	}

	public MarketDirection getBackwardDirection() {
		return backwardDirection;
	}
	
	
}
