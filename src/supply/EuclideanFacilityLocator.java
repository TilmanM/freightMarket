package supply;

import scenario.RegionImpl;
import utils.Coordinate;

public class EuclideanFacilityLocator implements FacilityLocator {

	public Coordinate locateFacility(Facility facility){
		return RegionImpl.randomCoord(facility.getRegion());	
	}
	
}
