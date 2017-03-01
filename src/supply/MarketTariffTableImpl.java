package supply;

import java.util.HashMap;

import scenario.Region;



public class MarketTariffTableImpl implements MarketTariffTable {

	private HashMap<String,MarketTariffTableEntry> tariffMap;
	
	public MarketTariffTableImpl(HashMap<String,MarketTariffTableEntry> tariffMap){
		this.tariffMap = tariffMap;
	}
	
	public MarketTariffTableEntry getTariff(String idString){
		return tariffMap.get(idString);			
	}
	
	public MarketTariffTableEntry getTariff(Region fromRegion, Region toRegion){
		
		return tariffMap.get(fromRegion.getId()+toRegion.getId());			
	}
	
	public void addEntry(MarketTariffTableEntry entry){
		this.tariffMap.put(entry.getId(), entry);
	}

	public HashMap<String,MarketTariffTableEntry> getTariffs(){
		return this.tariffMap;			
	}
	
	
}
