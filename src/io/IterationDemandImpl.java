package io;

import java.util.ArrayList;
import java.util.Collection;

import scenario.MarketLane;
import scenario.MarketScenario;

public class IterationDemandImpl implements IterationDemand {
	
	private ArrayList <LaneDemandInfo> laneDemandInfos;
	
	public IterationDemandImpl(MarketScenario scenario){
		this.laneDemandInfos = new ArrayList<LaneDemandInfo>();
		for(MarketLane lane : scenario.getMarketLanes()){
			LaneDemandInfo laneFlowInfo = new LaneDemandInfoImpl(lane);
			laneDemandInfos.add(laneFlowInfo);
		}
	}
	
	public Collection <LaneDemandInfo> getLaneDemandInfos(){
		return this.laneDemandInfos;
	}
	
}
