public class NewTokenizer 
{
	private String input;
	private int start;
	private int end;
	
	public NewTokenizer(String input)
	{
		if (input != null)
		{
			this.input = input;
		} else
		{
			this.input = "";
		}
		start = 0;
		end = 0;
		nextToken();
	}
	
	public String peekToken()
	{
		if (start < input.length())
		{
			return input.substring(start, end);
		}
		return null;
	}
	
	public String nextToken()
	{
		String token = peekToken();
		while (end < input.length() && Character.isSpaceChar(input.charAt(end)))
		{
			end++;
		}
		start = end;
		if (start < input.length())
		{
			if (Character.isDigit(input.charAt(start)))
			{
				do
				{
					end++;
				} while (end < input.length() && Character.isDigit(input.charAt(end)));
			} else
			{
				end = start + 1;
			}
		}
		return token;
	}
	
	
	public static void main(String[] args) 
	{
		String expression;
		expression = "3+2*4";
		expression = "  310          +421   +    532";
		NewTokenizer tokenizer = new NewTokenizer(expression);
		while (tokenizer.peekToken() != null)
		{
			System.out.println("Peek: " + tokenizer.peekToken());
			System.out.println("Next: " + tokenizer.nextToken());
		}
	}

}
