package io;

import java.util.Collection;

import io.Iteration;
import scenario.MarketScenario;

public interface SimulationDataTracker {
	public void trackData(MarketScenario scenario);
	public Collection<Iteration> getData();
}
