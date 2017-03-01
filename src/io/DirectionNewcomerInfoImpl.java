package io;

import java.util.ArrayList;

import scenario.MarketDirection;
import scenario.MarketLane;
import scenario.Region;
import supply.TSP;

public class DirectionNewcomerInfoImpl implements DirectionNewcomerInfo {

	private String marketDirectionId;
	private Region fromRegion;
	private Region toRegion;
	private DirectionTSPInfo newcomerInfo;
	
	public DirectionNewcomerInfoImpl (MarketLane lane, MarketDirection direction, DirectionDemandInfo demandInfo){
		this.marketDirectionId = direction.getId();
		this.fromRegion = direction.getFromRegion();
		this.toRegion = direction.getToRegion();
		if(lane.getNewcomer() != null){
			this.newcomerInfo = new DirectionTSPInfoImpl(direction, lane.getNewcomer(), demandInfo);
		}
	}

	public String getMarketDirectionId() {
		return marketDirectionId;
	}

	public Region getFromRegion() {
		return fromRegion;
	}

	public Region getToRegion() {
		return toRegion;
	}

	public DirectionTSPInfo getNewcomerInfo() {
		return newcomerInfo;
	}	
	
}
