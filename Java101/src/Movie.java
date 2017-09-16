import java.util.ArrayList;

//Change actor class to castMember class
//call array for movie "cast"
//Make cast an inner class
//Override toString method for castMember class. 

//Be able to add the cast and remove from the cast. 
//Return character from inputting actor.
//Return actor from inputting character. 

/*
 * Create a package called multimedia, put movie in it.
 * Change peopleAct to cast.
 * Three Constructors:
 * Movie(String title): year = 0, castSize = 0;
 * Movie(String title, int releaseYear): castSize = 0;
 * """Movie(String title, int releaseYear, int castSize): initialize all indexes to empty.
 * Methods
 * 	void setCast(int castSize) initialize all indexes to empty. //WIPE CLEAN
 * 	void setCast(CastMember[] cast) //WIPE CLEAN.
 *  void setCastMemberName(int index, String name)//Modifies the CastMember's name
 *  void setCastMemberCharacter(int index, String character) //Modifies the CastMember's character
 *  int findCastMemberByName(String name)
 *  int findCastMemberByCharacter(String character)
 *  boolean containsCastMemberName(String name)
 *  boolean containsCastMemberCharacter(String character)
 *  CastMember getCastMemberByName(String name) //If it cannot be found, return empty CastMember
 *  CastMember getCastMemberByCharacter(String character) //If it cannot be found, return empty CastMember
 *  boolean renameCastMemberName(String oldName, String newName)
 *  boolean renameCastMemberCharacter(String oldCharacter, String newCharacter)
 */
/*
 * Create a new package called data(under src)
 * Create a viewer class.
 * Import multimedia.Movie;
 */
public class Movie 
{
	private String name;
	private int year;
	//Do not make this a string. Hold the actor's name and the character that they play.
	private ArrayList<CastMember> peopleAct; 
	
	/**
	 * Creates a new Movie.
	 * @param name The name of the movie.
	 */
	public Movie(String name)
	{
		this(name, 0);
	}
	
	/**
	 * Creates a new Movie.
	 * @param name The name of the movie.
	 * @param year The year the movie was created.
	 */
	public Movie(String name, int year)
	{
		this.name = name;
		this.year = year;
		peopleAct = new ArrayList<CastMember>(10);
	}
	/**
	 * Gets the name of the movie.
	 * @return The name of the movie.
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Sets the name of the movie.
	 * @param name The string the movie's name will be set to. 
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * Gets the year the movie was released.
	 * @return The year the movie was released.
	 */
	
	public int getYear() 
	{
		return year;
	}
	
	/**
	 * Sets the year the movie was released.
	 * @param year The integer the movie's year will be set to.
	 */
	public void setYear(int year) 
	{
		this.year = year;
	}
	
	/**
	 * Gets the array of actors of the movie. 
	 * @return
	 */
	public ArrayList<CastMember> getActors() 
	{
		return peopleAct;
	}
	
	/**
	 * Sets the array of actors of the movie.
	 * @param peopleAct The array of actors the movie's array of actors will be set to. 
	 */
	public void setPeopleAct(ArrayList<CastMember> peopleAct) 
	{
		this.peopleAct = peopleAct;
	}
	
	public class CastMember
	{
		/**
		 * public CastMember() <-- Both name and characterName become null.
		 * 
		 */
		private String name;
		private String characterName;
		
		/**
		 * Creates a new CastMember, with name and characterName set to null.
		 */
		public CastMember()
		{
			name = null;
			characterName = null;
		}
		
		/**
		 * Creates a new CastMember.
		 * @param realName The name of the CastMember.
		 * @param character The name of the character the CastMember portrays.
		 */
		public CastMember(String realName, String character)
		{
			name = realName;
			characterName = character;
		}
		
		/**
		 * Returns the name of the CastMember.
		 * @return The name of the CastMember.
		 */
		public String getName()
		{
			return name;
		}
		
		/**
		 * Returns the name of the character the CastMember portrays.
		 * @return The name of the character the CastMember portrays.
		 */
		public String getCharacter()
		{
			return characterName;
		}
		
		/**
		 * Sets the name of the CastMember.
		 * @param newName The new name of the CastMember.
		 */
		public void setName(String newName)
		{
			name = newName;
		}
		
		/**
		 * Sets the name of the character that the CastMember portrays.
		 * @param newCharacter The new name of the character that the CastMember portrays.
		 */
		public void setCharacter(String newCharacter)
		{
			characterName = newCharacter;
		}
		/**
		 * Returns the name of the CastMember, a tab, then the character the CastMember played 
		 */
		public String toString()
		{
			return name + "\t" + characterName;
		}
	}
	/**
	 * Adds a new actor to the array of Actors.
	 * @param actor The actor added to the array.
	 */
	//Fix
	public void addActor(CastMember actor)
	{
		peopleAct.add(actor);
	}
	/**
	 * Removes an actor from the array of Actors.
	 * @param actor The actor to be removed.
	 * @return The removed actor if the removal was successful, null if the removal failed. 
	 */
	public CastMember removeActor(CastMember actor)
	{
		peopleAct.remove(actor);
		return actor;
	}
	/**
	 * Finds a character in the array of actors.
	 * @param actorName The name of the actor.
	 * @return Returns the name of the character played by the specific actor, or gives an error message if that character was not found. 
	 */
	public String findCharacter(String actorName)
	{
		for (int index = 0; index < peopleAct.size(); index++)
		{
			if (peopleAct.get(index) == null)
			{
				break;
			}
			else if (peopleAct.get(index).getName() == actorName)
			{
				return "The actor/actress " + actorName + " played " + peopleAct.get(index).getCharacter() + " in the movie " + name + ".";
			} 
		}
		return "Sorry, the following actor " + actorName + " was not found in the movie.";
	}
	/**
	 * Finds an actor in the array of actors.
	 * @param characterName The name of the character played by the actor. 
	 * @return Returns the name of the actor playing the specific character, or gives an error message if that actor was not found. 
	 */
	public String findActor(String characterName)
	{
		for (int index = 0; index < peopleAct.size(); index++)
		{
			if (peopleAct.get(index) == null)
			{
				break;
			}
			else if (peopleAct.get(index).getCharacter() == characterName)
			{
				return "The character " + characterName + " was played by " + peopleAct.get(index).getName() + " in the movie " + name + ".";
			} 
		}
		return "Sorry, the following character " + characterName + " was not found in the movie.";
	}
	
	/**
	 * Returns some basic information about the movie. 
	 * @return The name of the movie, its release year, and its actors.
	 */
	public String toString()
	{
		String temp = "";
		for (int index = 0; index < peopleAct.size(); index++)
		{
			if (index == peopleAct.size() - 1 || peopleAct.get(index + 1) == null)
			{
				temp += "and " + peopleAct.get(index).toString() + ".";
				break;
			}
			else
			{
				temp += peopleAct.get(index).toString() + ", ";
			}
		}
		String output = "Movie Name: " + name + "\nMovie Release Year: " + year + "\nMovie Actors/Actressess: " + temp;
		return output;
	}
	
}
