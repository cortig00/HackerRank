package easy.staircase;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the staircase function below.
    static void staircase(int n) {
    	String row = "";
    	for (int i = n - 1; i >= 0; i--) {
    		row = "";
			for (int j = 0; j < n; j++) {
				if (i <= j ) {
					row += "#";
				}else {
					row += " ";
				}
			}
			System.out.println(row);
		}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        staircase(5);
    }
}
