import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//TODO: FIX Q
//DONE: LESS POP BEST POP
//DONE: CHECK STACK SIZE BEFORE OPERAND
//DONE: negative numbers pls

public class ReversePolishNotationCalculator 
{
	public static void main(String[] args)
	{
		try (InputStreamReader console = new InputStreamReader(System.in);
			 BufferedReader input = new BufferedReader(console))
		{
			String line = "";
			while (!line.toLowerCase().equals("q")) 
			{
				int secondNum;
				Stack<Integer> numStack = new Stack<>();
				System.out.print(">> ");
				line = input.readLine();
				if (line.matches("-?[0-9]+"))
				{
					numStack.push(Integer.parseInt(line));
				} else if (line.toLowerCase().equals("c")) 
				{
					numStack.clear();
				} else
				{
					if (numStack.size() > 1)
					{
						if (line.equals("+"))
						{
							numStack.push(numStack.pop() + numStack.pop());
						} else if (line.equals("-"))
						{
							secondNum = numStack.pop();
							numStack.push(numStack.pop() - secondNum);
							//numStack.push(- numStack.pop() + numStack.pop());
						} else if (line.equals("*"))
						{
							numStack.push(numStack.pop() * numStack.pop());
						} else if (line.equals("/"))
						{
							secondNum = numStack.pop();
							numStack.push(numStack.pop() / secondNum);
							//numStack.push(1 / (numStack.pop() / numStack.pop()));
						} else if (line.equals("%"))
						{
							secondNum = numStack.pop();
							// 5 7 % 
							// 5 mod 7 = 5, but 7 mod 5 = 2 (we get items w/ 7 first, then 5 following)
							// 7 - (7 mod 5) = 7 - 2 = 5
							numStack.push(numStack.pop() % secondNum);
						} else
						{
							System.out.println("\"" + line + "\"" + " is not a valid. Please enter a valid number or operand.");
						}
					}
				}
				
			} 
			
		} catch (IOException exception)
		{
			System.err.println("Error -- " + exception);
		}
				
	}
}
