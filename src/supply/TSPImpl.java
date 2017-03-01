package supply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import controler.MarketControler;
import demand.Shipment;

public class TSPImpl implements TSP{

	private static final Logger logger = Logger.getLogger(TSPImpl.class.getName());
	
	private String id;
	private TSPTariffTable tariffTable;
	private Collection <TSPLane> lanes;
	private Collection <Facility> facilities;
	private HashMap <String,TSPRegion> tspRegions;
	private TSPType type;
	private LinearOfferMaker offerMaker;
	private ShipmentAssigner shipmentAssigner;
	private ShipmentRemover shipmentRemover;
	private HashMap<String,TSPTariffTableEntry> costMap;
	private CostDriverUpdater costDriverUpdater;
	private CostCalculator costCalculator;
	private PriceUpdater priceUpdater;
	private CostMapUpdater costMapUpdater;
	
	public TSPImpl (){
		tariffTable = new TSPTariffTableImpl();
		lanes = new ArrayList <TSPLane>();
		facilities = new ArrayList <Facility>();
		tspRegions = new HashMap <String,TSPRegion>();
		costMap = new HashMap<String,TSPTariffTableEntry>();
		offerMaker = new LinearOfferMaker(this);
		shipmentAssigner = new ShipmentAssigner(this);
		shipmentRemover = new ShipmentRemover(this);
		costCalculator = new LinearCostCalculatorImpl(this);
		priceUpdater = new CostBasedPriceUpdater(this);
		costMapUpdater = new LinearCostMapUpdater(this);
		costDriverUpdater = new RegressionCostDriverUpdater(this);
	}
	
	public Offer makeOffer(Shipment shipment){
		return offerMaker.makeOffer(shipment);		
	}

	public void assignShipment(Shipment shipment){
		shipmentAssigner.assignShipment(shipment);
	}
	
	public void clearShipments(){
		shipmentRemover.removeShipments();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TSPTariffTable getTariffTable() {
		return tariffTable;
	}

	public void setTariffTable(TSPTariffTable tariffTable) {
		this.tariffTable = tariffTable;
	}

	public Collection<TSPLane> getLanes() {
		return lanes;
	}

	public void setLanes(Collection<TSPLane> lanes) {
		this.lanes = lanes;
	}

	public Collection<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Collection<Facility> facilities) {
		this.facilities = facilities;
	}

	public TSPType getType() {
		return type;
	}

	public void setType(TSPType type) {
		this.type = type;
	}

	public HashMap <String,TSPRegion> getTspRegions() {
		return tspRegions;
	}


	public HashMap<String, TSPTariffTableEntry> getCostMap() {
	
		return costMap;
	}
	
	public void calculateCosts(){
		costCalculator.calculateCosts();
		costMapUpdater.updateCostMap();
	}
	
	public void updatePrices(){
		priceUpdater.updatePrices();
	}

	@Override
	public void updateCostDrivers() {
		costDriverUpdater.updateCostDrivers();
		
	}
	

}

