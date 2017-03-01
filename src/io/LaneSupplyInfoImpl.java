package io;

import scenario.MarketLane;
import scenario.Region;

public class LaneSupplyInfoImpl implements LaneSupplyInfo {
	
	private DirectionSupplyInfo forwardDirectionInfo;
	private DirectionSupplyInfo backwardDirectionInfo;
	private String LaneId;
	private Region firstRegion;
	private Region secondRegion;
	
	public LaneSupplyInfoImpl (MarketLane lane, LaneDemandInfo demandInfo){
		this.forwardDirectionInfo = new DirectionSupplyInfoImpl(lane,lane.getForwardDirection(), demandInfo.getForwardDirectionInfo());
		this.backwardDirectionInfo = new DirectionSupplyInfoImpl(lane,lane.getBackwardDirection(), demandInfo.getBackwardDirectionInfo());
		this.LaneId = lane.getId();
		this.firstRegion = lane.getFirstRegion();
		this.secondRegion = lane.getSecondRegion();
	}

	public DirectionSupplyInfo getForwardDirectionInfo() {
		return forwardDirectionInfo;
	}

	public DirectionSupplyInfo getBackwardDirectionInfo() {
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
