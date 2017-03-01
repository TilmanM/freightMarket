package scenario;


public class MarketLaneInfoElement {
	private loggedCategory key;
	private LaneInfoElementValue value;

	public MarketLaneInfoElement(loggedCategory key, LaneInfoElementValue value ) {
		this.key = key;
		this.value = value;
	}

	public loggedCategory getKey() {
		return key;
	}

	public LaneInfoElementValue getValue() {
		return value;
	}
	
	

}