package supply;


import java.util.ArrayList;
import java.util.Collection;

public class SimpleTSPResourcesImpl implements TSPResources {

	private FacilityType facilityType;
	private SimpleVehicleType localVehicleType;
	private SimpleVehicleType mainRunVehicleType;
	
		
	
	public  SimpleTSPResourcesImpl(){
		
	}
	
	public FacilityType getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}
	public SimpleVehicleType getLocalVehicleType() {
		return localVehicleType;
	}
	public void setLocalVehicleType(SimpleVehicleType localVehicleType) {
		this.localVehicleType = localVehicleType;
	}
	public SimpleVehicleType getMainRunVehicleType() {
		return mainRunVehicleType;
	}
	public void setMainRunVehicleType(SimpleVehicleType mainRunVehicleType) {
		this.mainRunVehicleType = mainRunVehicleType;
	}
	
	
	
}
