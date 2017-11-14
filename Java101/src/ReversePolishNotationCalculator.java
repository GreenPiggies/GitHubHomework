import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

//TODO: FIX Q
//TODO: LESS POP BEST POP
//TODO: CHECK STACK SIZE BEFORE OPERAND
//TODO: negative numbers pls

public class ReversePolishNotationCalculator 
{
	public static void main(String[] args)
	{
		try (InputStreamReader console = new InputStreamReader(System.in);
			 BufferedReader input = new BufferedReader(console))
		{
			String line;
			do 
			{
				Stack<Integer> numStack = new Stack<>();
				System.out.print(">> ");
				line = input.readLine();
				if (line.matches("[0-9]+"))
				{
					numStack.push(Integer.parseInt(line));
				} else if (line.toLowerCase().equals("c")) 
				{
					numStack.clear();
				} else if (line.equals("+"))
				{
					numStack.push(numStack.pop() + numStack.pop());
				} else if (line.equals("-"))
				{
					// 5 7 - should be -2, but we get 7 first, then 5, so we can't just subtract. We need to multipy the result by -1 to get the correct answer.
					numStack.push(- numStack.pop() + numStack.pop());
				} else if (line.equals("*"))
				{
					numStack.push(numStack.pop() * numStack.pop());
				} else if (line.equals("/")) //Same thing here as for subtraction. Order matters, so we need to reverse the answer (in this case 1 / result). 
				{
					numStack.push(1 / (numStack.pop() / numStack.pop()));
				} else if (line.equals("%"))
				{
					// 5 7 % 
					// 5 mod 7 = 5, but 7 mod 5 = 2 (we get items w/ 7 first, then 5 following)
					// 7 - (7 mod 5) = 7 - 2 = 5
					numStack.push(numStack.peek() - (numStack.pop() % numStack.pop()));
				} else
				{
					System.out.println(line + " is not a valid. Please enter a valid number or operand.");
				}
				System.out.println(numStack);
			} while (!line.toLowerCase().equals("q"));
			
		} catch (IOException exception)
		{
			System.err.println("Error -- " + exception);
		}
				
	}
}
