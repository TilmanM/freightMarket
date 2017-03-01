
package supply;

public class SimpleTSPVehicleType implements SimpleVehicleType {

	private String tspVehicleType;
	private String name;
	private long capacity;
	private double fixed;
	private double linear;

	public String getVehicleType() {
		return tspVehicleType;
	}
	public void setVehicleType(String facilityType) {
		this.tspVehicleType = facilityType;
	}
	public long getCapacity() {
		return capacity;
	}
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	public double getFixed() {
		return fixed;
	}
	public void setFixed(double fixed) {
		this.fixed = fixed;
	}
	public double getLinear() {
		return linear;
	}
	public void setLinear(double linear) {
		this.linear = linear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
