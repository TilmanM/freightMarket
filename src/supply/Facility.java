
package supply;

import java.util.Collection;

import utils.Coordinate;
import demand.Shipment;
import scenario.Region;


public interface Facility {

	
	public String getId();
	
	public void setId(String id);
	
	public Coordinate getCoord();
	
	public void setCoord(Coordinate coord);
	
	public Region getRegion();
	
	public void setRegion(Region region);
	
	public void increaseCapacity();

	public FacilityType getType();
	
	public void setType(FacilityType type); 
	
	public double getCapacity();
	
	public boolean capacityFree(Shipment shipment);
	
	public void decreaseCapacity();
	
	public FacilityUtilizationInformation getUtilizationInformation();
	
	public FacilityCostDrivers getCostDrivers();
	
	public void setCostDrivers(FacilityCostDrivers costDrivers);
	
	public Collection<TSPLane> getTSPLanes();
	
	public void setTSPLane (TSPLane tspLane);
	
	public TSP getTsp();

	public void setTsp(TSP tsp);
	
	public LinearFacilityCostValues getCostValues();
}

