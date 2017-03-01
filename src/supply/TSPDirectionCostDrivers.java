
package supply;

import scenario.Region;

public class TSPDirectionCostDrivers {

	private double mainRunVehicleUtilization;
	private double mainRunDistance;
	private double vehicleUtilizationParameter;
	private Region fromRegion;
	private Region toRegion;

	public TSPDirectionCostDrivers(){
	}
	
	public double getMainRunVehicleUtilization() {
		return mainRunVehicleUtilization;
	}
	public void setMainRunVehicleUtilization(double mainRunVehicleUtilization) {
		this.mainRunVehicleUtilization = mainRunVehicleUtilization;
	}
	public double getMainRunDistance() {
		return mainRunDistance;
	}
	public void setMainRunDistance(double mainRunDistance) {
		this.mainRunDistance = mainRunDistance;
	}
	
	public double getVehicleUtilizationParameter() {
		return vehicleUtilizationParameter;
	}
	public void setVehicleUtilizationParameter(double vehicleUtilizationParameter) {
		this.vehicleUtilizationParameter = vehicleUtilizationParameter;
	}

	public Region getFromRegion() {
		return fromRegion;
	}

	public void setFromRegion(Region fromRegion) {
		this.fromRegion = fromRegion;
	}

	public Region getToRegion() {
		return toRegion;
	}

	public void setToRegion(Region toRegion) {
		this.toRegion = toRegion;
	}
	
	
	
}

