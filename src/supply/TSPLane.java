package supply;

import scenario.MarketLane;
import scenario.Region;

public interface TSPLane {

	public Region getFirstRegion();

	public void setFirstRegion(TSPRegion firstRegion);

	public Region getSecondRegion();

	public void setSecondRegion(TSPRegion secondRegion);
	
	public void createDirections();
	
	public TSPDirection getForwardDirection();

	public TSPDirection getBackwardDirection();
	
	public MarketLane getMarketLane();

	public void setMarketLane(MarketLane marketLane);

	public Facility getMainRunFacility();

	public void setMainRunFacility(Facility mainRunFacility);
	
	public Facility getFirstFacility();
	
	public Facility getSecondFacility();

}
