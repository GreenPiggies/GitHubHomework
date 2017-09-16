
public class Actor 
{
	private String name;
	private String characterName;
	/**
	 * Creates a new Actor
	 * @param realName The name of the actor.
	 * @param character The name of the character the actor portrays.
	 */
	public Actor(String realName, String character)
	{
		name = realName;
		characterName = character;
	}
	/**
	 * Returns the name of the actor.
	 * @return The name of the actor.
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Returns the name of the character the actor portrays.
	 * @return The name of the character the actor portrays.
	 */
	public String getCharacter()
	{
		return characterName;
	}
	/**
	 * Sets the name of the actor.
	 * @param newName The new name of the actor.
	 */
	public void setName(String newName)
	{
		name = newName;
	}
	/**
	 * Sets the name of the character that the actor portrays.
	 * @param newCharacter The new name of the character that the actor portrays.
	 */
	public void setCharacter(String newCharacter)
	{
		characterName = newCharacter;
	}
}
