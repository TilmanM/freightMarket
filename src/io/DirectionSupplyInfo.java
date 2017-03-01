package io;

import java.util.ArrayList;

import scenario.Region;

public interface DirectionSupplyInfo {

	public String getMarketDirectionId();

	public Region getFromRegion();

	public Region getToRegion();

	public ArrayList<DirectionTSPInfo> getTSPInfos();
	
}

