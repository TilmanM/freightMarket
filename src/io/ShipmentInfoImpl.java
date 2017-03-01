package io;

import demand.Shipment;

public class ShipmentInfoImpl implements ShipmentInfo {

	public double quantity;
	
	public ShipmentInfoImpl (Shipment shipment){
		this.quantity = shipment.getQuantity();
	}

	public double getQuantity() {
		return quantity;
	}
	
	
}
