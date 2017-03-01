package io;

import scenario.MarketScenario;

public class IterationImpl implements Iteration {

	private long iterationNumber;
	private IterationSupply supply;
	private IterationDemand demand;
	private IterationNewcomer newcomer;
	private IterationMarket market;
	
	
	public IterationImpl(MarketScenario scenario){
		this.demand = new IterationDemandImpl(scenario);
		this.supply = new IterationSupplyImpl(scenario, demand);
		this.newcomer = new IterationNewcomerImpl(scenario, demand);
		this.market = new IterationMarketImpl(scenario);
	}
	
	
	public long getIterationNumber(){
		return iterationNumber;
	}
	
	public IterationSupply getSupply() {
		return supply;
	}

	public IterationDemand getDemand() {
		return demand;
	}
	
	public IterationNewcomer getNewcomer() {
		return newcomer;
	}

	public IterationMarket getMarket() {
		return market;
	}

	public void setIterationNumber(long number){
		this.iterationNumber = number;
	}
}
