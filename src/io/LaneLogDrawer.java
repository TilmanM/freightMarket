package io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.lang.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import scenario.LaneIterationInfoSet;
import scenario.ScenarioLog;
import scenario.loggedCategory;

public class LaneLogDrawer extends ScenarioLogDrawer {

	
	public LaneLogDrawer(Collection<ScenarioLog> scenarioLogs){
		super(scenarioLogs);
	}
	
	@Override
	public JFreeChart createChart(String laneId, loggedCategory category) {
		Collection<LaneIterationInfoSet> infoCollection =   getInfos(laneId,category);
		CategoryDataset dataset = createDataset(infoCollection);
		return getChart(dataset,laneId,category);
	}
	
	private Collection<LaneIterationInfoSet> getInfos(String laneId, loggedCategory category){
		ArrayList <LaneIterationInfoSet> infoList = new ArrayList <LaneIterationInfoSet>();
		int iterationNumber = 0;
		for(ScenarioLog scenLog : scenarioLogs){
			LaneIterationInfoSet infoSet = new LaneIterationInfoSet();
			infoSet = scenLog.getLaneIterationInfos(laneId, category);
			infoSet.setIterationNumber(iterationNumber);
			infoList.add(infoSet);
			iterationNumber++;
		}
		return infoList;
	}
	
	
	private CategoryDataset createDataset(Collection<LaneIterationInfoSet> iterationInfos){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double value;
		
		for(LaneIterationInfoSet iterationInfo : iterationInfos){
			if(iterationInfo.getElement().getValue().getDataTypeClass().getSuperclass().getSimpleName().equals("Number")){
				value = new Double(iterationInfo.getElement().getValue().getValue().toString());
				dataset.addValue(value, new Integer(0), new Integer(iterationInfo.getIterationNumber()));
			}
			
		}
		return dataset;
	}

	private JFreeChart getChart(final CategoryDataset dataset, String laneId, loggedCategory category) {
        
        JFreeChart chart = ChartFactory.createLineChart(
            category.toString() + " on Lane " + laneId,       
            "Iteration",                   
            category.toString(),                   
            dataset,                   
            PlotOrientation.VERTICAL,  
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );
        return chart;
	} 
}
