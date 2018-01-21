package roadgraph;

import java.util.Comparator;
import java.util.Map;

import geography.GeographicPoint;

public class DistanceComparator implements Comparator<GeographicPoint> {

	
	private Map<GeographicPoint, Double> shortestDuration;
	public DistanceComparator(Map<GeographicPoint, Double> childToParent) //child to parent
	{
		this.shortestDuration = childToParent;
	}
	@Override
	public int compare(GeographicPoint o1, GeographicPoint o2) {
		
		
		return shortestDuration.get(o1) < shortestDuration.get(o2) ? -1 : 1;
	}
	
}
