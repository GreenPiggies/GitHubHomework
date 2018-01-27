package roadgraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;
public class MapNode implements Cloneable
{
	GeographicPoint location;
	List<MapEdge> edges;
	
	public MapNode(GeographicPoint location)
	{
		this.location = location;
		edges = new ArrayList<>();
		
	}
	
	public MapEdge getEdge(GeographicPoint other)
	{
		for (MapEdge edge : edges)
		{
			if (edge.getEnd().equals(other))
			{
				return edge.clone();
			}
		}
		return null;
	}
	
	public List<GeographicPoint> getNeighbors()
	{
		ArrayList<GeographicPoint> children = new ArrayList<>();
		for (MapEdge edge : edges)
		{
			children.add(edge.getEnd());
		}
		return children;
	}
	
	public void addEdge(MapEdge edge)
	{
		edges.add(edge);
	}
	
	public GeographicPoint getLocation()
	{
		return (GeographicPoint) location.clone();
	}
	
	public List<MapEdge> getEdges()
	{
		ArrayList<MapEdge> newSet = new ArrayList<>();
		for (MapEdge edge : edges)
		{
			newSet.add((MapEdge) edge.clone());
		}
		
		return newSet;
		
	}
	
	public MapNode clone()
	{
		try
		{
			return (MapNode) super.clone();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
