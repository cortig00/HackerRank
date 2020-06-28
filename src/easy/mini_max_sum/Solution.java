package easy.mini_max_sum;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

	// Complete the miniMaxSum function below.
	static void miniMaxSum(int[] arr) {
		int max = Integer.MIN_VALUE;
		int minus = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (max <= arr[i]) {
				max = arr[i];
			}
			if (minus >= arr[i]) {
				minus = arr[i];
			}
		}

		BigInteger maxAdd = getAdd(arr, minus);
		BigInteger minAdd = getAdd(arr, max);

		System.out.println(minAdd+" "+maxAdd);
	}

	private static BigInteger getAdd(int[] arr, int skip) {
		BigInteger total = new BigInteger("0");
		
		Integer n = 0;
		boolean frequent = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != skip || frequent ) {
				n = Integer.valueOf(arr[i]);
				total = total.add(BigInteger.valueOf(n));
			}else {
				frequent = true;
			}
		}

		return total;
	}


	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int[] arr = new int[5];

		String[] arrItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < 5; i++) {
			int arrItem = Integer.parseInt(arrItems[i]);
			arr[i] = arrItem;
		}

		miniMaxSum(arr);

		scanner.close();
	}
}
