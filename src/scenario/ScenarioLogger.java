package scenario;

import java.util.ArrayList;
import java.util.Collection;

public interface ScenarioLogger {

	public void logMarketRound();
	public Collection<ScenarioLog> getScenarioLogs();
}
