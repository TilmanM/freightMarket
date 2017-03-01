package supply;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import controler.MarketControler;
import demand.Shipment;
import scenario.Region;



public class TSPDirectionImpl implements TSPDirection {

	private static final Logger logger = Logger.getLogger(TSPDirectionImpl.class.getName());
	
	private String id;
	private TSPRegion fromRegion;
	private TSPRegion toRegion;
	private Collection<Shipment> shipments;
	private double totalUtilization;
	private TSPDirectionCostDrivers costDrivers;
	
	public TSPDirectionImpl(String idString){
		this.id = idString;
		this.shipments = new ArrayList<Shipment>();
		this.totalUtilization = 0;
	}


	public Region getFromRegion() {
		return fromRegion.getRegion();
	}


	public void setFromRegion(TSPRegion fromRegion) {
		this.fromRegion = fromRegion;
	}


	public Region getToRegion() {
		return toRegion.getRegion();
	}


	public void setToRegion(TSPRegion toRegion) {
		this.toRegion = toRegion;
	}


	public String getId() {
		return id;
	}


	public Collection<Shipment> getShipments() {
		return shipments;
	}
	
	public void addShipment(Shipment shipment){
		this.shipments.add(shipment);
		this.totalUtilization = this.totalUtilization + shipment.getQuantity();
	}
	
	public void clearDirection(){
		this.shipments.clear();
		this.totalUtilization = 0;
	}


	public double getTotalUtilization() {
		return totalUtilization;
	}



	public Facility getFromFacility() {
		return fromRegion.getFacility();
	}



	public Facility getToFacility() {
		return toRegion.getFacility();
	}

	public void clearUtilization(){
		this.shipments.clear();
		this.totalUtilization = 0;
	}


	public TSPDirectionCostDrivers getCostDrivers() {
		return costDrivers;
	}
	
	

}

