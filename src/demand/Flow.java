
package demand;

import java.util.ArrayList;
import java.util.Collection;

import scenario.MarketLane;
import supply.LinearOffer;
import supply.TSP;

public interface Flow {

	public Company getShipper();

	public Company getRecipient();

	public double getStrength(); 
		
	public void setStrength(double strength);

	public void setShipper(Company shipper);

	public void setRecipient(Company recipient);
		
	public String getId();

	public void setId(String id);
		
	public MarketLane getMarketLane();
	
	public void setMarketLane(MarketLane marketLane);
	
	public DemandBehaviorParameters getParameters();

	public void setParameters(DemandBehaviorParameters parameters);
	
	public Shipment getShipment();

	public void setShipment(Shipment shipment);
	
	public TSP getPreviousTSP();

	public Collection<LinearOffer> getOffers();

	public OfferRequestor getOfferRequestor();
	
	public TSP selectTSP();
	
	public void requestOffers();
	
	public void assignShipment(TSP chosenOne);
	
	public TSPSelector getTspSelector();
	
	public TSP getCurrentTSP();
	
	public LinearOffer getChosenOffer();
}
