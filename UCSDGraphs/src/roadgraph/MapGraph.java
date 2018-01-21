/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private List<MapNode> nodes;
	private HashMap<GeographicPoint, MapNode> nodeMap;
	private int numVertices;
	private int numEdges;
	
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		nodes = new ArrayList<MapNode>();
		nodeMap = new HashMap<>();
		numVertices = 0;
		numEdges = 0;
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		Set<GeographicPoint> newSet = new HashSet<>();
		for (MapNode node : nodes)
		{
			newSet.add((GeographicPoint) node.getLocation());
		}
		return newSet;
		
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// Testing if valid bois
		boolean alreadyExists = false;
		for (MapNode node : nodes)
		{
			if (node.getLocation().equals(location))
			{
				alreadyExists = true;
				break;
			}
		}
	
		if (location != null && !alreadyExists)
		{
			//Let's add it
			MapNode newNode = new MapNode(location);
			nodes.add(newNode);
			nodeMap.put(location, newNode);
			numVertices++;
			return true;
		}
		
		
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException 
	{
		boolean presentFrom = false;
		boolean presentTo = false;
		for (MapNode node : nodes)
		{
			if (node.getLocation().equals(from))
			{
				presentFrom = true;
			}
			if (node.getLocation().equals(to)) 
			{
				presentTo = true;
			}
			
		}
		if (!presentFrom || !presentTo || roadName == null || roadType == null || length < 0)
		{
			throw new IllegalArgumentException();
		} else
		{
			MapEdge edge = new MapEdge(from, to, roadName, roadType, length);
			nodeMap.get(from).addEdge(edge);
			numEdges++;
		}
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
		
		Queue<GeographicPoint> queue = new LinkedList<GeographicPoint>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<>();
		
		queue.add(start);
		visited.add(start);
		
		while (queue.size() > 0)
		{
			GeographicPoint curr = queue.remove();
			if (curr.equals(goal))
			{
				LinkedList<GeographicPoint> trace = new LinkedList<>();
				GeographicPoint holder = goal;
				trace.addFirst(holder);
				while (holder != start)
				{
					GeographicPoint child = parent.get(holder);
					trace.addFirst(child);
					holder = child;
				}
				return trace;
			}
			for (GeographicPoint neighbor : nodeMap.get(curr).getNeighbors())
			{
				if (!visited.contains(neighbor))
				{
					visited.add(neighbor);
					parent.put(neighbor, curr); //child, parent
					queue.add(neighbor);
				}
			}
		}
		return null;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	/*public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		Map<GeographicPoint, Double> shortestDistance = new HashMap<GeographicPoint, Double>();
		Map<GeographicPoint, GeographicPoint> childToParent = new HashMap<>();
		Comparator<GeographicPoint> idComparator = new DistanceComparator(shortestDistance);
		PriorityQueue<GeographicPoint> queue = new PriorityQueue<GeographicPoint>(idComparator);
		Set<GeographicPoint> visited = new HashSet<GeographicPoint>();
		for (GeographicPoint key : nodeMap.keySet())
		{
			shortestDistance.put(key, Double.MAX_VALUE);
		}		
		
		shortestDistance.put(start, 0.0);
		
		queue.add(start);
		
		int removed = 0;
		
		while (queue.size() > 0)
		{
			GeographicPoint curr = queue.remove();
			removed++;
			nodeSearched.accept(curr);
			if (!visited.contains(curr))
			{
				//System.out.println
				//("Parent: " + curr);
				visited.add(curr);
				if (curr.equals(goal))
				{
					//System.out.println("Hi! I am here.");
					LinkedList<GeographicPoint> trace = new LinkedList<>();
					GeographicPoint holder = goal;
					trace.addFirst(holder);
					while (holder != start)
					{
						GeographicPoint child = childToParent.get(holder);
						trace.addFirst(child);
						holder = child;
					}
					System.out.println("Dijkstra: " + removed);

					//System.out.println("Trace: " + trace);
					//System.out.println("------------------------------------------------------------------------------------------");
					
					return trace;
				}
				int counter = 0;
				for (GeographicPoint neighbor : nodeMap.get(curr).getNeighbors())
				{
					if (!visited.contains(neighbor))
					{
						double newDistance = neighbor.distance(curr) + shortestDistance.get(curr);
						double oldDistance = shortestDistance.get(neighbor);
						if (newDistance < oldDistance)
						{
							//System.out.print(" Child Number " + counter + " : " + neighbor + " ");
							//System.out.print(" newDistance: " + newDistance);
							//System.out.println(" oldDistance: " + oldDistance);
							shortestDistance.put(neighbor, newDistance);
							childToParent.put(neighbor, curr);
							queue.add(neighbor);
							counter++;
						}
					}
				}
			}
			
		}
		return null;

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
	}
	*/
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		Map<GeographicPoint, Double> shortestDuration = new HashMap<GeographicPoint, Double>();
		Map<GeographicPoint, GeographicPoint> childToParent = new HashMap<>();
		Comparator<GeographicPoint> idComparator = new DistanceComparator(shortestDuration);
		PriorityQueue<GeographicPoint> queue = new PriorityQueue<GeographicPoint>(idComparator);
		Set<GeographicPoint> visited = new HashSet<GeographicPoint>();
		for (GeographicPoint key : nodeMap.keySet())
		{
			shortestDuration.put(key, Double.MAX_VALUE);
		}		
		
		shortestDuration.put(start, 0.0);
		
		queue.add(start);
		
		int removed = 0;
		
		while (queue.size() > 0)
		{
			GeographicPoint curr = queue.remove();
			removed++;
			nodeSearched.accept(curr);
			if (!visited.contains(curr))
			{
				//System.out.println
				//("Parent: " + curr);
				visited.add(curr);
				if (curr.equals(goal))
				{
					//System.out.println("Hi! I am here.");
					LinkedList<GeographicPoint> trace = new LinkedList<>();
					GeographicPoint holder = goal;
					trace.addFirst(holder);
					while (holder != start)
					{
						GeographicPoint child = childToParent.get(holder);
						trace.addFirst(child);
						holder = child;
					}
					System.out.println("Dijkstra: " + removed);

					//System.out.println("Trace: " + trace);
					//System.out.println("------------------------------------------------------------------------------------------");
					
					return trace;
				}
				for (GeographicPoint neighbor : nodeMap.get(curr).getNeighbors())
				{
					if (!visited.contains(neighbor))
					{
						MapEdge sharedEdge = nodeMap.get(curr).getEdge(neighbor);
						double newDuration = sharedEdge.getDuration() + shortestDuration.get(curr);
						double oldDuration = shortestDuration.get(neighbor);
						if (newDuration < oldDuration)
						{
							//System.out.print(" Child Number " + counter + " : " + neighbor + " ");
							//System.out.print(" newDistance: " + newDistance);
							//System.out.println(" oldDistance: " + oldDistance);
							shortestDuration.put(neighbor, newDuration);
							childToParent.put(neighbor, curr);
							queue.add(neighbor);
						}
					}
				}
			}
			
		}
		return null;

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		Map<GeographicPoint, Double> shortestDistance = new HashMap<GeographicPoint, Double>();
		Map<GeographicPoint, GeographicPoint> childToParent = new HashMap<>();
		Comparator<GeographicPoint> idComparator = new AStarComparator(shortestDistance, goal);
		PriorityQueue<GeographicPoint> queue = new PriorityQueue<GeographicPoint>(idComparator);
		Set<GeographicPoint> visited = new HashSet<GeographicPoint>();
		for (GeographicPoint key : nodeMap.keySet())
		{
			shortestDistance.put(key, Double.MAX_VALUE);
		}		
		
		int removed = 0;
		shortestDistance.put(start, 0.0);
		
		queue.add(start);
		
		while (queue.size() > 0)
		{
			GeographicPoint curr = queue.remove();
			removed++;
			nodeSearched.accept(curr);
			if (!visited.contains(curr))
			{
				//System.out.println
				//("Parent: " + curr);
				visited.add(curr);
				if (curr.equals(goal))
				{
					//System.out.println("Hi! I am here.");
					LinkedList<GeographicPoint> trace = new LinkedList<>();
					GeographicPoint holder = goal;
					trace.addFirst(holder);
					while (holder != start)
					{
						GeographicPoint child = childToParent.get(holder);
						trace.addFirst(child);
						holder = child;
					}
					//System.out.println("Trace: " + trace);
					//System.out.println("------------------------------------------------------------------------------------------");
					System.out.println("A*: " + removed);
					return trace;
				}
				for (GeographicPoint neighbor : nodeMap.get(curr).getNeighbors())
				{
					if (!visited.contains(neighbor))
					{
						double newDistance = neighbor.distance(curr) + shortestDistance.get(curr);
						double oldDistance = shortestDistance.get(neighbor);
						if (newDistance < oldDistance)
						{
							//System.out.print(" Child Number " + counter + " : " + neighbor + " ");
							//System.out.print(" newDistance: " + newDistance);
							//System.out.println(" oldDistance: " + oldDistance);
							shortestDistance.put(neighbor, newDistance);
							childToParent.put(neighbor, curr);
							queue.add(neighbor);
						}
					}
				}
				//System.out.println();
			}
			
		}
		return null;

		
	}

	
	
	public static void main(String[] args)
	{
		/*System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		*/
		
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);

		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
