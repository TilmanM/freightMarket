package scenario;

import supply.Facility;
import utils.Coordinate;

public interface Region {

		
	public String getId(); 
	
	public void setId(String regionId); 
    	
	public Coordinate getCoord();
	
	public void setCoord(Coordinate coord);
		
	public double getRadius();
	
	public void setRadius(double radius);


	
    

}
