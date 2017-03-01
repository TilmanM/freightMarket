package supply;


import java.util.ArrayList;
import java.util.Collection;

import demand.Shipment;
import scenario.Region;
import scenario.RegionImpl;
import utils.Coordinate;

public class FacilityImpl implements Facility {

	private String Id;
	private TSP tsp;
	private Coordinate coord;
	private Region region;
	private double capacity;
	private FacilityType type;
	private FacilityUtilizationInformation utilizationInformation;
	private FacilityCostDrivers costDrivers;
	private FacilityLocator locator;
	private LinearFacilityCostValues costValues;
	private ArrayList <TSPLane> tspLanes;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Coordinate getCoord() {
		return coord;
	}
	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public boolean capacityFree(Shipment shipment) {
		return utilizationInformation.capacityFree(shipment);
	}
		
	
	public double getCapacity() {
		return capacity;
	}
	public void increaseCapacity(){
		this.capacity = this.capacity + this.type.getIncrement();
	}
	
	public void decreaseCapacity(){
		this.capacity = this.capacity - this.type.getIncrement();
	}	
	public FacilityImpl(Region region, TSP tsp){
		this.region = region;
		locator = new EuclideanFacilityLocator();
		this.coord = locator.locateFacility(this);
		this.utilizationInformation = new FacilityUtilizationInformation(this);
		this.tspLanes = new ArrayList<TSPLane>();
	}
	public FacilityType getType() {
		return type;
	}
	public void setType(FacilityType type) {
		this.type = type;
		this.capacity = type.getCapacity();
		
	}
	public FacilityUtilizationInformation getUtilizationInformation() {
		return utilizationInformation;
	}
	public FacilityCostDrivers getCostDrivers() {
		return costDrivers;
	}
	public LinearFacilityCostValues getCostValues() {
		return costValues;
	}
	public void setCostDrivers(FacilityCostDrivers costDrivers) {
		this.costDrivers = costDrivers;
	}

	public Collection<TSPLane> getTSPLanes() {		
		return this.tspLanes;
	}

	public void setTSPLane(TSPLane tspLane) {
		this.tspLanes.add(tspLane);
		
	}
	public TSP getTsp() {
		return tsp;
	}
	public void setTsp(TSP tsp) {
		this.tsp = tsp;
	}
	
	
	
	
}

