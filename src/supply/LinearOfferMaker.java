package supply;

import demand.Shipment;

public class LinearOfferMaker implements OfferMaker {

	private TSP tsp;
	
	public LinearOfferMaker(TSP tsp){
		this.tsp = tsp;
	}
	
	public Offer makeOffer(Shipment shipment) {
		
			
			String fromRegionId = shipment.getFlow().getShipper().getRegion().getId();
			String toRegionId = shipment.getFlow().getRecipient().getRegion().getId();
			if(tsp.getTspRegions().get(fromRegionId).getFacility().capacityFree(shipment)
					&& tsp.getTspRegions().get(toRegionId).getFacility().capacityFree(shipment)){
			
			TSPTariffTableEntry entry = tsp.getTariffTable().getTariff(fromRegionId+toRegionId);
			
			LinearOffer offer = new LinearOffer(shipment);
			offer.setConstant(entry.getConstant());
			offer.setLinear(entry.getLinear());	
			offer.setTsp(tsp);
			return offer;		
			}
			else{
				return new DefaultOffer();
			}
	}

}
