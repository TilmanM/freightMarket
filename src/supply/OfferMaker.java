package supply;

import demand.Shipment;

public interface OfferMaker {

	public Offer makeOffer(Shipment shipment);
}
