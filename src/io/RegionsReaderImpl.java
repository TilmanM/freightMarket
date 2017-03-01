
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
import scenario.RegionImpl;
import utils.Coordinate;
import utils.CoordinateImpl;

public class RegionsReaderImpl implements RegionsReader {
	
	private static final Logger logger = Logger.getLogger(RegionsReaderImpl.class.getName());

	private Collection<Region> regions;
	private String regionsFilePath;
	private XMLEventReader xmlEventReader;


	public RegionsReaderImpl(String regionsFilePath){
		this.regionsFilePath = regionsFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		regions = new ArrayList<Region>();
		try {
			fileInputStream = new FileInputStream(regionsFilePath);
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


	public Collection<Region> getRegionsFromFile(){
		logger.trace("Start reading regions from file " + this.regionsFilePath); 
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("Region")){
						RegionImpl roundRegion = new RegionImpl(startElement.getAttributeByName(new QName("id")).getValue());
						logger.trace("Region " + roundRegion.getId() + " created"); 
						Coordinate regionCoord = new CoordinateImpl();
						double x = Double.parseDouble(startElement.getAttributeByName(new QName("x")).getValue());
						regionCoord.setX(x);
						logger.trace("x-coordinate assigned to region " + roundRegion.getId()); 
						double y = Double.parseDouble(startElement.getAttributeByName(new QName("y")).getValue());
						regionCoord.setY(y);
						logger.trace("y-coordinate assigned to region " + roundRegion.getId()); 
						roundRegion.setCoord(regionCoord);
						double radius = Double.parseDouble(startElement.getAttributeByName(new QName("radius")).getValue());
						roundRegion.setRadius(radius);
						regions.add(roundRegion);
					}

				}	
			}
			logger.trace("Regions successfully read from file"); 
		} 

		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return regions;
	}
}


