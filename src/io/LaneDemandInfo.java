package io;

import scenario.Region;

public interface LaneDemandInfo {
	public DirectionDemandInfo getForwardDirectionInfo();

	public DirectionDemandInfo getBackwardDirectionInfo();

	public String getLaneId();

	public Region getFirstRegion();

	public Region getSecondRegion();

}
