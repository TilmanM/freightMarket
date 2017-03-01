package utils;

//  calculation method from: w01.tp1.jp/~a540015671/study/ch2.pdf

public class WGS84toJPXYcoordinates {

	static final double Lat0 = 36.0;      
	static final double Lon0 = 134.333333;
	static final double a = 6378137.00;       	
	static final double b = 6356752.314;		  	
	static final double m0 = 0.9999;
	static double e = Math.sqrt((a*a-b*b)/(a*a));
	static double eDat = Math.sqrt((a*a-b*b)/(b*b));

	
	private double deg2rad(double deg) {
	        return (deg * Math.PI / 180.0);
	}
	
	public double toXcoordinate(double lon, double lat){
		double rLat = deg2rad(lat);
		double rLon = deg2rad(lon);
		double rLat0 = deg2rad(Lat0);
		double rLon0 = deg2rad(Lon0);
		
		double W = Math.sqrt(1-e*e*Math.sin(rLat)*Math.sin(rLat));
		double N = a/W;

		double n = eDat*Math.cos(rLat);		
		double t = Math.tan(rLat);
		
		double dLon = rLon-rLon0;	
		double dLat = deltaLat(rLat, rLat0);
		
		double x = m0*( dLat   +dLon*dLon/2*N*Math.sin(rLat)*Math.cos(rLat)
				        +Math.pow(dLon, 4)/24  *N  *Math.sin(rLat)  *Math.pow(Math.cos(rLat),3)  *(5  -t*t    +9*n*n   +4*n*n*n*n)
				        +Math.pow(dLon, 6)/720 *N  *Math.sin(rLat)  *Math.pow(Math.cos(rLat),5)  *(61 -58*t*t +t*t*t*t +270*n*n  -330*t*t*n*n)
				       );
		double y = m0*( dLon*N*Math.cos(rLat)  
					    +Math.pow(dLon,3)/6    *N  *Math.pow(Math.cos(rLat),3)  *(1   -t*t     +n*n)
						+Math.pow(dLon, 5)/120 *N  *Math.pow(Math.cos(rLat),5)  *(5   -18*t*t  +t*t*t*t  +14*n*n  -58*t*t*n*n)
				      );
		
		return x;

	}
	
	public double toYcoordinate(double lon, double lat){
		double rLat = deg2rad(lat);
		double rLon = deg2rad(lon);
		double rLat0 = deg2rad(Lat0);
		double rLon0 = deg2rad(Lon0);
		
		double W = Math.sqrt(1-e*e*Math.sin(rLat)*Math.sin(rLat));
		double N = a/W;

		double n = eDat*Math.cos(rLat);		
		double t = Math.tan(rLat);
		
		double dLon = rLon-rLon0;	
		double dLat = deltaLat(rLat, rLat0);
		
		double x = m0*( dLat   +dLon*dLon/2*N*Math.sin(rLat)*Math.cos(rLat)
				        +Math.pow(dLon, 4)/24  *N  *Math.sin(rLat)  *Math.pow(Math.cos(rLat),3)  *(5  -t*t    +9*n*n   +4*n*n*n*n)
				        +Math.pow(dLon, 6)/720 *N  *Math.sin(rLat)  *Math.pow(Math.cos(rLat),5)  *(61 -58*t*t +t*t*t*t +270*n*n  -330*t*t*n*n)
				       );
		double y = m0*( dLon*N*Math.cos(rLat)  
					    +Math.pow(dLon,3)/6    *N  *Math.pow(Math.cos(rLat),3)  *(1   -t*t     +n*n)
						+Math.pow(dLon, 5)/120 *N  *Math.pow(Math.cos(rLat),5)  *(5   -18*t*t  +t*t*t*t  +14*n*n  -58*t*t*n*n)
				      );
		
		return y;

	}
	
	
	
	
	
	
	
	private double deltaLat(double rLat, double rLat0){
		
		
		double k1 = 1.005052501813087;
		double k2 = 0.005063108622224;
		double k3 = 0.000010627590263;
		double k4 = 0.000000020820379;
		double k5 = 0.000000000039324;
		double k6 = 0.000000000000071;
		
				
	
		
		double dZeta = 	a*(1-e*e)*(	k1*(rLat-rLat0)
//		double dZeta = 	a*(1-e*e)*(	k1*(rLat0-rLat)
									-1/2*k2*Math.sin(2*rLat-2*rLat0)
									+1/4*k3*Math.sin(4*rLat-4*rLat0)
									-1/6*k4*Math.sin(6*rLat-6*rLat0)
									+1/8*k5*Math.sin(8*rLat-8*rLat0)
									-1/10*k6*Math.sin(10*rLat-10*rLat0)
									);
		
		return dZeta;
	}
	/*public static XYCoordinate transform(String lon, String lat) {

		double lonNorm = (Double.parseDouble(lon) * 3600 - 26782.5) / 10000;
		double latNorm = (Double.parseDouble(lat) * 3600 - 169028.66) / 10000;
		
		double CH1903X = 
			200147.07 +
			308807.95 * latNorm +
			3745.25 * Math.pow(lonNorm, 2) +
			76.63 * Math.pow(latNorm, 2) -
			194.56 * Math.pow(lonNorm, 2) * latNorm +
			119.79 * Math.pow(latNorm, 3);
		
		double CH1903Y = 
			600072.37 +
			211455.93 * lonNorm -
			10938.51 * lonNorm * latNorm -
			0.36 * lonNorm * Math.pow(latNorm, 2) -
			44.54 * Math.pow(lonNorm, 3);
		
		/* Important Note: in the Swiss Grid, y describes easting and x describes 
		 * northing, contrary to the usual naming conventions!		 */ 
	/*	return  new XYCoordinate(Double.toString(CH1903X),Double.toString(CH1903Y));
	}*/
}
