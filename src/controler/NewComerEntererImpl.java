package controler;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import scenario.MarketLane;
import scenario.MarketScenario;
import supply.TSP;
import supply.TSPLane;

public class NewComerEntererImpl implements NewComerEnterer {

	private static final Logger logger = Logger.getLogger(NewComerEntererImpl.class.getName());
	
	private MarketScenario scenario;
	
	public NewComerEntererImpl(MarketScenario scenario){
		this.scenario = scenario;
	}
	
	public void newcomerEntersLanes(){
		ArrayList <TSPLane> entryLanes = lanesInWhichToEnter(scenario.getNewcomer());
		logger.trace("Observer TSP starts entering lanes permanantly ");
		
		if(entryLanes.size() > 0){
			scenario.getTsps().add(scenario.getNewcomer());	
			logger.trace("Observer TSP " + scenario.getNewcomer().getId() + " added to set of permanent TSPs");
		}
		for(TSPLane lane : entryLanes){
			MarketLane marketLane = lane.getMarketLane();
			marketLane.getTSPs().add(marketLane.getNewcomer());
			marketLane.removeNewcomer();
			logger.trace("Observer TSP " + scenario.getNewcomer().getId() + " added permanently to lane " + marketLane.getId());
		}
		scenario.removeNewcomer();
	}
	
	private ArrayList<TSPLane> lanesInWhichToEnter(TSP newcomer){
		ArrayList<TSPLane> entryLanes = new ArrayList<TSPLane>();
		logger.trace("Determining lanes in which the observer enters");
		for(TSPLane lane : newcomer.getLanes()){
			if(lane.getBackwardDirection().getTotalUtilization() >= newcomer.getType().getMinimalUtilization()
					&& lane.getForwardDirection().getTotalUtilization() >= newcomer.getType().getMinimalUtilization()){
				entryLanes.add(lane);
			}
		}
		
		return entryLanes;
		
	}

}
