package supply;

import java.util.HashMap;

import scenario.Region;


public interface MarketTariffTable {
 
	public MarketTariffTableEntry getTariff(String idString);			
	
	public void addEntry(MarketTariffTableEntry entry);
	
	public MarketTariffTableEntry getTariff(Region fromRegion, Region toRegion);
	
	public HashMap<String,MarketTariffTableEntry> getTariffs();
}
