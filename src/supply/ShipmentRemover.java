package supply;

import demand.Shipment;
import scenario.Region;

public class ShipmentRemover {

	private TSP tsp;
	
	public ShipmentRemover (TSP tsp){
		this.tsp = tsp;
	}
	
	public void removeShipments(){
		removeShipmentsFromFacilities();
		removeShipmentsFromTSPLanes();
	}

	private void removeShipmentsFromFacilities(){
		
		for(Facility facility : tsp.getFacilities()){
			facility.getUtilizationInformation().clearUtilization();
		}


	}

	private void removeShipmentsFromTSPLanes(){
		for(TSPLane lane : tsp.getLanes()){
			lane.getForwardDirection().clearUtilization();
			lane.getBackwardDirection().clearUtilization();
		}		
	}
	
	
}

