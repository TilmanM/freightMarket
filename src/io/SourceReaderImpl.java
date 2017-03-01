
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;

public class SourceReaderImpl {
private static final Logger logger = Logger.getLogger(SourceReaderImpl.class.getName());
	
	private String lanesFilePath;
	private String regionsFilePath;
	private String flowsFilePath;
	private String companiesFilePath;
	private String tariffsFilePath;
	private String tspTypesFilePath;
	private String flowParameterFilePath;
	private String spatialCostDriversFilePath;
	private String mainRunCostDriversFilePath;
	
	private String sourceFilePath;
	private XMLEventReader xmlEventReader; 
	
	public SourceReaderImpl(String sourceFilePath){
		this.sourceFilePath = sourceFilePath;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(sourceFilePath);
			try {
				xmlEventReader = factory.createXMLEventReader(fileInputStream);
			} catch (XMLStreamException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public String getLanesFilePath() {
		return lanesFilePath;
	}
	public String getRegionsFilePath() {
		return regionsFilePath;
	}
	public String getFlowsFilePath() {
		return flowsFilePath;
	}
	public String getCompaniesFilePath() {
		return companiesFilePath;
	}
	public String getInitialTariffTableFilePath() {
		return tariffsFilePath;
	}
	public String getTspTypesFilePath() {
		return tspTypesFilePath;
	}
	public String getFlowParameterFilePath() {
		return flowParameterFilePath;
	}
	public String getSpatialCostDriversFilePath() {
		return spatialCostDriversFilePath;
	}
	public String getMainRunCostDriversFilePath() {
		return mainRunCostDriversFilePath;
	}

	public void readSources(){
		logger.trace("Start reading source file paths from file " + sourceFilePath); 
		try {
			while(xmlEventReader.hasNext()){
				XMLEvent event;
				event = xmlEventReader.nextEvent();
		
				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();
					
					if(startElement.getName().getLocalPart().equals("LanesFile")){
						this.lanesFilePath = startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to lanes file: " + this.lanesFilePath); 
					}
					else if(startElement.getName().getLocalPart().equals("RegionsFile")){
						this.regionsFilePath = startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to regions file: " + this.regionsFilePath); 
					}
					else if(startElement.getName().getLocalPart().equals("CompaniesFile")){
						this.companiesFilePath = startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to companies file: " + this.companiesFilePath);  
					}
					else if(startElement.getName().getLocalPart().equals("FlowsFile")){
						this.flowsFilePath = startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to flows file: " + this.flowsFilePath); 
					}
					else if(startElement.getName().getLocalPart().equals("InitialTariffsFile")){
						this.tariffsFilePath = startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to tariffs file: " + this.tariffsFilePath);
					}
					else if(startElement.getName().getLocalPart().equals("TSPTypesFile")){
						this.tspTypesFilePath = startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to tsp types file: " + this.tspTypesFilePath);
					}
					else if(startElement.getName().getLocalPart().equals("FlowParametersFile")){
						this.flowParameterFilePath= startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to flow parameters file: " + this.flowParameterFilePath);
					}
					else if(startElement.getName().getLocalPart().equals("SpatialCostDriversFile")){
						this.spatialCostDriversFilePath= startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to spatial cost drivers file: " + this.spatialCostDriversFilePath);
					}				
					else if(startElement.getName().getLocalPart().equals("MainRunCostDriversFile")){
						this.mainRunCostDriversFilePath= startElement.getAttributeByName(new QName("path")).getValue();
						logger.trace("Path to main run cost drivers file: " + this.mainRunCostDriversFilePath); 
					}	
				}	
			}
			logger.trace("Source file paths successfully read"); 
		} 
			
			
		catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
			
	}
			 
	
 }
	


