package io;

import java.util.ArrayList;

import demand.Shipment;
import scenario.MarketDirection;
import scenario.Region;
import supply.TSP;
import supply.TSPLane;
import supply.TSPType;

public class DirectionTSPInfoImpl implements DirectionTSPInfo {
	private String id;
	private double fixedPrice;
	private double linearPrice;
	private String marketDirectionId;
	private ArrayList <FlowInfo> assignedFlowInfos;
	private double totalVolume; 
	private double totalCosts;
	
	public DirectionTSPInfoImpl(MarketDirection direction, TSP tsp, DirectionDemandInfo demandInfo){
		this.id = tsp.getId();
		this.marketDirectionId = direction.getId();
		this.fixedPrice = tsp.getCostMap().get(marketDirectionId).getConstant();
		this.linearPrice = tsp.getCostMap().get(marketDirectionId).getLinear();
		this.assignedFlowInfos = new ArrayList <FlowInfo>();
		for(TSPLane lane : tsp.getLanes()){
			if(lane.getMarketLane().getForwardDirection() == direction){
				for(Shipment shipment : lane.getForwardDirection().getFromFacility().getUtilizationInformation().getOutgoingShipments()){
					if(shipment.getFlow().getShipper().getRegion() == direction.getFromRegion()){
						for(FlowInfo flowInfo: demandInfo.getFlowInfos()){
							if(flowInfo.getId()==shipment.getFlow().getId()){
								if(shipment.getFlow().getCurrentTSP()==null){
									flowInfo.setCurrentTSP(this);
								}
								else{
									flowInfo.setPreviousTSP(flowInfo.getCurrentTSP());
									flowInfo.setCurrentTSP(this);
								}
								assignedFlowInfos.add(flowInfo);
							}
						}
					}
				}
			}
			if(lane.getMarketLane().getBackwardDirection() == direction){
				for(Shipment shipment : lane.getForwardDirection().getFromFacility().getUtilizationInformation().getOutgoingShipments()){
					if(shipment.getFlow().getShipper().getRegion() == direction.getFromRegion()){
						for(FlowInfo flowInfo: demandInfo.getFlowInfos()){
							if(flowInfo.getId()==shipment.getFlow().getId()){
								assignedFlowInfos.add(flowInfo);
							}
						}
					}
				}
			}
		
		}
		calculateTotalVolume();
		calculateTotalCosts();
	}
	
	private double calculateTotalVolume(){
		double totalVolume = 0;
		for(FlowInfo info : assignedFlowInfos){
			totalVolume = totalVolume + info.getShipment().getQuantity();
		}
		return totalVolume;
	}
	
	private double calculateTotalCosts(){
		double totalCosts = 0;
		for(FlowInfo info : assignedFlowInfos){
			totalCosts = totalCosts + info.getPricePaid();
		}
		return totalCosts;
	}

	public String getId() {
		return id;
	}

	public double getFixedPrice() {
		return fixedPrice;
	}

	public double getLinearPrice() {
		return linearPrice;
	}

	public String getMarketDirectionId() {
		return marketDirectionId;
	}

	public ArrayList<FlowInfo> getAssignedFlowInfos() {
		return assignedFlowInfos;
	}

	public double getTotalVolume() {
		return totalVolume;
	}

	public double getTotalCosts() {
		return totalCosts;
	}
	
}
