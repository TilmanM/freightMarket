package scenario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import demand.Company;
import demand.Flow;
import supply.Facility;
import supply.TSP;
import supply.TSPLane;

public class SimpleScenarioWriterImpl implements ScenarioWriter {

	private Writer LogFileWriter;
	private MarketScenario scenario;
	private String lineSeparator;
	
	public SimpleScenarioWriterImpl (MarketScenario scenario){
		this.scenario = scenario;
		
	}
	
	public void writeInitialScenario(String filename){
		
		
		try {
			File logFile = new File(filename);
			LogFileWriter = new FileWriter(logFile);
			lineSeparator = "\n";
			writeTariffTable();
			writeRegions();
			writeTSPs();
			writeLanes();
			LogFileWriter.flush();
			LogFileWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeIteratioNScenario(String filename){
		
		try{
			File logFile = new File(filename);
			LogFileWriter = new FileWriter(logFile);
			lineSeparator = "\n";
			writeTariffTable();
			writeTSPs();
		}catch(Exception e){
			
		}
		
	}
	
	
	private void writeRegions() throws Exception{
		
		LogFileWriter.write(scenario.getRegions().size() + " regions " + lineSeparator);
		for(Region region : scenario.getRegions()){
			LogFileWriter.write("Region: "+ region.getId() + " Coordinate x="+ region.getCoord().getX() + " y="+region.getCoord().getY() + " radius="+region.getRadius() + lineSeparator );   
			LogFileWriter.write("Companies: " + lineSeparator);
			writeCompanies(region);	
		}
	}

	private void writeLanes() throws Exception{
		
		LogFileWriter.write(scenario.getMarketLanes().size() + " market lanes " + lineSeparator);
		for(MarketLane lane : scenario.getMarketLanes()){
			LogFileWriter.write("Lane: "+ lane.getId() + " between region "+ lane.getFirstRegion().getId() + " and region "+ lane.getSecondRegion().getId()+  lineSeparator);   
			LogFileWriter.write(lane.getFlows().size()+ " flows " + lineSeparator);  
			LogFileWriter.write(lane.getTSPs().size()+ " TSPs " + lineSeparator);  
			for(TSP tsp : lane.getTSPs()){
				LogFileWriter.write("TSP: " + tsp.getId());  
			}
		}
	}
	
	private void writeCompanies (Region region) throws Exception{
		
		for(Company company : scenario.getCompanies()){
			if(company.getRegion().equals(region)){
				LogFileWriter.write("Company: "+ company.getId() + " x=" + company.getCoord().getX() + " y="+ company.getCoord().getY()  +lineSeparator);
				LogFileWriter.write("Starting flows: " + lineSeparator);
				for(Flow flow : scenario.getFlows()){
					if(flow.getShipper().equals(company)){
						LogFileWriter.write("Flow: " + flow.getId() + " to Company " + flow.getRecipient().getId() + " Strength " + flow.getStrength() + lineSeparator);
					}
				}
			}
		}
	}	
	
	private void writeTariffTable() throws Exception{
		LogFileWriter.write("Tariff table: " + lineSeparator);
		for(String TariffString : scenario.getTariffTable().getTariffs().keySet()){
			LogFileWriter.write("Tariff from region " + scenario.getTariffTable().getTariff(TariffString).getFromRegion().getId() + " to region " + scenario.getTariffTable().getTariff(TariffString).getToRegion().getId()
					+ " constant " + scenario.getTariffTable().getTariff(TariffString).getConstant() + " linear " + scenario.getTariffTable().getTariff(TariffString).getLinear() + lineSeparator);
		}	
	}
		
	private void writeTSPs() throws Exception{
		LogFileWriter.write("TSPs: " + lineSeparator);
		for(TSP tsp : scenario.getTsps()){
			LogFileWriter.write("TSP: " + tsp.getId() + " type "+ tsp.getType().getTspType() + lineSeparator);
			LogFileWriter.write("Lanes: " + lineSeparator);
			for(TSPLane lane : tsp.getLanes()){
				LogFileWriter.write("Lane between region " + lane.getFirstRegion().getId() + " and region " + lane.getSecondRegion().getId() + lineSeparator);
				LogFileWriter.write("Direction from region " + lane.getForwardDirection().getFromRegion().getId() + " to region " + lane.getForwardDirection().getToRegion().getId() + lineSeparator);
				LogFileWriter.write("Tariff: constant=" + tsp.getTariffTable().getTariff(lane.getForwardDirection().getFromRegion().getId()+lane.getForwardDirection().getToRegion().getId()).getConstant()
						+ " linear="+tsp.getTariffTable().getTariff(lane.getForwardDirection().getFromRegion().getId()+lane.getForwardDirection().getToRegion().getId()).getLinear() + lineSeparator );
				LogFileWriter.write("Direction from region " + lane.getBackwardDirection().getFromRegion().getId() + " to region " + lane.getBackwardDirection().getToRegion().getId() + lineSeparator);
				LogFileWriter.write("Tariff: constant=" + tsp.getTariffTable().getTariff(lane.getBackwardDirection().getFromRegion().getId()+lane.getBackwardDirection().getToRegion().getId()).getConstant()
						+ " linear="+tsp.getTariffTable().getTariff(lane.getBackwardDirection().getFromRegion().getId()+lane.getBackwardDirection().getToRegion().getId()).getLinear() + lineSeparator );
			}
			LogFileWriter.write("Facilities: " + lineSeparator);
			for(Facility facility : tsp.getFacilities()){
				LogFileWriter.write("Facility " + facility.getId() + " in Region " + facility.getRegion() + " Coordinate x= " + facility.getCoord().getX()+
						" y=" +facility.getCoord().getY() + lineSeparator);
			}
		}
	}
	
	
	}
