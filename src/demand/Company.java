package demand;

import scenario.Region;
import utils.Coordinate;


public interface Company {

	public String getId();
	
	public void setId(String id);

	public Coordinate getCoord();
	
	public void setCoord(Coordinate coord);
	
	public Region getRegion();
	
	public void setRegion(Region region);
}
	
		

