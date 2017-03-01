package demand;

import java.util.Comparator;

import supply.LinearOffer;

public class LinearOfferComparator implements Comparator<LinearOffer> {

	@Override
	public int compare(LinearOffer arg0, LinearOffer arg1) {
		Shipment shipment = arg0.getShipment();
		double arg0Price = arg0.getLinear()*shipment.getQuantity() + arg0.getConstant();
		double arg1Price = arg1.getLinear()*shipment.getQuantity() + arg1.getConstant();
		
		if((arg0Price -arg1Price) < 0){
			return -1;
			
		}
		
		if((arg0Price -arg1Price) > 0){
			return 1;
			
		}
		else{
			return 0;
		}
		
	}

}
