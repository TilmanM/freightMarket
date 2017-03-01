
package supply;

import java.util.Collection;
import java.util.HashMap;

import demand.Shipment;

public interface TSP {
	public String getId();

	public void setId(String id);

	public TSPTariffTable getTariffTable();

	public void setTariffTable(TSPTariffTable tariffTable);

	public Collection<TSPLane> getLanes();

	public void setLanes(Collection<TSPLane> lanes);

	public Collection<Facility> getFacilities();

	public void setFacilities(Collection<Facility> facilities);

	public TSPType getType();

	public void setType(TSPType type);
	
	public HashMap <String,TSPRegion> getTspRegions();

	public Offer makeOffer(Shipment shipment);		

	public void assignShipment(Shipment shipment);
	
	public HashMap<String, TSPTariffTableEntry> getCostMap();
	
	public void clearShipments();
	
	public void updateCostDrivers();
	
	public void calculateCosts();
	
	public void updatePrices();
	
	
}
