package scenario;

import java.util.Random;

import org.junit.experimental.theories.Theories;

import supply.Facility;
import utils.Coordinate;
import utils.CoordinateImpl;

public class RegionImpl implements Region {

	private String id;
	private Coordinate coord;
	private double radius;
	private static Random regionRandom;
	
	
	static{
		regionRandom = new Random(1);
	}
	
	public RegionImpl(String Id){
		this.id = Id;
	}
		
	public Coordinate getCoord() {
		return coord;
	}
	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public static Coordinate randomCoord(Region region){
		Coordinate facilityLocation = new CoordinateImpl();
		double distanceFromCenter = regionRandom.nextDouble() * region.getRadius();
		double angle = regionRandom.nextDouble()*360;
		double companyLocationX = distanceFromCenter * Math.cos(Math.toRadians(angle));
		double companyLocationY = distanceFromCenter * Math.sin(Math.toRadians(angle));
		facilityLocation.setX(region.getCoord().getX() + companyLocationX);
		facilityLocation.setY(region.getCoord().getY() + companyLocationY);
		return facilityLocation;				
	}


	
    
	
	
	
}
