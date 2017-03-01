package io;

import scenario.MarketScenario;
import supply.MarketTariffTable;
import supply.MarketTariffTableImpl;

public class IterationMarketImpl implements IterationMarket {

	private MarketTariffTable tariffTable;
	
	public IterationMarketImpl(MarketScenario scenario){
		this.tariffTable = new MarketTariffTableImpl(scenario.getTariffTable().getTariffs());
	}

	public MarketTariffTable getTariffTable() {
		return tariffTable;
	}
	
	
}
