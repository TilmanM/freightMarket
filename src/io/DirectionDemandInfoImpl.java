package io;

import java.util.ArrayList;

import demand.Flow;
import scenario.MarketDirection;
import scenario.Region;

public class DirectionDemandInfoImpl implements DirectionDemandInfo {

	private String marketDirectionId;
	private Region fromRegion;
	private Region toRegion;
	private double directionVolume;
	private ArrayList <FlowInfo> flowInfos;
	
	public DirectionDemandInfoImpl (MarketDirection direction){
		this.marketDirectionId = direction.getId();
		this.fromRegion = direction.getFromRegion();
		this.toRegion = direction.getToRegion();
		this.flowInfos = new ArrayList <FlowInfo>();
		this.directionVolume = 0;
		for(Flow flow : direction.getFlows()){
			FlowInfo info = new FlowInfoImpl(flow);
			flowInfos.add(info);
			directionVolume = directionVolume + flow.getStrength();
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

	public double getDirectionVolume() {
		return directionVolume;
	}

	public ArrayList<FlowInfo> getFlowInfos() {
		return flowInfos;
	}
	
	
	
}
