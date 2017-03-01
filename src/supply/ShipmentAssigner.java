package supply;

import org.apache.log4j.Logger;

import controler.MarketControler;
import demand.Shipment;
import scenario.Region;

public class ShipmentAssigner {

	private static final Logger logger = Logger.getLogger(ShipmentAssigner.class.getName());
	
	private TSP tsp;
	
	public ShipmentAssigner (TSP tsp){
		this.tsp = tsp;
	}
	
	public void assignShipment(Shipment shipment){
		logger.trace("Start assigning shipment " + shipment.getId() + " to TSP " + tsp.getId());
		AssignShipmentToFacility(shipment);
		AssignShipmentToTSPLane(shipment);
		logger.trace("Shipment " + shipment.getId() + " assigned to TSP " + tsp.getId());
	}

	private void AssignShipmentToFacility(Shipment shipment){
		Region fromRegion = shipment.getFlow().getShipper().getRegion();
		Region toRegion = shipment.getFlow().getRecipient().getRegion();
		
		Facility fromFacility = tsp.getTspRegions().get(fromRegion.getId()).getFacility();
		logger.trace("Shipment departs from facility  " + fromFacility.getId() + " of TSP " + tsp.getId());
		Facility toFacility = tsp.getTspRegions().get(toRegion.getId()).getFacility();
		logger.trace("Shipment ends at facility  " + toFacility.getId() + " of TSP " + tsp.getId());
		
		logger.trace("Facility  " + fromFacility.getId() + " was utilized with " + fromFacility.getUtilizationInformation().getVolumeOfOutgoingFlows() + " before");
		logger.trace("Facility  " + fromFacility.getId() + " had " + fromFacility.getUtilizationInformation().getNumberOfOutgoingFlows() + " outgoing flows before");
		fromFacility.getUtilizationInformation().addShipment(shipment);
		logger.trace("Facility  " + fromFacility.getId() + " is now utilized with " + fromFacility.getUtilizationInformation().getVolumeOfOutgoingFlows());
		logger.trace("Facility  " + fromFacility.getId() + " has " + fromFacility.getUtilizationInformation().getNumberOfOutgoingFlows() + " outgoing flows now");
		
	
		logger.trace("Facility  " + toFacility.getId() + " was utilized with " + toFacility.getUtilizationInformation().getVolumeOfIncomingFlows() + " before");
		logger.trace("Facility  " + fromFacility.getId() + " had " + fromFacility.getUtilizationInformation().getVolumeOfIncomingFlows() + " incoming flows before");
		toFacility.getUtilizationInformation().addShipment(shipment);
		logger.trace("Facility  " + toFacility.getId() + " is now utilized with " + toFacility.getUtilizationInformation().getVolumeOfIncomingFlows());
		logger.trace("Facility  " + fromFacility.getId() + " was utilized with " + fromFacility.getUtilizationInformation().getVolumeOfOutgoingFlows() + " before");
		
	}

	private void AssignShipmentToTSPLane(Shipment shipment){
		for(TSPLane lane : tsp.getLanes()){
			if(lane.getMarketLane().equals(shipment.getFlow().getMarketLane())){
				Region forwardFrom = lane.getForwardDirection().getFromRegion();
				if(forwardFrom == shipment.getFlow().getShipper().getRegion()){
					lane.getForwardDirection().addShipment(shipment);
					logger.trace("Shipment assigned to direction  " + lane.getForwardDirection().getId() + " of lane " + lane.getMarketLane().getId());
				}
				else{
					lane.getBackwardDirection().addShipment(shipment);
					logger.trace("Shipment assigned to direction  " + lane.getBackwardDirection().getId() + " of lane " + lane.getMarketLane().getId());
				}
			}
		}
	}
	
	
}

