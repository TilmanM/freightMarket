package scenario;

import java.util.ArrayList;
import java.util.Collection;



public class MarketLaneInfo{
	private ArrayList<MarketLaneInfoElement> infoElementsList;
	private MarketLane marketLane;
	
	public MarketLaneInfo(MarketLane marketLane){
		this.infoElementsList = new ArrayList<MarketLaneInfoElement>();
		this.marketLane = marketLane;
		fillElementsList();
	}
	
	private void fillElementsList(){
		

		LaneInfoElementValue flowsOnLaneValue = new LaneInfoElementValue(marketLane.getFlows().size()); 
		MarketLaneInfoElement flowsOnLane = new MarketLaneInfoElement(loggedCategory.FLOWS,flowsOnLaneValue);
		infoElementsList.add(flowsOnLane);
	
		LaneInfoElementValue tspsOnLaneValue = new LaneInfoElementValue(marketLane.getTSPs().size()); 
		MarketLaneInfoElement tspsOnLane = new MarketLaneInfoElement(loggedCategory.TSPs,tspsOnLaneValue);
		infoElementsList.add(tspsOnLane);
		
		LaneInfoElementValue laneOpenValue = new LaneInfoElementValue(marketLane.isOpen()); 
		MarketLaneInfoElement laneOpen= new MarketLaneInfoElement(loggedCategory.OPEN, laneOpenValue);
		infoElementsList.add(laneOpen);
		
	}
	
	public Collection<MarketLaneInfoElement> getInfoElements(){
		 return infoElementsList;
	}

	public MarketLane getMarketLane() {
		return marketLane;
	}
	
	
}	