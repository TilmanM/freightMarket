package supply;

public interface SimpleVehicleType {
	
	public String getVehicleType() ;
	
	public void setVehicleType(String vehicleType);

	public String getName() ;
	
	public void setName(String name);
	
	public long getCapacity();
	
	public void setCapacity(long capacity);
	
	public double getFixed();
	
	public void setFixed(double fixed);
	
	public double getLinear();
	
	public void setLinear(double linear);
	
}

