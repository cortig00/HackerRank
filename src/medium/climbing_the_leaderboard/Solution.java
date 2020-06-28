package medium.climbing_the_leaderboard;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

	// Complete the climbingLeaderboard function below.
	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		ArrayList<Integer> leaderboard = getLeaderBoard(scores);

		int[] results = new int[alice.length];
		for (int i = 0; i < alice.length; i++) {
			results[i] = getAlicePosition(leaderboard,alice[i]);
		}

		return results;
	}


	private static ArrayList<Integer> getLeaderBoard(int[] scores){
		ArrayList<Integer> leaderboard = new ArrayList<Integer>();

		int counter = 0;
		for (int i = 0; i < scores.length; i++) {
			if (i == 0) {
				leaderboard.add(scores[i]);				
			}else {		
				if (leaderboard.get(counter) != scores[i]) {
					leaderboard.add(scores[i]);
					counter++;
				}
			}
		}

		return leaderboard;	
	}

	public static int getAlicePosition(ArrayList<Integer> leaderboard, int x) { 
		int n = leaderboard.size(); 

		// Finding block size to be jumped 
		int step = (int)Math.floor(Math.sqrt(n)); 

		// Finding the block where element is present (if it is present) 
		int prev = 0; 
		boolean exit = false;
		while (!exit) {
			if (leaderboard.get(Math.min(step, n) - 1) <= x ) {
				exit = true;
			}else {
				prev = step; 
				step += (int)Math.floor(Math.sqrt(n)); 
				if (prev >= n) 
					return n + 1; 	//The element is not present -> Last position of the leaderboard
			}
		} 
		
		for (int i = prev; i < step; i++) {
			if(x >= leaderboard.get(i)) {
				return i+1;
			}
		}

		return step + 1; //The element is not present -> Last element of the sub division of the leaderboard
	} 

    private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		int scoresCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] scores = new int[scoresCount];

		String[] scoresItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < scoresCount; i++) {
			int scoresItem = Integer.parseInt(scoresItems[i]);
			scores[i] = scoresItem;
		}

		int aliceCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] alice = new int[aliceCount];

		String[] aliceItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < aliceCount; i++) {
			int aliceItem = Integer.parseInt(aliceItems[i]);
			alice[i] = aliceItem;
		}

		int[] result = climbingLeaderboard(scores, alice);

		for (int i = 0; i < result.length; i++) {
			bufferedWriter.write(String.valueOf(result[i]));

			if (i != result.length - 1) {
				bufferedWriter.write("\n");
			}
		}

		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
