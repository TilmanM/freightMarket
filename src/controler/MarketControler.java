package controler;

import java.util.Random;

import org.apache.log4j.Logger;

import io.SimulationDataTracker;
import io.SimulationDataTrackerImpl;
import demand.Flow;

import scenario.MarketScenario;
import scenario.MarketTariffTableUpdater;
import scenario.MarketTariffTableUpdaterImpl;
import supply.TSP;

public class MarketControler {

	private static final Logger logger = Logger.getLogger(MarketControler.class.getName());
	
	private MarketScenario scenario;
	private NewComerCreator newComerCreator;
	private NewComerEnterer newComerEnterer;
	private NewComerExitor newComerExitor;
	
	private MarketTariffTableUpdater marketUpdater;
	public static Random simulationRandom;
	private SimulationDataTracker tracker;
	
	static{
		simulationRandom = new Random(1);
	}
	
	public MarketControler(String inputParameterPath){	
		this.scenario = MarketScenario.getInstance();
		scenario.initializeScenario(inputParameterPath);	
		this.tracker = new SimulationDataTrackerImpl();
		this.newComerCreator = new NewComerCreatorImpl(scenario);
		this.newComerEnterer = new NewComerEntererImpl(scenario);
		this.newComerExitor = new NewComerExitorImpl(scenario);
	}
		
	public void run(){
	
		while(true){
			for(int i =1; i < 100; i++){
				logger.trace("Demand side of the market is active in iteration " + i);
				demandIsActive();
				logger.trace("Supply side of the market is active in iteration " + i);
				supplyIsActive();		
				tracker.trackData(scenario);
				for(TSP tsp : scenario.getTsps()){
					tsp.clearShipments();
				}
				for(Flow flow : scenario.getFlows()){
					flow.getOffers().clear();
				}
			}
			newComerExitor.newcomerExitsLanes();
			tracker.trackData(scenario);
			newComerEnterer.newcomerEntersLanes();
			newComerCreator.createNewcomer();
			if(scenario.getNewcomer()== null){
				break;
			}	
		}
	}

	
	private void demandIsActive(){
		for(Flow flow : scenario.getFlows()){
			flow.requestOffers();
			TSP chosenOne = flow.selectTSP();
			if(chosenOne != null){
				flow.assignShipment(chosenOne);
			}
		}		
	}
	
	private void supplyIsActive(){
	for(TSP tsp : scenario.getTsps()){
			tsp.updateCostDrivers();
			tsp.calculateCosts();
			tsp.updatePrices();
		}
		this.marketUpdater = new MarketTariffTableUpdaterImpl();
		marketUpdater.updateMarketTarifftable(scenario);	
	}
	
	
}
