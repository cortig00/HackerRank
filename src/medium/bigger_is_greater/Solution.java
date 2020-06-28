package medium.bigger_is_greater;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

	private static char[] asciiValues;
	
    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
    	int length = w.length();
    	//int[] asciiValues = new int[length];
    	asciiValues = new char[length];
    	
    	for (int i = 0; i < length; i++) {
    		asciiValues[i] = w.charAt(i); 
		}
    	
    	int contador = length - 1;
    	boolean stop = false;
    	int firstChange = 0;
    	
    	while(contador < length && !stop && contador != 0) {
    		if (contador == firstChange) {
    			stop = true;
			}
    		if (isGreater(asciiValues[contador], asciiValues[contador-1])) {
				//Permutamos esos valores, reseteamos el valor de los anteriores 
    			permuteVars(contador);
    			//reStartSearch(w,contador);
    			firstChange = contador;
    			contador = length - 1;
			}else {
				contador--;
			}
    	}
    	
    	if (String.valueOf(asciiValues).contentEquals(w)) {
	    	return "no answer";
		}
    	
		return String.valueOf(asciiValues);
    }
    
    private static boolean isGreater(char bigger, char lower) {
    	if (bigger > lower) {
			return true;
		}
    	return false;
    }

    private static void permuteVars(int contador) {
    	char aux = asciiValues[contador];
		asciiValues[contador] = asciiValues[contador-1];
		asciiValues[contador-1] = aux;
    }
    
    //Hacemos que, una vez permutado, del valor de contador a la derecha el array vuelva a la normalidad
    private static void reStartSearch(String word, int contador) {
    	System.out.println(contador);
    	for (int i = asciiValues.length - 1 ; i > contador; i--) {
    		asciiValues[i] = word.charAt(i);
		}
    }

    public static void main(String[] args) throws IOException {
            /*System.out.println(biggerIsGreater("ab"));
            System.out.println(biggerIsGreater("bb"));
            System.out.println(biggerIsGreater("hefg"));
            System.out.println(biggerIsGreater("fedcbabcd"));*/
           // System.out.println("dkhc - My Output:"+biggerIsGreater("dkhc")+ " - Expected: hcdk");/**/
            System.out.println("abdc - My Output:"+biggerIsGreater("abdc")+ " - Expected: acbd");/**/
    }
}
