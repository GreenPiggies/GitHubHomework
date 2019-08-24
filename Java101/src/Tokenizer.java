/**
 * This class breaks up a string describing an expression into tokens: numbers, parentheses, and operators.
 * @author Wesley
 *
 */
public class Tokenizer 
{
	String input;
	/**
	 * Constructs a tokenizer object.
	 * @param input The string to tokenize.
	 */
	public Tokenizer(String input)
	{
		if (input != null)
		{
			this.input = input;
		}
	}
	
	/**
	 * Peeks at the next token without consuming it.
	 * @return The next token or null if there are no more tokens.
	 */
	public String peekToken()
	{
		String token = null;
		boolean tokenFound = false;
		int tokenIndex = 0;
		while (!tokenFound && tokenIndex < input.length())
		{
			char character = input.charAt(tokenIndex);
			if (Character.isDigit(character))
			{
				if (token == null)
				{
					token = "";
				}
				token += Character.toString(character);
			} else
			{
				if (character == '+' ||
					character == '-' || 
					character == '*' ||
					character == '/' ||
					character == '(' ||
					character == ')')
				{
					token = Character.toString(character);
				}
				tokenFound = true;
			}
			tokenIndex++;
		}
		return token;
	}
	
	/**
	 * Gets the next token and moves the tokenizer to the following token.
	 * @return The next token or null if there are no more tokens.
	 */
	public String nextToken()
	{
		String next = peekToken();			
		//System.out.println("Character: " + next);
		input = input.substring(next.length()).trim();
		//System.out.println(input);
		return next;
	}
	
	public static void main(String[] args)
	{
		Tokenizer tokenBoi = new Tokenizer("3 + 2 + 5 * 4");
		while (tokenBoi.peekToken() != null)
		{
			System.out.println(tokenBoi.nextToken());
		}
	}
}
