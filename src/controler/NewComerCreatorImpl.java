package controler;

import org.apache.log4j.Logger;

import scenario.MarketLane;
import scenario.MarketScenario;
import supply.TSP;

public class NewComerCreatorImpl implements NewComerCreator {

	private static final Logger logger = Logger.getLogger(NewComerCreatorImpl.class.getName());
	
	private MarketScenario scenario;
	
	public NewComerCreatorImpl(MarketScenario scenario){
		this.scenario = scenario;
	}

	public void createNewcomer(){
		logger.trace("Create new observing TSP");
		TSP newcomer = scenario.createTSP();
		int openLanes = 0;
		for(MarketLane lane: scenario.getMarketLanes()){
			logger.trace("Start assigning newcomer to open lanes");
			if(lane.isOpen()){
				scenario.addNewcomer(newcomer);
				lane.setNewcomer(scenario.getNewcomer());
				logger.trace("Assigned newcomer to lane: " + lane.getId());
				openLanes++;
			}
		}
	}
}
