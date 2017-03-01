
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
import scenario.MarketScenario;
import scenario.Region;
import scenario.RegionImpl;
import supply.MarketTariffTableEntry;
import supply.MarketTariffTableEntryImpl;

public class InitialTariffTableReaderImpl implements InitialTariffTableReader {

	private static final Logger logger = Logger.getLogger(InitialTariffTableReaderImpl.class.getName());
	
	private Collection<MarketTariffTableEntry> entries;

	private String tariffsFilePath;
	private XMLEventReader xmlEventReader;


	public InitialTariffTableReaderImpl(String tariffsFilePath){
		this.tariffsFilePath = tariffsFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		entries = new ArrayList<MarketTariffTableEntry>();
		try {
			fileInputStream = new FileInputStream(tariffsFilePath);
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


	public Collection<MarketTariffTableEntry> getTariffsFromFile(Collection<Region> regions){
		logger.trace("Start reading initial traiffs from file: " + tariffsFilePath);
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("Tariff")){
						MarketTariffTableEntryImpl entry = new MarketTariffTableEntryImpl();
							
						for(Region region : regions){
							if(region.getId().equals(startElement.getAttributeByName(new QName("fromRegion")).getValue())){
								entry.setFromRegion(region);
							}
							if(region.getId().equals(startElement.getAttributeByName(new QName("toRegion")).getValue())){
								entry.setToRegion(region);
							}
						}				
						entry.setId(entry.getFromRegion().getId()+entry.getToRegion().getId());
						logger.trace("Market tariff table entry from region " + entry.getFromRegion().getId() + " to region " + entry.getToRegion().getId() + "created");
						double constant = Double.parseDouble(startElement.getAttributeByName(new QName("constant")).getValue());
						entry.setConstant(constant);
						logger.trace("Fixed component: " + entry.getConstant());
						double linear = Double.parseDouble(startElement.getAttributeByName(new QName("linear")).getValue());
						logger.trace("Linear component: " + entry.getLinear());
						entry.setLinear(linear);
						entries.add(entry);
					}

				}	
			}
			logger.trace("Initial tariffs successfully read");
		} 

		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return entries;
	}
	
	
	
}

