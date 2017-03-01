package supply;

import java.util.ArrayList;
import java.util.Collection;

import demand.Shipment;

public class FacilityUtilizationInformation {

	private Facility facility;
	
	private int numberOfIncomingFlows;
	private int numberOfOutgoingFlows;
	
	private double volumeOfIncomingFlows;
	private double volumeOfOutgoingFlows;
	
	private Collection <Shipment> incomingShipments;
	private Collection <Shipment> outgoingShipments;
	
	private Collection <TSPLane> lanes;
	
	public FacilityUtilizationInformation(Facility facility){
		this.facility = facility;
		this.incomingShipments = new ArrayList<Shipment>();
		this.outgoingShipments = new ArrayList<Shipment>();
		this.lanes = new ArrayList<TSPLane>();
	}
	
	public void clearUtilization(){
		this.incomingShipments.clear();
		this.outgoingShipments.clear();
		this.numberOfIncomingFlows = 0;
		this.numberOfOutgoingFlows = 0;
		this.volumeOfIncomingFlows = 0;
		this.volumeOfOutgoingFlows = 0;
	}

	public int getNumberOfIncomingFlows() {
		return numberOfIncomingFlows;
	}

	public int getNumberOfOutgoingFlows() {
		return numberOfOutgoingFlows;
	}

	public double getVolumeOfIncomingFlows() {
		return volumeOfIncomingFlows;
	}

	public double getVolumeOfOutgoingFlows() {
		return volumeOfOutgoingFlows;
	}

	public Collection<Shipment> getIncomingShipments() {
		return incomingShipments;
	}

	public Collection<Shipment> getOutgoingShipments() {
		return outgoingShipments;
	}

	public Collection<TSPLane> getLanes() {
		return lanes;
	}

	public void addShipment(Shipment shipment){
		if(shipment.getFlow().getRecipient().getRegion().equals(facility.getRegion())){
			incomingShipments.add(shipment);
			numberOfIncomingFlows++;
			volumeOfIncomingFlows = volumeOfIncomingFlows + shipment.getQuantity();
		}
		else if(shipment.getFlow().getShipper().getRegion().equals(facility.getRegion())){
			outgoingShipments.add(shipment);
			numberOfOutgoingFlows++;
			volumeOfOutgoingFlows = volumeOfOutgoingFlows + shipment.getQuantity();
		}
		else {
			
		}
	}

	public boolean capacityFree(Shipment shipment){
		if(shipment.getFlow().getRecipient().getRegion().equals(facility.getRegion())){
			if(volumeOfIncomingFlows + shipment.getQuantity() < facility.getCapacity()){
				return true;
			}
			else{
				return false;
			}
		}
		else if(shipment.getFlow().getShipper().getRegion().equals(facility.getRegion())){
			if(volumeOfOutgoingFlows + shipment.getQuantity() < facility.getCapacity()){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
}

