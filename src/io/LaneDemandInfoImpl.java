package io;

import java.util.ArrayList;

import demand.Flow;
import scenario.MarketLane;
import scenario.MarketScenario;
import scenario.Region;

public class LaneDemandInfoImpl implements LaneDemandInfo {

	private DirectionDemandInfo forwardDirectionInfo;
	private DirectionDemandInfo backwardDirectionInfo;
	private String LaneId;
	private Region firstRegion;
	private Region secondRegion;
	
	public LaneDemandInfoImpl (MarketLane lane){
		this.forwardDirectionInfo = new DirectionDemandInfoImpl(lane.getForwardDirection());
		this.backwardDirectionInfo = new DirectionDemandInfoImpl(lane.getBackwardDirection());
		this.LaneId = lane.getId();
		this.firstRegion = lane.getFirstRegion();
		this.secondRegion = lane.getSecondRegion();
	}

	public DirectionDemandInfo getForwardDirectionInfo() {
		return forwardDirectionInfo;
	}

	public DirectionDemandInfo getBackwardDirectionInfo() {
		return backwardDirectionInfo;
	}

	public String getLaneId() {
		return LaneId;
	}

	public Region getFirstRegion() {
		return firstRegion;
	}

	public Region getSecondRegion() {
		return secondRegion;
	}

	
		
	
	
	
	
	
}
