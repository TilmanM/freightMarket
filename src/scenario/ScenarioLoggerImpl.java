
package scenario;

import java.util.ArrayList;
import java.util.Collection;

public class ScenarioLoggerImpl implements ScenarioLogger {

	private MarketScenario scenario;
	private ArrayList<ScenarioLog> logList;
	
	public ScenarioLoggerImpl (MarketScenario scenario){
		this.scenario = scenario;
		this.logList = new ArrayList<ScenarioLog>();
	}
	
	public void logMarketRound(){
		ScenarioLog log = new ScenarioLog(this.scenario);
		logList.add(log);
	}

	public Collection<ScenarioLog> getScenarioLogs(){
		return logList;
	}
}	

