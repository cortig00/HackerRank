package easy.two_strings;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

	// Complete the twoStrings function below.
	private static String twoStrings(String s1, String s2) {

		StringBuilder first = new StringBuilder(s1);
		StringBuilder second = new StringBuilder(s2);

		HashMap<Character, Boolean> frequency = new HashMap<Character, Boolean>();
		
		char current = ' ';
		for (int i = 0; i < first.length(); i++) {
			current = first.charAt(i);
			if (frequency.get(current) == null) {
				frequency.put(current, true);
			}
		}
		
		for (int i = 0; i < second.length(); i++) {
			current = second.charAt(i);
			if (frequency.get(current) != null) {
				return "YES";
			}
		}
		
		/*
		for(char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
			//Comparación. Es otra solución pero conlleva peor complejidad. 
			 //habría que usar indexOf() o contains de String y es menos eficiente
		}/**/
		
		return "NO";
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int qItr = 0; qItr < q; qItr++) {
			String s1 = scanner.nextLine();

			String s2 = scanner.nextLine();

			String result = twoStrings(s1, s2);

			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
