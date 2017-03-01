package supply;

public class EuclideanFacilityDistanceAssigner implements FacilityDistanceAssigner {

	
	public double assignDistance(Facility fromFacility, Facility toFacility) {
	
		double xDifference = fromFacility.getCoord().getX()-toFacility.getCoord().getX();
		
		double yDifference = fromFacility.getCoord().getY()-toFacility.getCoord().getY();
				
		return Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2)) ;

	}

	
}
