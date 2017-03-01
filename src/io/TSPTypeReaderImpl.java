
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

import utils.Coordinate;
import utils.CoordinateImpl;
import demand.Company;
import demand.CompanyImpl;
import demand.Flow;
import demand.FlowImpl;
import scenario.MarketScenario;
import scenario.Region;
import scenario.RegionImpl;
import supply.SimpleFacilityType;
import supply.SimpleTSPResourcesImpl;
import supply.SimpleTSPTypeImpl;
import supply.SimpleTSPVehicleType;
import supply.TSPResources;
import supply.TSPType;

/*Hier muss noch ein entsprechendes Pattern rein, sonst wird es bei mehreren TSP typen zu unübersichtlich!*/

public class TSPTypeReaderImpl implements TSPTypeReader{

	private static final Logger logger = Logger.getLogger(TSPTypeReaderImpl.class.getName());
	
	private Collection<TSPType> tspTypes;
	private String tspTypesFilePath;
	private XMLEventReader xmlEventReader;
	
	
	public TSPTypeReaderImpl(String tspTypesFilePath){
		this.tspTypesFilePath = tspTypesFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		tspTypes = new ArrayList<TSPType>();
		try {
			fileInputStream = new FileInputStream(tspTypesFilePath);
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

	public Collection<TSPType> getTSPTypesFromFile(){
		logger.trace("Start reading tsp types from file: " + this.tspTypesFilePath);
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();
				
				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("TSPType")){
						SimpleTSPTypeImpl simpleTSPType = new SimpleTSPTypeImpl(startElement.getAttributeByName(new QName("type")).getValue());
						logger.trace("Start creating TSP type :" + simpleTSPType.getTspType());
						do{
							event = xmlEventReader.nextEvent();
							if(event.isStartElement()){
								startElement = event.asStartElement();
								
								if(startElement.getName().getLocalPart().equals("Resource")){
									logger.trace("Start creating resources of: " + simpleTSPType.getTspType());
									if(startElement.getAttributeByName(new QName("resourceType")).getValue().equals("SimpleFacility")){
										SimpleFacilityType facilityType = new SimpleFacilityType();
										String resourceType = startElement.getAttributeByName(new QName("resourceType")).getValue();
										facilityType.setFacilityType(resourceType);
										long capacity = Long.parseLong(startElement.getAttributeByName(new QName("capacity")).getValue());
										facilityType.setCapacity(capacity);
										long capacityIncrement = Long.parseLong(startElement.getAttributeByName(new QName("increment")).getValue());
										facilityType.setIncrement(capacityIncrement);
										String facilityTypeName = startElement.getAttributeByName(new QName("name")).getValue();
										facilityType.setName(facilityTypeName);
										double fixed = Double.parseDouble(startElement.getAttributeByName(new QName("fixed")).getValue());
										facilityType.setFixed(fixed);
										double incrementCosts = Double.parseDouble(startElement.getAttributeByName(new QName("incrementCosts")).getValue());
										facilityType.setIncrementCosts(incrementCosts);
										double minimalUtilization = Double.parseDouble(startElement.getAttributeByName(new QName("minimalUtilization")).getValue());
										simpleTSPType.setMinimalUtilization(minimalUtilization);
										simpleTSPType.getResources().setFacilityType(facilityType);
										logger.trace("Created  " + facilityType.getFacilityType() + " capacity:"+ facilityType.getCapacity() + " increment:" + facilityType.getIncrement()
										+ " name:" + facilityType.getName() + " fixed:" + facilityType.getFixed() + " minimal Uilization:" + simpleTSPType.getMinimalUtilization());
									}
									if(startElement.getAttributeByName(new QName("resourceType")).getValue().equals("localTSPVehicle")){
										SimpleTSPVehicleType localVehicleType = new SimpleTSPVehicleType();
										String vehicleResourceType = startElement.getAttributeByName(new QName("resourceType")).getValue();
										localVehicleType.setVehicleType(vehicleResourceType);
										String vehicleTypeName = startElement.getAttributeByName(new QName("name")).getValue();
										localVehicleType.setName(vehicleTypeName);
										long capacity = Long.parseLong(startElement.getAttributeByName(new QName("capacity")).getValue());
										localVehicleType.setCapacity(capacity);
										double fixed = Double.parseDouble(startElement.getAttributeByName(new QName("fixed")).getValue());
										localVehicleType.setFixed(fixed);
										double linear = Double.parseDouble(startElement.getAttributeByName(new QName("linear")).getValue());
										localVehicleType.setLinear(linear);
										simpleTSPType.getResources().setLocalVehicleType(localVehicleType);
										logger.trace("Created  " + localVehicleType.getVehicleType() + " capacity:"+ localVehicleType.getCapacity() +  " name:" + localVehicleType.getName()
										+ " fixed:" + localVehicleType.getFixed() + " linear:" + localVehicleType.getLinear());
									}
									if(startElement.getAttributeByName(new QName("resourceType")).getValue().equals("mainRunTSPVehicle")){							
										SimpleTSPVehicleType mainRunVehicleType = new SimpleTSPVehicleType();
										String vehicleTypeName = startElement.getAttributeByName(new QName("resourceType")).getValue();
										mainRunVehicleType.setName(vehicleTypeName);
										String vehicleResourceType = startElement.getAttributeByName(new QName("resourceType")).getValue();
										mainRunVehicleType.setVehicleType(vehicleResourceType);
										long capacity = Long.parseLong(startElement.getAttributeByName(new QName("capacity")).getValue());
										mainRunVehicleType.setCapacity(capacity);
										double fixed = Double.parseDouble(startElement.getAttributeByName(new QName("fixed")).getValue());
										mainRunVehicleType.setFixed(fixed);
										double linear = Double.parseDouble(startElement.getAttributeByName(new QName("linear")).getValue());
										mainRunVehicleType.setLinear(linear);
										simpleTSPType.getResources().setMainRunVehicleType(mainRunVehicleType);
										logger.trace("Created  " + mainRunVehicleType.getVehicleType() + " capacity:"+ mainRunVehicleType.getCapacity() +  " name:" + mainRunVehicleType.getName()
										+ " fixed:" + mainRunVehicleType.getFixed() + " linear:" + mainRunVehicleType.getLinear());
									}						
								}
							}
							if(event.isEndElement()){
								if(event.asEndElement().getName().getLocalPart().equals("TSPType")){
									tspTypes.add(simpleTSPType);
									break;
								}
							}
						}while(true);
						
						
					}

				}	
			}
			logger.trace("Tsp types successfully read from file");
		} 

		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return tspTypes;
	}



}

