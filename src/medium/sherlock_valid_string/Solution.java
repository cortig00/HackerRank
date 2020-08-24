package medium.sherlock_valid_string;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

	// Complete the isValid function below.
	static String isValid(String s) {
		HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();

		StringBuilder word = new StringBuilder(s);

		for (int i = 0; i < word.length(); i++) {
			if (frequencies.get(word.charAt(i)) == null) {
				frequencies.put(word.charAt(i), 1);
			}else {
				frequencies.put(word.charAt(i), frequencies.remove(word.charAt(i)) + 1 );
			}
		}


		// Print values
		ArrayList<Integer> ocurrences = new ArrayList<>(frequencies.values());
		Collections.sort(ocurrences);
		
		int min = ocurrences.get(0);
		int max = ocurrences.get( ocurrences.size() - 1 );
		int numberMin = 0;
		int numberMax = 0;
		
		System.out.println(ocurrences.toString());
		
		for (int i = 0; i < ocurrences.size(); i++) {
			if (ocurrences.get(i) == min) {
				numberMin++;
			}
			else if (ocurrences.get(i) == max) {
				numberMax++;
			}else {

				return "NO";
			}					
		}

        if (numberMin >= 2 && numberMax >= 2) {
			return "NO";
		}      

		return "YES";
	}

	//ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = scanner.nextLine();

		String result = isValid(s);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
