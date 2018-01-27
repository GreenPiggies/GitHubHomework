package roadgraph;

import java.util.Comparator;
import java.util.Map;

import geography.GeographicPoint;

public class AStarComparator implements Comparator<GeographicPoint> {

	private Map<GeographicPoint, Double> shortestDistance;
	private GeographicPoint goal;
	public AStarComparator(Map<GeographicPoint, Double> shortestDistance, GeographicPoint goal) //child to parent
	{
		this.shortestDistance = shortestDistance;
		this.goal = goal;
	}
	@Override
	public int compare(GeographicPoint o1, GeographicPoint o2) {
		
		int firstLevel;
		int secondLevel;
		
			
		return shortestDistance.get(o1) + o1.distance(goal) < 
				shortestDistance.get(o2) + o2.distance(goal) ? -1 : 1;
	}
	/*private int getLevel(GeographicPoint point) 
	{
		int level = 0;
		GeographicPoint holder = point;
		while (holder != null)
		{
			holder = childToParent.get(holder);
			level++;
		}
		return level;
		
		
	} */

}
