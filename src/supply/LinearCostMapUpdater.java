package supply;

import java.util.HashMap;

import org.apache.log4j.Logger;

import controler.MarketControler;

public class LinearCostMapUpdater implements CostMapUpdater{
	
	private static final Logger logger = Logger.getLogger(LinearCostMapUpdater.class.getName());
	
	private TSP tsp;
	private Facility mainRunFacility;
	
	public LinearCostMapUpdater(TSP tsp){
		this.tsp = tsp;
	}
	
	public void updateCostMap(){
		HashMap<String,TSPTariffTableEntry> costMap = tsp.getCostMap();
		for(TSPLane lane : tsp.getLanes()){
			
			mainRunFacility = lane.getMainRunFacility();
			TSPTariffTableEntry forwardEntry = costMap.get(lane.getForwardDirection().getId());
			forwardEntry.setConstant(getDirectionFixed(lane.getForwardDirection()));	
			logger.trace("Fixed costs of  tsp" + tsp.getId() + " on direction " + lane.getForwardDirection().getId() + " =" + forwardEntry.getConstant());
			forwardEntry.setLinear(getDirectionLinear(lane.getForwardDirection()));
			logger.trace("Linear costs of  tsp" + tsp.getId() + " on direction " + lane.getForwardDirection().getId() + " =" + forwardEntry.getLinear());
			
			TSPTariffTableEntry backwardEntry = costMap.get(lane.getBackwardDirection().getId());
			logger.trace("Fixed costs of  tsp" + tsp.getId() + " on direction " + lane.getBackwardDirection().getId() + " =" + backwardEntry.getConstant());
			backwardEntry.setConstant(getDirectionFixed(lane.getBackwardDirection()));	
			logger.trace("Linear costs of  tsp" + tsp.getId() + " on direction " + lane.getBackwardDirection().getId() + " =" + backwardEntry.getLinear());
			backwardEntry.setLinear(getDirectionLinear(lane.getBackwardDirection()));
		}
	}

	private double getDirectionFixed(TSPDirection direction){
		double fixed = 0;
		
		fixed = fixed + direction.getFromFacility().getCostValues().getCollectionCostsPerShipmentFixed();
		fixed = fixed + direction.getToFacility().getCostValues().getDistributionCostsPerShipmentFixed();
		if(mainRunFacility.getCostValues().getMainRunCostsPerShipmentFixed().containsKey(direction)){
			fixed = fixed + mainRunFacility.getCostValues().getMainRunCostsPerShipmentFixed().get(direction);
		}
		if(mainRunFacility.getCostValues().getTransshipmentCostsPerShipmentFixed().containsKey(direction)){
			fixed = fixed + mainRunFacility.getCostValues().getTransshipmentCostsPerShipmentFixed().get(direction);
		}		
		return fixed;
	}
	
	private double getDirectionLinear(TSPDirection direction){
		double linear = 0;
		
		linear = linear + direction.getFromFacility().getCostValues().getCollectionCostsPerShipmentLinear();
		linear = linear + direction.getToFacility().getCostValues().getDistributionCostsPerShipmentLinear();
		if(mainRunFacility.getCostValues().getMainRunCostsPerShipmentLinear().containsKey(direction)){
			linear = linear + mainRunFacility.getCostValues().getMainRunCostsPerShipmentLinear().get(direction);
		}
		if(mainRunFacility.getCostValues().getTransshipmentCostsPerShipmentLinear().containsKey(direction)){
			linear = linear + mainRunFacility.getCostValues().getTransshipmentCostsPerShipmentLinear().get(direction);
		}
		
		return linear;
	}
	
}
