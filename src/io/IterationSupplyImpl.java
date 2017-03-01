package io;

import java.util.ArrayList;
import java.util.Collection;

import scenario.MarketLane;
import scenario.MarketScenario;

public class IterationSupplyImpl implements IterationSupply {
private ArrayList <LaneSupplyInfo> laneSupplyInfos;
	
	public IterationSupplyImpl(MarketScenario scenario, IterationDemand demand){
		this.laneSupplyInfos = new ArrayList<LaneSupplyInfo>();
		for(MarketLane lane : scenario.getMarketLanes()){
			for(LaneDemandInfo demandInfo : demand.getLaneDemandInfos()){
				if(demandInfo.getLaneId()==lane.getId()){
					LaneSupplyInfo laneInfo = new LaneSupplyInfoImpl(lane, demandInfo);
					laneSupplyInfos.add(laneInfo);
				}
			}			
		}
	}
	
	public Collection <LaneSupplyInfo> getLaneSupplyInfos(){
		return this.laneSupplyInfos;
	}
	
}
