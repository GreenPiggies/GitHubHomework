import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		Scanner br = new Scanner(new InputStreamReader(System.in));
		String line = null;
		int count = 1;
		while ((line = br.nextLine()) != null && !line.equals("0"))
		{
			int balance = 0;
			int numEvents = Integer.parseInt(line);
			StringTokenizer str = new StringTokenizer(br.nextLine());
			for (int event = 0; event < numEvents; event++)
			{
				int value = Integer.parseInt(str.nextToken());				
				balance = (value > 0) ? (balance + 1) : (balance - 1);
			}
			System.out.println("Case " + count + ": " + balance);
			count++;
		}
		br.close();

	}

}
