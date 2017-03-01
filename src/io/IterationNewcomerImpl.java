package io;

import java.util.ArrayList;
import java.util.Collection;

import scenario.MarketLane;
import scenario.MarketScenario;

public class IterationNewcomerImpl implements IterationNewcomer{
private ArrayList <LaneNewcomerInfo> laneNewcomerInfos;
	
	public IterationNewcomerImpl(MarketScenario scenario, IterationDemand demand){
		this.laneNewcomerInfos = new ArrayList<LaneNewcomerInfo>();
		for(MarketLane lane : scenario.getMarketLanes()){
			for(LaneDemandInfo demandInfo : demand.getLaneDemandInfos()){
				if(demandInfo.getLaneId()==lane.getId()){
					LaneNewcomerInfo laneInfo = new LaneNewcomerInfoImpl(lane, demandInfo);
					laneNewcomerInfos.add(laneInfo);
				}
			}			
		}
	}
	
	public Collection <LaneNewcomerInfo> getLaneNewcomerInfos(){
		return this.laneNewcomerInfos;
	}
	
}
