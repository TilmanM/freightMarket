
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;

import demand.Company;
import demand.CompanyImpl;
import demand.DemandBehaviorParameters;
import demand.DemandBehaviorParametersImpl;
import demand.Flow;
import demand.FlowImpl;
import scenario.MarketScenario;

public class FlowParameterReaderImpl implements FlowParameterReader{

	private static final Logger logger = Logger.getLogger(FlowParameterReaderImpl.class.getName());
	
	private DemandBehaviorParameters parameters;
	private String flowsParameterPath;
	private XMLEventReader xmlEventReader;

	public FlowParameterReaderImpl(String flowsParameterPath){
		this.flowsParameterPath = flowsParameterPath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		parameters = new DemandBehaviorParametersImpl();
		try {
			fileInputStream = new FileInputStream(flowsParameterPath);
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

	public DemandBehaviorParameters getFlowParametersFromFile(){
		logger.trace("Start to read behavior parameters from file " + flowsParameterPath);
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals("alpha")){
						parameters.setAlpha(Double.parseDouble(startElement.getAttributeByName(new QName("value")).getValue()));
						logger.trace("Behavior parameter alpha= " + parameters.getAlpha() );
					}	
					if(startElement.getName().getLocalPart().equals("deltaEMC")){
						parameters.setDeltaEMC(Double.parseDouble(startElement.getAttributeByName(new QName("value")).getValue()));
						logger.trace("Behavior parameter deltaEMC= " + parameters.getDeltaEMC());
					}
				}	
							
			}
			logger.trace("Behavior parameters successfully read from file");
		}
		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return parameters;
	}

}
