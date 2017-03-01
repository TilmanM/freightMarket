package utils;

public class PoiZone {

	private int zoneId;
	private double lat;
	private double lon;
	
	public static void assignXYcoordinates (PoiZone zone){
		double lat = zone.getLat();
		double lon = zone.getLon();
		
	}
	
	
	
	public PoiZone(int zoneId,  double lat, double lon){
		this.zoneId = zoneId;
		this.lat = lat;
		this.lon = lon;
	}

	public int getZoneId() {
		return zoneId;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}



	public void setLat(double lat) {
		this.lat = lat;
	}



	public void setLon(double lon) {
		this.lon = lon;
	}
	
	
}
