package demand;
//Kann das nicht besser als innere Klasse von FlowImpl geschrieben werden? Können Interfaces innere Klassen haben?
public class ShipmentImpl implements Shipment{

	
	
	private Flow flow;
	private double quantity;
	private String id;
	
	public String getId(){
		return this.id;
	}
	
	public Flow getFlow() {
		return this.flow;
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
