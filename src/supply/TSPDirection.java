package supply;

import demand.Shipment;
import scenario.Region;


public interface TSPDirection {

	public Region getFromRegion();
	
	public void setFromRegion(TSPRegion fromRegion);

	public Region getToRegion() ;

	public void setToRegion(TSPRegion toRegion);

	public String getId();
	
	public double getTotalUtilization();

	public void addShipment(Shipment shipment);
	
	public Facility getFromFacility();
	
	public Facility getToFacility();
	
	public void clearUtilization();
	
	public TSPDirectionCostDrivers getCostDrivers();
}

