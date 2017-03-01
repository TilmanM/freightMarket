package scenario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import supply.MarketTariffTable;
import supply.MarketTariffTableEntry;
import supply.MarketTariffTableEntryImpl;
import supply.MarketTariffTableImpl;


public class ScenarioLog{
		
		private MarketScenario scenario;
	    private ArrayList<MarketLaneInfo> marketLaneInfos;
		private MarketTariffTable marketTariffTable;
		
		//private ArrayList<SpatialCostDrivers> spatialDrivers;
		//private ArrayList<TSPDirectionCostDrivers> mainRunDrivers;
		
		public ScenarioLog(MarketScenario scenario){
			this.scenario = scenario;
			this.marketLaneInfos = collectMarketLaneInfos();
			this.marketTariffTable = deepCopyMarketTariffTable();
		}
						
		public ArrayList<MarketLaneInfo> getMarketLaneInfos() {
			return marketLaneInfos;
		}

		public MarketTariffTable getMarketTariffTable() {
			return marketTariffTable;
		}

		private MarketTariffTable deepCopyMarketTariffTable(){
			HashMap <String,MarketTariffTableEntry> entriesMapCopy = new HashMap <String,MarketTariffTableEntry>();
			for(Entry<String,MarketTariffTableEntry> entry: scenario.getTariffTable().getTariffs().entrySet()){
				MarketTariffTableEntry valueCopy = new MarketTariffTableEntryImpl();
				String keyCopy = new String(entry.getKey());
				valueCopy.setId(entry.getValue().getId());
				valueCopy.setFromRegion(entry.getValue().getFromRegion());
				valueCopy.setToRegion(entry.getValue().getToRegion());
				valueCopy.setLinear(entry.getValue().getLinear());
				valueCopy.setConstant(entry.getValue().getConstant());
				entriesMapCopy.put(keyCopy, valueCopy);
			}
			MarketTariffTable tableCopy = new MarketTariffTableImpl(entriesMapCopy);
			return tableCopy;
		}

		private ArrayList<MarketLaneInfo> collectMarketLaneInfos(){
			ArrayList<MarketLaneInfo> infoList = new ArrayList<MarketLaneInfo>();
			for(MarketLane marketLane : scenario.getMarketLanes()){
				MarketLaneInfo laneInfo = new MarketLaneInfo(marketLane);
				infoList.add(laneInfo);
			}
			return infoList;
		}
		
		public LaneIterationInfoSet getLaneIterationInfos(String laneId, loggedCategory category){
			LaneIterationInfoSet infoSet = new LaneIterationInfoSet();
				for(MarketLaneInfo laneInfo : marketLaneInfos){
					if(laneInfo.getMarketLane().getId().equals(laneId)){
						for(MarketLaneInfoElement element : laneInfo.getInfoElements()){
							if(element.getKey().name().equals(category.name())){
								infoSet.setElement(element);
							}
						}
					}
				}
			
			return infoSet;
		}
}

