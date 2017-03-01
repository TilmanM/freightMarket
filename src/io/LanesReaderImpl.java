
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;

import scenario.MarketDirection;
import scenario.MarketDirectionImpl;
import scenario.MarketLane;
import scenario.MarketLaneImpl;
import scenario.MarketScenario;
import scenario.Region;
import scenario.RegionImpl;
import utils.Coordinate;
import utils.CoordinateImpl;

public class LanesReaderImpl {
	private static final Logger logger = Logger.getLogger(LanesReaderImpl.class.getName());
	
	private Collection<MarketLane> lanes;
    private String lanesFilePath;
	private XMLEventReader xmlEventReader;

	
	public LanesReaderImpl(String lanesFilePath){
		this.lanesFilePath =lanesFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		lanes = new ArrayList<MarketLane>();
		try {
			fileInputStream = new FileInputStream(lanesFilePath);
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
	
	public Collection<MarketLane> getLanesFromFile(Collection <Region> regions){
		logger.trace("Start reading market lanes from file " + lanesFilePath);
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("Lane")){
						MarketLane lane = new MarketLaneImpl();
						lane.setId(startElement.getAttributeByName(new QName("id")).getValue());
						logger.trace("Marke lane " + lane.getId() + " created");
						Iterator <Region> regionsIterator = regions.iterator();
						while(regionsIterator.hasNext()){
							Region region = regionsIterator.next();
							if(region.getId().equals(startElement.getAttributeByName(new QName("firstRegion")).getValue())){
								lane.setFirstRegion(region);
							}
							if(region.getId().equals(startElement.getAttributeByName(new QName("secondRegion")).getValue())){
								lane.setSecondRegion(region);
							}
							else{
								
							}
						}
						lanes.add(lane);
					}

				}	
			}
			logger.trace("Lanes successfully read from file");
		} 

		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return lanes;
	}
	
		
	
	
}

