package io;

import java.util.ArrayList;
import java.util.Collection;

import demand.Company;
import demand.DemandBehaviorParameters;
import demand.DemandBehaviorParametersImpl;
import demand.Flow;
import supply.LinearOffer;
import supply.TSP;


public class FlowInfoImpl implements FlowInfo {
	private String id;
	private DemandBehaviorParameters parameters;
	private ShipmentInfo shipmentInfo;
	private double strength;
	private Company shipper;
	private Company recipient;
	private Collection<LinearOffer> offers;
	private DirectionTSPInfo currentTSP;
	private DirectionTSPInfo previousTSP;
	private double pricePaid;
	
	public FlowInfoImpl(Flow flow){
		this.id = flow.getId();
		this.parameters = new DemandBehaviorParametersImpl();
		parameters.setAlpha(flow.getParameters().getAlpha());
		parameters.setDeltaEMC(flow.getParameters().getDeltaEMC());
		this.shipmentInfo = new ShipmentInfoImpl(flow.getShipment());
		this.strength = flow.getStrength();
		this.shipper= flow.getShipper();
		this.recipient = flow.getRecipient();
		this.offers = new ArrayList<LinearOffer>();
		for(LinearOffer offer : flow.getOffers()){
			this.offers.add(offer);
		}	
		this.pricePaid = flow.getChosenOffer().getPrice();
	}
	
		
	public String getId() {
		return id;
	}
	public DemandBehaviorParameters getParameters() {
		return parameters;
	}
	public ShipmentInfo getShipment() {
		return shipmentInfo;
	}
	public double getStrength() {
		return strength;
	}
	public Company getShipper() {
		return shipper;
	}
	public Company getRecipient() {
		return recipient;
	}
	public Collection<LinearOffer> getOffers() {
		return offers;
	}
	public DirectionTSPInfo getCurrentTSP() {
		return currentTSP;
	}
	public DirectionTSPInfo getPreviousTSP() {
		return previousTSP;
	}
	public double getPricePaid() {
		return pricePaid;
	}	
	public void setPreviousTSP(DirectionTSPInfo previousTSP){
		this.previousTSP = previousTSP;
	}
	public void setCurrentTSP(DirectionTSPInfo currentTSP){
		this.currentTSP = currentTSP;
	}
}
