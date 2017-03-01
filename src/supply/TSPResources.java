package supply;

import java.util.Collection;

public interface TSPResources {
	
	public FacilityType getFacilityType();

	public void setFacilityType(FacilityType facilityType);
	
	public SimpleVehicleType getLocalVehicleType();
	
	public void setLocalVehicleType(SimpleVehicleType localVehicleType);
	
	public SimpleVehicleType getMainRunVehicleType();
	
	public void setMainRunVehicleType(SimpleVehicleType mainRunVehicleType);
	
	
}
