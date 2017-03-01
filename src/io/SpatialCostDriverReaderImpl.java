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
import supply.MarketTariffTableEntry;
import supply.MarketTariffTableEntryImpl;
import supply.SpatialCostDrivers;

public class SpatialCostDriverReaderImpl implements SpatialCostDriverReader{

	private static final Logger logger = Logger.getLogger(SpatialCostDriverReaderImpl.class.getName());
	
	private Collection<SpatialCostDrivers> drivers;

	private String driversFilePath;
	private XMLEventReader xmlEventReader;

	public SpatialCostDriverReaderImpl(String driversFilePath){
		this.driversFilePath= driversFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		drivers = new ArrayList<SpatialCostDrivers>();
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

	public Collection<SpatialCostDrivers> getCostDriversFromFile(Collection<Region> regions){
		logger.trace("Start reading spatial cost drivers from file: " + this.driversFilePath);
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("Region")){
						SpatialCostDrivers driver = new SpatialCostDrivers();
							
						for(Region region : regions){
							if(region.getId().equals(startElement.getAttributeByName(new QName("id")).getValue())){
								driver.setRegion(region);
							}	
						}				
					
						while(true){
							event = xmlEventReader.nextEvent();
								if(event.isStartElement()){															
									if(event.asStartElement().getName().getLocalPart().equals("distributionLocalCostParameter")){
										double distributionLocalCostParameter = Double.parseDouble((event.asStartElement().getAttributeByName(new QName("value")).getValue()));
										driver.setDistributionLocalCostParameter(distributionLocalCostParameter);
									}
									if(event.asStartElement().getName().getLocalPart().equals("distributionConnectionCostParameter")){
										double distributionConnectionCostParameter = Double.parseDouble(event.asStartElement().getAttributeByName(new QName("value")).getValue());
										driver.setDistributionConnectionCostParameter(distributionConnectionCostParameter);
									}
									if(event.asStartElement().getName().getLocalPart().equals("distributionVehicleUtilizationParameter")){
										double distributionVehicleUtilizationParameter = Double.parseDouble(event.asStartElement().getAttributeByName(new QName("value")).getValue());
										driver.setDistributionVehicleUtilizationParameter(distributionVehicleUtilizationParameter);
									}
									if(event.asStartElement().getName().getLocalPart().equals("collectionLocalCostParameter")){
										double collectionLocalCostParameter = Double.parseDouble((event.asStartElement().getAttributeByName(new QName("value")).getValue()));
										driver.setCollectionLocalCostParameter(collectionLocalCostParameter);
									}
									if(event.asStartElement().getName().getLocalPart().equals("collectionConnectionCostParameter")){
										double collectionConnectionCostParameter = Double.parseDouble(event.asStartElement().getAttributeByName(new QName("value")).getValue());
										driver.setCollectionConnectionCostParameter(collectionConnectionCostParameter);
									}
									if(event.asStartElement().getName().getLocalPart().equals("collectionVehicleUtilizationParameter")){
										double collectionVehicleUtilizationParameter = Double.parseDouble(event.asStartElement().getAttributeByName(new QName("value")).getValue());
										driver.setCollectionVehicleUtilizationParameter(collectionVehicleUtilizationParameter);
									}				
								
								}		
								else if(event.isEndElement()){
									
									if(event.asEndElement().getName().getLocalPart().equals("Region")){
										drivers.add(driver);
										break;
									}
								}	
						}
						logger.trace("Spatial cost drivers for region " + driver.getRegion() + " created " + "distributionLocalCostParameter=" + driver.getDistributionLocalCostParameter() + 
								" distributionConnectionCostParameter=" + driver.getDistributionConnectionCostParameter() + " distributionVehicleUtilizationParameter=" +  driver.getDistributionVehicleUtilizationParameter() +
								" collectionLocalCostParameter=" + driver.getCollectionLocalCostParameter() + " collectionConnectionCostParameter=" + driver.getCollectionConnectionCostParameter() +
								" collectionVehicleUtilizationParameter=" + driver.getCollectionVehicleUtilizationParameter());
					}	
				}
			} 
			logger.trace("Spatial cost drivers successfully read");
		}
		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return drivers;
	}
	
	
	
	
}

