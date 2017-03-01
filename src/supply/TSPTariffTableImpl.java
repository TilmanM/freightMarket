package supply;

import java.util.HashMap;

import scenario.Region;



public class TSPTariffTableImpl implements TSPTariffTable{
private HashMap<String,TSPTariffTableEntry> tariffMap;
	
	public TSPTariffTableImpl(){
		this.tariffMap = new HashMap<String,TSPTariffTableEntry>();
	}
	
	public TSPTariffTableEntry getTariff(String idString){
		return tariffMap.get(idString);			
	}
	
	public void addEntry(TSPTariffTableEntry entry){
		this.tariffMap.put(entry.getId(), entry);
	}
	
	
	
	public HashMap<String, TSPTariffTableEntry> getTariffMap() {
		return tariffMap;
	}

	public void removeEntry(Region fromRegion, Region toRegion){
		String tariffMapId = TSPTariffTableEntryImpl.makeIdString(fromRegion.getId(), toRegion.getId());
		this.tariffMap.remove(tariffMapId);
	}
}
