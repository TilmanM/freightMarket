package supply;

import java.util.HashMap;

import scenario.Region;

public interface TSPTariffTable {
	

	public TSPTariffTableEntry getTariff(String idString);			
	
	public void addEntry(TSPTariffTableEntry entry);
	
	public void removeEntry(Region fromRegion, Region toRegion);
	
	public HashMap<String, TSPTariffTableEntry> getTariffMap();


}
