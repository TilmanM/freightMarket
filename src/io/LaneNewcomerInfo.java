package io;

import scenario.Region;

public interface LaneNewcomerInfo {

	public DirectionNewcomerInfo getForwardDirectionInfo();

	public DirectionNewcomerInfo getBackwardDirectionInfo();

	public String getLaneId();

	public Region getFirstRegion();

}
