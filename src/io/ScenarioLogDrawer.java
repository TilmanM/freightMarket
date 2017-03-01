package io;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import scenario.ScenarioLog;
import scenario.loggedCategory;

public abstract class ScenarioLogDrawer {
	
	protected Collection<ScenarioLog> scenarioLogs;
	
	public ScenarioLogDrawer(Collection<ScenarioLog> scenarioLogs){
		this.scenarioLogs = scenarioLogs;
	}
	
	public abstract JFreeChart createChart(String laneId, loggedCategory category);
	
	public void PlotChart(JFreeChart chart, String filePath, int width, int height){
		File file = new File(filePath);
		try {
			ChartUtilities.saveChartAsPNG(file, chart, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
