package io;

import java.util.ArrayList;

public interface DirectionTSPInfo {
	
	public String getId();

	public double getFixedPrice();

	public double getLinearPrice();

	public String getMarketDirectionId();

	public ArrayList<FlowInfo> getAssignedFlowInfos();

	public double getTotalVolume();

	public double getTotalCosts();
}
