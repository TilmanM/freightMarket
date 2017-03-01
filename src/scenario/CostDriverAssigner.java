package scenario;

import java.util.Collection;

import supply.Facility;
import supply.SpatialCostDrivers;

public class CostDriverAssigner {

	private Collection <SpatialCostDrivers> costDrivers;
	
	public CostDriverAssigner(Collection <SpatialCostDrivers> costDrivers){
		this.costDrivers = costDrivers;
	}
	
	
	public void assignCostDrivers(Facility facility){
		for(SpatialCostDrivers drivers : costDrivers){
			if(drivers.getRegion() == facility.getRegion()){
					facility.getCostDrivers().setSpatialCostDrivers(drivers);
			}
		}
	}
}
