
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

import utils.Coordinate;
import utils.CoordinateImpl;
import scenario.MarketLane;
import scenario.MarketLaneImpl;
import scenario.Region;
import demand.Company;
import demand.CompanyImpl;





public class CompanyReaderImpl {

	private static final Logger logger = Logger.getLogger(CompanyReaderImpl.class.getName());
	
	private Collection<Company> companies;
	private String companiesFilePath;
	private XMLEventReader xmlEventReader;
	
	public CompanyReaderImpl(String companiesFilePath){
		this.companiesFilePath =companiesFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		companies = new ArrayList<Company>();
		try {
			fileInputStream = new FileInputStream(companiesFilePath);
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
	
	public Collection<Company> getCompaniesFromFile(Collection <Region> regions){
		logger.trace("Start reading companies from file " + this.companiesFilePath);
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("Company")){
						Company company = new CompanyImpl();
						company.setId(startElement.getAttributeByName(new QName("id")).getValue());
						logger.trace("Company " + company.getId() + " created");
						double x = Double.parseDouble(startElement.getAttributeByName(new QName("lat")).getValue());
						double y = Double.parseDouble(startElement.getAttributeByName(new QName("lon")).getValue());
						Coordinate companyCoord = new CoordinateImpl();
						companyCoord.setX(x);
						companyCoord.setY(y);
						company.setCoord(companyCoord);
						logger.trace("Coordinates " + company.getCoord().getX() + ";" + company.getCoord().getY()  + " added to company " + company.getId());
						Iterator <Region> regionsIterator = regions.iterator();
						while(regionsIterator.hasNext()){
							Region region = regionsIterator.next();
							if(region.getId().equals(startElement.getAttributeByName(new QName("zone")).getValue())){
								company.setRegion(region);
								logger.trace("Region " + company.getRegion().getId() + " added to company " + company.getId());
							}
						}
							companies.add(company);
					}

				}	
			}
			logger.trace("Companies successfully read from file");
		} 

		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return companies;
	}
	
}

