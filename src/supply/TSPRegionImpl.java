package supply;

import scenario.Region;

public class TSPRegionImpl implements TSPRegion {

	private Region region;
	private Facility facility;
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Facility getFacility() {
		return facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	
}
