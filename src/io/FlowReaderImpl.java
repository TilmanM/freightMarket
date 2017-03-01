
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import demand.Company;
import demand.CompanyImpl;
import demand.Flow;
import demand.FlowImpl;
import scenario.MarketScenario;


public class FlowReaderImpl implements FlowReader {

	private static final Logger logger = Logger.getLogger(FlowReaderImpl.class.getName());
	
	private Collection<Flow> flows;
	private HashMap<String,Company> companyMap;
	private String flowsFilePath;
	private XMLEventReader xmlEventReader;

	public FlowReaderImpl(String flowsFilePath){
		this.flowsFilePath =flowsFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		flows = new ArrayList<Flow>();
		try {
			fileInputStream = new FileInputStream(flowsFilePath);
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

	public Collection<Flow> getFlowsFromFile(Collection <Company> companies){
		logger.trace("Start readeing flows from file: " + flowsFilePath);

		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("Company")){
						Company fromCompany = new CompanyImpl();
						for(Company company : companies){
							if(startElement.getAttributeByName(new QName("id")).getValue().equals(company.getId())){
								fromCompany = company;

							}
						} 					
						int i = 1;
						do{
							event = xmlEventReader.nextEvent();
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals("Customer")){
									
									Flow flow = new FlowImpl();
									Company toCompany = new CompanyImpl();
									flow.setShipper(fromCompany);
									for(Company company : companies){
										if(event.asStartElement().getAttributeByName(new QName("id")).getValue().equals(company.getId())){
											toCompany = company;
											flow.setRecipient(toCompany);
										}
									}	

									flow.setStrength(Double.parseDouble(event.asStartElement().getAttributeByName(new QName("monthlyWt")).getValue()));
									flow.setId("From " + fromCompany.getId()+ " to "+toCompany.getId()+" flow "+i);
									logger.trace("Flow " + flow.getId() + " created");
									i++;
									flows.add(flow);
								}
							}
							if(event.isEndElement()){
								if(event.asEndElement().getName().getLocalPart().equals("Company")){
									break;
								}
							}
						}while(true);





					}	
				}
			}
			logger.trace("Successfully read all flows from file");
		}
		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return flows;
	}


}

