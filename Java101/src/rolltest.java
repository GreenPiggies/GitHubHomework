import java.math.BigInteger;
import java.math.BigDecimal;
public class rolltest {
	static int MAX_LEVEL = 20;
	static int MAX_HP = 800;
	
	static int BASE_HP = 200;
	static int MIN_GAIN = 20;
	static int MAX_GAIN = 30;
	
	static long[][] dp;
	public static void main(String[] args) {
		dp = new long[MAX_LEVEL][MAX_HP];
		dp[0][BASE_HP] = 1;
		for (int i = 1; i < 20; i++) {
			for (int j = 0; j < MAX_HP; j++) {
				for (int k = MIN_GAIN; k <= MAX_GAIN && j - k >= 0; k++) {
					/* The sum of all combinations of level/hp is calculated using the 
					 * combinations of level/hp of the previous level, adding all combos
					 * with an HP difference between */
					dp[i][j] += dp[i - 1][j - k];
				}
			}
		}
		for (int i = 770 - (10 * 19); i <= 770; i++) {
			System.out.println("# of ways to get a roll of " + i + " : " + dp[19][i]);
		}
	}

}
