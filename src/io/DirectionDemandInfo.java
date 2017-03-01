package io;

import java.util.ArrayList;

import scenario.Region;

public interface DirectionDemandInfo {
	public String getMarketDirectionId();

	public Region getFromRegion();

	public Region getToRegion();

	public double getDirectionVolume();

	public ArrayList<FlowInfo> getFlowInfos();
	
}
