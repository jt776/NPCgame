import java.util.Random;
import java.util.Scanner;

public class Combat {
	
	private static Random rand = new Random();
	
	//return true if party wins, return false if party wipes
	//TODO: initiative based on dex
	//TODO: calculate party and enemy health at start of combat based on con
	public static boolean beginCombat(int[] partyStats, int[] enemyStats, String[] partyNames, String[]enemyNames) {
		
		Scanner s = new Scanner(System.in);
		boolean looper = true;
		boolean winner = false;
		int n = 0;
		
		System.out.println("COMBAT BEGIN!");
		
		while(looper) {
			System.out.println("Slash (1), Spell (2), Block (3) or Run (4)?");
			n = s.nextInt();
			if(n == 1) {
				physicalHit();
			} else if(n == 2) {
				
			} else if(n == 3) {
				
			} else if(n == 4) {
				
			} else System.out.println("USER INPUT ERROR. Try again...");
		}
		
		return winner;
		}

	//chance to hit (DEX) must beat enemy Armor (CON), damage (STR) is returned if hit is higher 
	private static int physicalHit(int[] playerStats, int[]enemyStats) {
		
		if((rand.nextInt(19) + 1 + playerStats[1]) > (enemyStats[2] + 10))
			return rand.nextInt(5)+playerStats[0];
		else return 0;

	}
	
	//chance to hit (WIS) must beat enemy Armor (CON), damage (INT) is returned if hit is higher 
	private static int magicalHit(int[] playerStats, int[]enemyStats) {
		if((rand.nextInt(19) + 1 + playerStats[4]) > (enemyStats[2] + 10))
			return rand.nextInt(5)+playerStats[3];
		else return 0;
	}
}
