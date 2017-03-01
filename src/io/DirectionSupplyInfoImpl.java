package io;

import java.util.ArrayList;

import demand.Flow;
import scenario.MarketDirection;
import scenario.MarketLane;
import scenario.Region;
import supply.TSP;

public class DirectionSupplyInfoImpl implements DirectionSupplyInfo {
	private String marketDirectionId;
	private Region fromRegion;
	private Region toRegion;
	private ArrayList <DirectionTSPInfo> tspInfos;
	
	public DirectionSupplyInfoImpl (MarketLane lane, MarketDirection direction, DirectionDemandInfo demandInfo){
		this.marketDirectionId = direction.getId();
		this.fromRegion = direction.getFromRegion();
		this.toRegion = direction.getToRegion();
		this.tspInfos = new ArrayList <DirectionTSPInfo>();
		for(TSP tsp : lane.getTSPs()){
			DirectionTSPInfo info = new DirectionTSPInfoImpl(direction, tsp, demandInfo);
			tspInfos.add(info);
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

	public ArrayList<DirectionTSPInfo> getTSPInfos() {
		return tspInfos;
	}	
	
}
