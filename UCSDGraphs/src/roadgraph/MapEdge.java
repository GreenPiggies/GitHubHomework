package roadgraph;
import java.util.ArrayList;
//Highway: 55
//Normal: 35
//Expressway: 45
//Neighborhood: 25
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;
public class MapEdge implements Cloneable
{
	private GeographicPoint start;
	private GeographicPoint end;
	private String name;
	private String type;
	private double length; //In miles
	
	public MapEdge(GeographicPoint start, GeographicPoint end, String name, String type, double length)
	{
		this.start = start;
		this.end = end;
		this.name = name;
		this.type = type;
		this.length = length;
	}
	
	public double getDuration()
	{
		int mph = 0;
		if (type.equals("highway"))
		{
			mph = 55;
		} else if (type.equals("normal"))
		{
			mph = 35;
		} else if (type.equals("expressway"))
		{
			mph = 45;
		} else 
		{
			mph = 25;
		}
		return length / mph;
	}
	
	public MapEdge clone()
	{
		try
		{
			return (MapEdge) super.clone();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public GeographicPoint getStart() {
		return (GeographicPoint) start.clone();
	}


	public GeographicPoint getEnd() {
		return (GeographicPoint) end.clone();
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public double getLength() {
		return length;
	}

	
	
}
