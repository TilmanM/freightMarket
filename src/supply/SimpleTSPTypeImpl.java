package supply;

public class SimpleTSPTypeImpl implements TSPType {

	private TSPResources resources;
	private String tspType;
	private double minimalUtilization;
	
	public SimpleTSPTypeImpl(String tspType){
		this.tspType = tspType;
		this.resources = new SimpleTSPResourcesImpl();
	}
	
	
	public TSPResources getResources() {
		return resources;
	}

	public void setResources(TSPResources resources) {
		this.resources = resources;
	}

	public String getTspType() {
		return tspType;
	}


	public double getMinimalUtilization() {
		return minimalUtilization;
	}


	public void setMinimalUtilization(double minimalUtilization) {
		this.minimalUtilization = minimalUtilization;
	}
	
	
	
	
}
