package supply;

import scenario.MarketLane;
import scenario.Region;



public class TSPLaneImpl implements TSPLane {

	private String id;
	private TSPRegion firstRegion;
	private TSPRegion secondRegion;
	private MarketLane marketLane;
	private TSPDirection forwardDirection;
	private TSPDirection backwardDirection;
	
	private Facility mainRunFacility;
	
	public TSPLaneImpl (String id){
		this.id = id;
	}

	public String getIdString(){
		return "TSP "+ this.id + "lane from: "+ firstRegion.getRegion().getId() + " to " +secondRegion.getRegion().getId();
	}
	
	public Region getFirstRegion() {
		return firstRegion.getRegion();
	}

	public void setFirstRegion(TSPRegion firstRegion) {
		this.firstRegion = firstRegion;
	}

	public Region getSecondRegion() {
		return secondRegion.getRegion();
	}

	public void setSecondRegion(TSPRegion secondRegion) {
		this.secondRegion = secondRegion;
	}
	
	public void createDirections(){
		this.forwardDirection = new TSPDirectionImpl(firstRegion.getRegion().getId()+secondRegion.getRegion().getId());
		forwardDirection.setFromRegion(firstRegion);
		forwardDirection.setToRegion(secondRegion);
		this.backwardDirection = new TSPDirectionImpl(secondRegion.getRegion().getId()+firstRegion.getRegion().getId());
		backwardDirection.setFromRegion(secondRegion);
		backwardDirection.setToRegion(firstRegion);
	}

	public TSPDirection getForwardDirection() {
		return forwardDirection;
	}

	public TSPDirection getBackwardDirection() {
		return backwardDirection;
	}

	public MarketLane getMarketLane() {
		return marketLane;
	}

	public void setMarketLane(MarketLane marketLane) {
		this.marketLane = marketLane;
	}

	public Facility getMainRunFacility() {
		return mainRunFacility;
	}

	public void setMainRunFacility(Facility mainRunFacility) {
		this.mainRunFacility = mainRunFacility;
	}


	public Facility getFirstFacility() {
		return firstRegion.getFacility();
	}


	public Facility getSecondFacility() {
		return secondRegion.getFacility();
	}

	
	
}
