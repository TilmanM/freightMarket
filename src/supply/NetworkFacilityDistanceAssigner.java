package supply;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.network.NodeImpl;
import org.matsim.core.router.Dijkstra;
import org.matsim.core.router.DijkstraTest;
import org.matsim.core.router.costcalculators.FreespeedTravelTimeAndDisutility;
import org.matsim.core.router.util.LeastCostPathCalculator;
import org.matsim.core.trafficmonitoring.FreeSpeedTravelTime;

public class NetworkFacilityDistanceAssigner implements FacilityDistanceAssigner{

	Dijkstra routingAlgo;
	
	public NetworkFacilityDistanceAssigner(Network network){
		FreespeedTravelTimeAndDisutility costFunction = new FreespeedTravelTimeAndDisutility(-1,1,-999);
		FreeSpeedTravelTime travelTime = new FreeSpeedTravelTime();
		this.routingAlgo = new Dijkstra(network, costFunction,travelTime); 
	}
	
	
	@Override
	public double assignDistance(Facility fromFacility, Facility toFacility) {

		Node fromNode = new NodeImpl(Id.createNodeId(fromFacility.getId()));
		fromNode.getCoord().setX(fromFacility.getCoord().getX());
		fromNode.getCoord().setY(fromFacility.getCoord().getY());
		
		Node toNode = new NodeImpl(Id.createNodeId(toFacility.getId()));
		toNode.getCoord().setX(toFacility.getCoord().getX());
		toNode.getCoord().setY(toFacility.getCoord().getY());
		
		LeastCostPathCalculator.Path path = routingAlgo.calcLeastCostPath(fromNode, toNode, 0, null, null);
	
		double distance = 0;
	
		for(Link link : path.links){
			distance = distance + link.getLength();
		}
		return distance;
	}

}
