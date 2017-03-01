package io;

import java.util.ArrayList;

import scenario.Region;

public interface DirectionNewcomerInfo {

	public String getMarketDirectionId();

	public Region getFromRegion();

	public Region getToRegion();

	public DirectionTSPInfo getNewcomerInfo();
	
	
}

