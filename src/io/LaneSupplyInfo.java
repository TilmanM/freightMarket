package io;

import scenario.Region;

public interface LaneSupplyInfo {

	public DirectionSupplyInfo getForwardDirectionInfo();

	public DirectionSupplyInfo getBackwardDirectionInfo();

	public String getLaneId();

	public Region getFirstRegion();


}
