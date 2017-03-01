package demand;


import scenario.Region;
import utils.Coordinate;

public class CompanyImpl implements Company{

	private String id;
	private Coordinate coord;
	private Region region;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Coordinate getCoord() {
		return coord;
	}
	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
	
	
}
