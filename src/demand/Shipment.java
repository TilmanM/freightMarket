package demand;

public interface Shipment {
	public String getId();
	
	public Flow getFlow();

	public double getQuantity();

	public void setFlow(Flow flow);

	public void setQuantity(double quantity);

	public void setId(String id);

}
