package medium.encryption;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the encryption function below.
    static String encryption(String s) {
    	s = s.replaceAll("\\s+","");
    	int length = s.length();
    	double squareRoot = Math.sqrt(length);
    	
    	int row = (int) Math.floor(squareRoot);
    	int column = (int) Math.ceil(squareRoot);
    	
    	if (row * column < length) {
    		row++;
		}
    	
    	char[][] grid = new char[row][column];
    	int current = 0;
    	
    	for (int i = 0; i < row ; i++) {
    		for (int j = 0; j < column && current < length; j++) {
        		grid[i][j] = s.charAt(current);
    			current++;
			}
		}
    	
    	String output = "";
    	String aux = "";
    	for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				if (grid[j][i] == '\u0000' ) { // Valor por defecto de un char
					break;
				}
				aux += grid[j][i];
			}
			output += aux+" ";
			aux="";
		}
    	
    	return output;
    }

    public static void main(String[] args) throws IOException {
       System.out.println(encryption("have a nice day"));
       System.out.println(encryption("chillout"));
       System.out.println(encryption("feed the dog")); 
       System.out.println(encryption("bgwdyygtmwhtwhusfedckrgybozfjaukgsngqvzftiypqukxypbkghjiwkphkjtsdizueaz")); 
    }
}
