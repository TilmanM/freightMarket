package demand;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import controler.MarketControler;
import scenario.MarketLane;
import supply.LinearOffer;
import supply.Offer;
import supply.TSP;

public class FlowImpl implements Flow {

	private static final Logger logger = Logger.getLogger(FlowImpl.class.getName());
	
	private String id;
	private double strength;
	private Company shipper;
	private Company recipient;
	private TSP previousTSP;
	private TSP currentTSP;
	private LinearOffer chosenOffer;
	private Collection<LinearOffer> offers;
	
	private MarketLane marketLane;
	private DemandBehaviorParameters parameters;
	private Shipment shipment;
	private OfferRequestor offerRequestor;
    private TSPSelector tspSelector;
	
	public FlowImpl(){
		this.offerRequestor = new OfferRequestor(this);
		this.tspSelector = new TSPSelectorImpl();
		this.offers = new ArrayList<LinearOffer>();
	}
	
	@Override
	public Company getShipper() {
		
		return this.shipper;
	}

	@Override
	public Company getRecipient() {
		
		return this.recipient;
	}

	@Override
	public double getStrength() {
		
		return this.strength;
	}


	public void setStrength(double strength) {
		this.strength = strength;
		this.shipment = new ShipmentImpl();
		this.shipment.setFlow(this);
		this.shipment.setId("Shipment from " + this.id);
		this.shipment.setQuantity(strength);
	}

	public void setShipper(Company shipper) {
		this.shipper = shipper;
	}

	public void setRecipient(Company recipient) {
		this.recipient = recipient;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MarketLane getMarketLane() {
		return marketLane;
	}

	public void setMarketLane(MarketLane marketLane) {
		this.marketLane = marketLane;
	}

	public DemandBehaviorParameters getParameters() {
		return parameters;
	}

	public void setParameters(DemandBehaviorParameters parameters) {
		this.parameters = parameters;
	}
	
	public void requestOffers(){
		this.offers =  offerRequestor.requestOffers();
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public TSP getPreviousTSP() {
		return previousTSP;
	}

	public OfferRequestor getOfferRequestor() {
		return offerRequestor;
	}
	
	public TSP selectTSP(){
		logger.trace("Flow " + this.id + " selects TSPs from offers");
		TSP chosenOne = this.tspSelector.selectTSP(this.offers);
		for(LinearOffer offer : this.offers){
			if (offer.getTsp() == chosenOne){
				chosenOffer = offer;
				break;
			}
		}
		if(currentTSP == null){
			this.currentTSP = chosenOne;
		}
		else{
			this.previousTSP = this.currentTSP;
			this.currentTSP = chosenOne;
		}
		return chosenOne;
	}
	
	public void assignShipment(TSP chosenOne){
		logger.trace("Flow " + this.id + " assigns shipment to TSP " + chosenOne.getId());
		chosenOne.assignShipment(this.shipment);
	}

	public TSPSelector getTspSelector() {
		return tspSelector;
	}

    public Collection<LinearOffer> getOffers() {

		return offers;
	}

	public TSP getCurrentTSP() {
		return currentTSP;
	}

	public LinearOffer getChosenOffer() {
		return chosenOffer;
	}
	
    
	
	}
	
	
	
	
	
	

