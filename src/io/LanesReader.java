package io;

import java.util.Collection;

import scenario.MarketLane;
import scenario.Region;

public interface LanesReader {
	public Collection<MarketLane> getLanesFromFile(Collection <Region> regions);
}
