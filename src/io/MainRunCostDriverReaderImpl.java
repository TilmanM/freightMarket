
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;

import scenario.MarketScenario;
import scenario.Region;
import supply.SpatialCostDrivers;
import supply.TSPDirectionCostDrivers;

public class MainRunCostDriverReaderImpl implements MainRunCostDriverReader {

	private static final Logger logger = Logger.getLogger(MainRunCostDriverReaderImpl.class.getName());
	
	private Collection<TSPDirectionCostDrivers> drivers;

	private String driversFilePath;
	private XMLEventReader xmlEventReader;

	public MainRunCostDriverReaderImpl(String driversFilePath){
		this.driversFilePath= driversFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		drivers = new ArrayList<TSPDirectionCostDrivers>();
		try {
			fileInputStream = new FileInputStream(driversFilePath);
			try {
				xmlEventReader = factory.createXMLEventReader(fileInputStream);
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Collection<TSPDirectionCostDrivers> getCostDriversFromFile(Collection<Region> regions){
		logger.trace("Start reading main run cost drivers from file");
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("TSPDirection")){
						TSPDirectionCostDrivers driver = new TSPDirectionCostDrivers();
							
						for(Region region : regions){
							if(region.getId().equals(startElement.getAttributeByName(new QName("FromRegion")).getValue())){
								driver.setFromRegion(region);
							}	
							if(region.getId().equals(startElement.getAttributeByName(new QName("ToRegion")).getValue())){
								driver.setToRegion(region);
							}	
						}				
															
						double mainRunVehicleUtilization = Double.parseDouble(startElement.getAttributeByName(new QName("mainRunVehicleUtilization")).getValue());
						driver.setMainRunVehicleUtilization(mainRunVehicleUtilization);
						drivers.add(driver);
					}
									
						
				}
			} 
		}
		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return drivers;
	}
	
	
}

