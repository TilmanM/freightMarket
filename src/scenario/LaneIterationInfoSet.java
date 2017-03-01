package scenario;


public class LaneIterationInfoSet {
	private int iterationNumber;
	private String laneId;
	private MarketLaneInfoElement element;
	
	public int getIterationNumber() {
		return iterationNumber;
	}
	public void setIterationNumber(int iterationNumber) {
		this.iterationNumber = iterationNumber;
	}
	public MarketLaneInfoElement getElement() {
		return element;
	}
	public void setElement(MarketLaneInfoElement element) {
		this.element = element;
	}
	public String getLaneId() {
		return laneId;
	}
	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}
	
}