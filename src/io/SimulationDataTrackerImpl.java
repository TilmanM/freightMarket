package io;

import java.util.ArrayList;
import java.util.Collection;

import io.Iteration;
import io.IterationImpl;
import scenario.MarketScenario;

public class SimulationDataTrackerImpl implements SimulationDataTracker {

private ArrayList<Iteration> iterations;


	public SimulationDataTrackerImpl(){
		this.iterations = new ArrayList<Iteration>();
	}
	
		
	public void trackData(MarketScenario scenario) {
		IterationImpl iteration= new IterationImpl(scenario);
		iteration.setIterationNumber(iterations.size()+1);
		iterations.add(iteration);
	}


	public Collection<Iteration> getData() {
		return this.iterations;
	}

	
}
