
import java.util.Scanner;

/**
 * A class that can compute the value of an arithmetic expression.
 * @author greenpig
 *
 */
public class Evaluator 
{
	private Tokenizer tokenizer;
	/**
	 * Constructs an evaluator object.
	 * @param expression A string containing the expression.
	 */
	public Evaluator(String expression)
	{
		tokenizer = new Tokenizer(expression);
	}
	
	/**
	 * Evaluates the expression.
	 * @return The value of the expression.
	 */
	public int getExpressionValue()
	{
		int value = getTermValue();
		String token = tokenizer.peekToken();
		boolean shouldLoop = true;
		while (shouldLoop && token != null)
		{
			if (token.equals("+"))
			{	
				tokenizer.nextToken();
				value += getTermValue();
			} else if (token.equals("-"))
			{
				tokenizer.nextToken();
				value -= getTermValue();
			} else
			{
				shouldLoop = false;
			}
			token = tokenizer.peekToken();
		}
		return value;
	}
	
	/**
	 * Evaluates the next term found in the expression. 
	 * @return The value of the term. 
	 */
	public int getTermValue()
	{
		int value = getFactorValue();
		String token = tokenizer.peekToken();
		boolean shouldLoop = true;
		while (shouldLoop && token != null)
		{
			if (token.equals("*"))
			{
				tokenizer.nextToken();
				value *= getFactorValue();
			} else if (token.equals("/"))
			{
				tokenizer.nextToken();
				value /= getFactorValue();
			} else
			{
				shouldLoop = false;
			}
			token = tokenizer.peekToken();
		}
		return value;
	}
	
	/**
	 * Evaluates the next factor found in the term.
	 * @return The value of the factor.
	 */
	public int getFactorValue()
	{
		int value = 0;
		String token = tokenizer.peekToken();
		if (token != null)
		{
			if (token.equals("("))
			{
				System.out.println("(");
				tokenizer.nextToken();
				value = getExpressionValue();
				token = tokenizer.peekToken();
				if (token.equals(")"))
				{
					System.out.println(")");
					tokenizer.nextToken();
				}
			} else if (token.matches("[0-9]+"))
			{
				value = Integer.parseInt(tokenizer.nextToken());
			}
		}
		return value;
	}
	
	/**
	 * Calculates the value of an inputed expression.
	 * @param args 
	 */
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the expression:");
		
		Evaluator eval = new Evaluator(in.nextLine());
		System.out.println(eval.getExpressionValue());
	}
}
