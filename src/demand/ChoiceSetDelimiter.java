package demand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import supply.LinearOffer;

public class ChoiceSetDelimiter{
	
	private LinearOfferComparator offerComparator;
	private Flow flow;
	
	
	public ChoiceSetDelimiter(Flow flow){
		this.flow = flow;
		this.offerComparator = new LinearOfferComparator();
	}
	
	private double getLowestPrice(Collection<LinearOffer> offers){
		/*if(offers.size()==1){
			LinearOffer soleOffer  = offers.iterator().next();
			return soleOffer.getPrice();
		}
		else{*/
			ArrayList <LinearOffer> sortedOfferList = new ArrayList<LinearOffer>(offers);
			Collections.sort(sortedOfferList, offerComparator);
			LinearOffer lowestOffer = sortedOfferList.get(0);
			return lowestOffer.getConstant() + lowestOffer.getLinear()*lowestOffer.getShipment().getQuantity();
		//}	
	}

	
	private ArrayList<LinearOffer> getNormalizedOffers(ArrayList<LinearOffer> offers){
		double lowestPrice = getLowestPrice(offers);
		for(LinearOffer offer : offers){
			offer.setNormalizedPrice(lowestPrice);
		}
		return offers;
	}
	
	public boolean choiceSetIsLargeEnough (ArrayList<LinearOffer> choiceSet){
		Collections.sort(choiceSet, offerComparator);
		if(choiceSet.size()==0){
			return false;
		}
		if(choiceSet.size()>1){
			getNormalizedOffers(choiceSet);
			ArrayList<LinearOffer>previousChoiceSet = new ArrayList<LinearOffer>(choiceSet.subList(0, (choiceSet.size()-1)));
			double EMC = getEMC(getNormalizedOffers(choiceSet));
			double previousEMC = getEMC(getNormalizedOffers(previousChoiceSet));
		
			if(((previousEMC-EMC)/previousEMC) < flow.getParameters().getDeltaEMC()){
				return true;
			}
			else{
				return false;
			}
		}
		if (choiceSet.size()==1){
			choiceSet.get(0).setNormalizedPrice(choiceSet.get(0).getPrice());
			return false;
		}
		else{
			return false;
		}
	}
	
	public double getEMC(ArrayList<LinearOffer> choiceSet){
		double sumOfExponentials = 0;
		for(LinearOffer offer: choiceSet){
			sumOfExponentials = sumOfExponentials + Math.exp(flow.getParameters().getAlpha() * offer.getNormalizedPrice()) ;
			
		}
		
		sumOfExponentials = (1/flow.getParameters().getAlpha()) * Math.log(sumOfExponentials);
	
		return sumOfExponentials;
	}
}
