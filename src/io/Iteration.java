package io;

import io.IterationDemand;

public interface Iteration {
	public long getIterationNumber();
	public void setIterationNumber(long number);
	public IterationSupply getSupply();
	public IterationDemand getDemand();
	public IterationMarket getMarket();
	public IterationNewcomer getNewcomer(); 
}
