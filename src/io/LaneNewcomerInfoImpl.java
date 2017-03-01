package io;

import scenario.MarketLane;
import scenario.Region;

public class LaneNewcomerInfoImpl implements LaneNewcomerInfo {

	private DirectionNewcomerInfo forwardDirectionInfo;
	private DirectionNewcomerInfo backwardDirectionInfo;
	private String LaneId;
	private Region firstRegion;
	private Region secondRegion;
	
	public LaneNewcomerInfoImpl (MarketLane lane, LaneDemandInfo demandInfo){
		this.forwardDirectionInfo = new DirectionNewcomerInfoImpl(lane,lane.getForwardDirection(), demandInfo.getForwardDirectionInfo());
		this.backwardDirectionInfo = new DirectionNewcomerInfoImpl(lane,lane.getBackwardDirection(), demandInfo.getBackwardDirectionInfo());
		this.LaneId = lane.getId();
		this.firstRegion = lane.getFirstRegion();
		this.secondRegion = lane.getSecondRegion();
	}

	public DirectionNewcomerInfo getForwardDirectionInfo() {
		return forwardDirectionInfo;
	}

	public DirectionNewcomerInfo getBackwardDirectionInfo() {
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
