package supply;

import java.util.HashMap;

public class FacilityCostDrivers {

	
	private HashMap<TSPDirection, TSPDirectionCostDrivers> mainRunCostDrivers;
	private SpatialCostDrivers spatialCostDrivers;
	
	
	public FacilityCostDrivers(){
		this.spatialCostDrivers = new SpatialCostDrivers();
		this.mainRunCostDrivers = new HashMap<TSPDirection, TSPDirectionCostDrivers>();
	}
	
	
	public HashMap<TSPDirection, TSPDirectionCostDrivers> getMainRunCostDrivers() {
		return mainRunCostDrivers;
	}
	
	public void setMainRunCostDrivers(
			HashMap<TSPDirection, TSPDirectionCostDrivers> mainRunCostDrivers) {
		this.mainRunCostDrivers = mainRunCostDrivers;
	}

	public SpatialCostDrivers getSpatialCostDrivers() {
		return spatialCostDrivers;
	}

	public void setSpatialCostDrivers(SpatialCostDrivers spatialCostDrivers) {
		this.spatialCostDrivers = spatialCostDrivers;
	}
	
	
	
	
}
