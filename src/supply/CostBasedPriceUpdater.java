
package supply;

import org.apache.log4j.Logger;

import controler.MarketControler;

public class CostBasedPriceUpdater implements PriceUpdater {

	private static final Logger logger = Logger.getLogger(CostBasedPriceUpdater.class.getName());
	
	private TSP tsp;
	private static double alpha;
	
	public CostBasedPriceUpdater(TSP tsp){
		this.tsp = tsp;
	}
	
	public void updatePrices(){
		
			for(TSPTariffTableEntry entry : tsp.getCostMap().values()){
			TSPTariffTableEntry oldEntry = 	tsp.getTariffTable().getTariff(entry.getId());
			logger.trace("Old price of TSP " + tsp.getId() + " from region " + oldEntry.getFromRegion().getId() + " to region " + "fixed=" + oldEntry.getConstant() + " linear=" + oldEntry.getLinear());
			double newConstant = alpha * entry.getConstant() + (1-alpha)*  oldEntry.getConstant();
			double newLinear = alpha* entry.getLinear() + (1-alpha) * oldEntry.getLinear();
			oldEntry.setConstant(newConstant);
			oldEntry.setLinear(newLinear);
			logger.trace("New price of TSP " + tsp.getId() + " from region " + oldEntry.getFromRegion().getId() + " to region " + "fixed=" + oldEntry.getConstant() + " linear=" + oldEntry.getLinear());
		}
	}
}

