
package demand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import controler.MarketControler;
import supply.LinearOffer;
import supply.Offer;
import supply.TSP;

public class OfferRequestor {

	private Flow flow;
	private ChoiceSetDelimiter delimiter;
	private ArrayList <LinearOffer> offers; 
	
	
	
	public OfferRequestor(Flow flow){
		this.flow = flow;
		this.delimiter = new ChoiceSetDelimiter(flow);
		offers= new ArrayList<LinearOffer>();
	}
	
	public Collection<LinearOffer> requestOffers(){
		offers.clear();
		ArrayList<TSP> supply = new ArrayList<TSP>(flow.getMarketLane().getTSPs());
		
		if(flow.getPreviousTSP()!=null && supply.contains(flow.getPreviousTSP())){
			supply.remove(flow.getPreviousTSP());
			Offer previousOffer = flow.getPreviousTSP().makeOffer(flow.getShipment());
			if(previousOffer.getType().equals("LINEAR OFFER")){			
				offers.add((LinearOffer)previousOffer);

			}
		}	
		while(!delimiter.choiceSetIsLargeEnough(offers) && offers.size()<flow.getMarketLane().getTSPs().size()){
				Collections.shuffle(supply, MarketControler.simulationRandom);
				TSP tsp = supply.get(0);
				supply.remove(tsp);
				Offer tspOffer = tsp.makeOffer(flow.getShipment());
				if(tspOffer.getType().equals("LINEAR OFFER")){

					offers.add((LinearOffer)tspOffer);
				}
		}
		if(!delimiter.choiceSetIsLargeEnough(offers) && flow.getMarketLane().getNewcomer()!=null){
			Offer newcomerOffer = flow.getMarketLane().getNewcomer().makeOffer(flow.getShipment());
			if(newcomerOffer.getType().equals("LINEAR OFFER")){
				offers.add((LinearOffer)newcomerOffer);
			}	
		}
		
		
		return offers;
	}

	public ChoiceSetDelimiter getDelimiter() {
		return delimiter;
	}
 
	
	
}
