package demand;

import java.util.ArrayList;
import java.util.Collection;

import supply.LinearOffer;
import supply.TSP;

public interface TSPSelector {

	public TSP selectTSP(Collection<LinearOffer> offers);

	public ArrayList<LinearOffer> getOffers();
	
	public ArrayList<ProbabilityTuple> getTuples();
	
}