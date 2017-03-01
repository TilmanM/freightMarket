
package scenario;

import org.apache.log4j.Logger;

import controler.MarketControler;
import supply.MarketTariffTableEntry;
import supply.MarketTariffTableEntryImpl;
import supply.TSP;

public class MarketTariffTableUpdaterImpl implements MarketTariffTableUpdater {

	private static final Logger logger = Logger.getLogger(MarketTariffTableUpdaterImpl.class.getName());
	
	@Override
	public void updateMarketTarifftable(MarketScenario scenario) {
	
		for(MarketTariffTableEntry entry : scenario.getTariffTable().getTariffs().values()){
			logger.trace("Update market price from region " + entry.getFromRegion().getId() + " to region " + entry.getToRegion().getId());
			logger.trace("Old price fixed=" + entry.getConstant());
			logger.trace("Old price linear=" + entry.getLinear());
			double fixed = 0;
			double linear = 0;
			int numberOfTSPs = 0;
			for(TSP tsp : scenario.getTsps()){
				if(tsp.getTariffTable().getTariff(entry.getId())!=null){
					fixed = fixed + tsp.getTariffTable().getTariff(entry.getId()).getConstant();
					linear = linear + tsp.getTariffTable().getTariff(entry.getId()).getLinear();
					numberOfTSPs++;	
				}
			}
			logger.trace("Number of suppliers on lane " + numberOfTSPs);
			entry.setConstant(fixed/numberOfTSPs);
			logger.trace("New price fixed=" + entry.getConstant());
			entry.setLinear(linear/numberOfTSPs);
			logger.trace("New price linear=" + entry.getLinear());
		}
	}	
}
