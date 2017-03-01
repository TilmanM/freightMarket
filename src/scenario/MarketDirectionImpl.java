package scenario;

import java.util.Collection;

import demand.Flow;

public class MarketDirectionImpl implements MarketDirection {

	private String Id;
	private Region fromRegion;
	private Region toRegion;
	private Collection <Flow> flows;
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
	public Collection<Flow> getFlows() {
		return flows;
	}
	
	
	
}
