package io;

import java.util.Collection;

import demand.Company;
import demand.DemandBehaviorParameters;
import supply.LinearOffer;

public interface FlowInfo {

	public String getId();
	
	public DemandBehaviorParameters getParameters();
	
	public ShipmentInfo getShipment();
	
	public double getStrength();
	
	public Company getShipper();
	
	public Company getRecipient();
	
	public Collection<LinearOffer> getOffers();
	
	public DirectionTSPInfo getCurrentTSP();
	
	public DirectionTSPInfo getPreviousTSP();
	
	public double getPricePaid();
	
	public void setCurrentTSP(DirectionTSPInfo currentTSP);
	
	public void setPreviousTSP( DirectionTSPInfo previousTSP);
}

