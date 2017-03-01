package controler;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import scenario.MarketScenario;
import supply.Facility;
import supply.TSP;
import supply.TSPLane;

public class NewComerExitorImpl implements NewComerExitor {

	private static final Logger logger = Logger.getLogger(NewComerExitorImpl.class.getName());
	
	private MarketScenario scenario;
	
	public NewComerExitorImpl(MarketScenario scenario){
		this.scenario = scenario;
	}
	
	public void newcomerExitsLanes(){
		logger.trace("Checking if observing TSP has to leave some lanes");
		ArrayList <TSPLane> exitLanes = lanesFromWhichToExit(scenario.getNewcomer());
		for(TSPLane lane : exitLanes){
			lane.getMarketLane().closeLane();
			logger.trace("Market lane " + lane.getMarketLane().getId() + " closed");
			lane.getMarketLane().removeNewcomer();
			logger.trace("Observer removed from lane " + lane.getMarketLane().getId());
			scenario.getNewcomer().getTariffTable().removeEntry(lane.getFirstRegion(), lane.getSecondRegion());
			scenario.getNewcomer().getTariffTable().removeEntry(lane.getSecondRegion(), lane.getFirstRegion());
			logger.trace("Entries for market lane " + lane.getMarketLane().getId() + " removed from observing TSP");
			for(Facility facility : scenario.getNewcomer().getFacilities()){
				if(facility.getRegion().equals(lane.getFirstRegion())){
					facility.decreaseCapacity();
					logger.trace("Capacity of facility " + facility.getId() + " reduced");
					if(facility.getCapacity()<=facility.getType().getCapacity()){
						scenario.getNewcomer().getTspRegions().remove(facility.getRegion());
						scenario.getNewcomer().getFacilities().remove(facility);
						logger.trace("Facility " + facility.getId() + " removed");
					}
				}
				if(facility.getRegion().equals(lane.getSecondRegion())){
					facility.decreaseCapacity();
					logger.trace("Capacity of facility " + facility.getId() + " reduced");
					if(facility.getCapacity()<=facility.getType().getCapacity()){
						scenario.getNewcomer().getTspRegions().remove(facility.getRegion());
						scenario.getNewcomer().getFacilities().remove(facility);
						logger.trace("Facility " + facility.getId() + " removed");
					}
				}
			}
		}
		
	}
	
	
	
	
	
	private ArrayList<TSPLane> lanesFromWhichToExit(TSP newcomer){
		ArrayList<TSPLane> exitLanes = new ArrayList<TSPLane>();
		
		for(TSPLane lane : newcomer.getLanes()){
			if(lane.getBackwardDirection().getTotalUtilization() < newcomer.getType().getMinimalUtilization()
					&& lane.getForwardDirection().getTotalUtilization() < newcomer.getType().getMinimalUtilization()){
				exitLanes.add(lane);
			}
		}
		
		return exitLanes;
		
	}
}
