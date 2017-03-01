package demand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;

import controler.MarketControler;
import supply.LinearOffer;
import supply.TSP;
import supply.TSPImpl;

public class TSPSelectorImpl implements TSPSelector {

	private static final Logger logger = Logger.getLogger(TSPSelectorImpl.class.getName());
	
	private ArrayList<LinearOffer> offers;
	private ProbabilityTupleComparator comparator;
	private ArrayList<ProbabilityTuple> tuples;
	private LinearOfferComparator offerComparator;
	
	public TSPSelectorImpl(){
		this.offers = new ArrayList<LinearOffer>();
		this.comparator = new ProbabilityTupleComparator();
		this.offerComparator = new LinearOfferComparator();
	}
	
	public TSP selectTSP(Collection<LinearOffer> offers){
		TSP chosenOne = new TSPImpl();
		this.offers = (ArrayList<LinearOffer>) offers;
		logger.trace("Start choosing TSP from " + offers.size() + " offers");
		tuples = new ArrayList<ProbabilityTuple>(calculateChoiceProbabilities());
		Collections.sort(tuples, comparator);
		double choiceProbability = MarketControler.simulationRandom.nextDouble();
		double cumulativeProbability = 0;
		for(ProbabilityTuple tuple: tuples){
			cumulativeProbability = cumulativeProbability + tuple.getChoiceProbability();
			if(cumulativeProbability > choiceProbability){
				chosenOne = tuple.getOffer().getTsp();
				break;
			}
		}
		logger.trace("TSP " + chosenOne.getId() + " selected from offers");
		return chosenOne;
	}
	
	private ArrayList<LinearOffer> getNormalizedOffers(ArrayList<LinearOffer> offers){
		double lowestPrice = getLowestPrice(offers);
		for(LinearOffer offer : offers){
			offer.setNormalizedPrice(lowestPrice);
		}
		return offers;
	}
	
	private double getLowestPrice(Collection<LinearOffer> offers){
		
			ArrayList <LinearOffer> sortedOfferList = new ArrayList<LinearOffer>(offers);
			Collections.sort(sortedOfferList, offerComparator);
			LinearOffer lowestOffer = sortedOfferList.get(0);
			return lowestOffer.getConstant() + lowestOffer.getLinear()*lowestOffer.getShipment().getQuantity();
		
	}
	
	public Collection<ProbabilityTuple> calculateChoiceProbabilities(){
		ArrayList<ProbabilityTuple> probabilities = new ArrayList<ProbabilityTuple>();
		double logitDenominator = 0;
		getNormalizedOffers(offers);
		for(LinearOffer offer : offers){
			ProbabilityTuple tuple = new ProbabilityTuple(offer);
			double utility = offer.getShipment().getFlow().getParameters().getAlpha() * offer.getNormalizedPrice();
			tuple.setUtility(utility);
			logitDenominator = logitDenominator + Math.exp(utility);
			probabilities.add(tuple);
		}
		for(ProbabilityTuple tuple : probabilities){
			tuple.setChoiceProbability(Math.exp(tuple.getUtility())/logitDenominator);
			logger.trace("TSP " + tuple.getOffer().getTsp().getId() + " charges " + tuple.getOffer().getPrice() + 
					" and is chosen with probability " + tuple.getChoiceProbability());
		}
		
		return probabilities;
	}

	public ArrayList<LinearOffer> getOffers() {
		return offers;
	}

	public ArrayList<ProbabilityTuple> getTuples() {
		return tuples;
	}

    
   
	

}
