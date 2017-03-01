package supply;

import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import scenario.MarketScenario;

public class MarketTariffTableCreatorImpl {
	
	
	private static final Logger logger = Logger.getLogger(MarketTariffTableCreatorImpl.class.getName());
	
	private MarketTariffTable table;
	
	public MarketTariffTable createMarketTariffTable(Collection<MarketTariffTableEntry> entries){	
		HashMap<String,MarketTariffTableEntry> tariffMap = new HashMap<String,MarketTariffTableEntry>();
		logger.trace("Start creating market tariff table");
		
		for(MarketTariffTableEntry entry : entries){
				tariffMap.put(entry.getId(), entry);
				logger.trace("Entry " + entry.getId() + " added to the table");
		}
		
		this.table = new MarketTariffTableImpl(tariffMap);
		logger.trace("Market tariff table successfully created");
		return table;
	}
	
	
}

