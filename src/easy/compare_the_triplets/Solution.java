package easy.compare_the_triplets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
    	int resultA = 0;
    	int resultB = 0;
    	int currentA = 0;
    	int currentB = 0;
    	
    	Iterator<Integer> iterListA = a.iterator();
    	Iterator<Integer> iterListB = b.iterator();
    	
    	int comparation = 0;
    	
    	while (iterListA.hasNext()) {
    		currentA = (Integer) iterListA.next();
    		currentB = (Integer) iterListB.next();
    		
    		comparation = Integer.compare(currentA, currentB);
    		if (comparation > 0) {
    			resultA += 1; 
			}else if (comparation < 0) {
				resultB += 1;
			}
		}
    		
    	return Arrays.asList(resultA, resultB);
    }

    public static void main(String[] args) throws IOException {

        List<Integer> a = new ArrayList<Integer>();
        a.add(5);
        a.add(6);
        a.add(7);

        List<Integer> b = new ArrayList<Integer>();
        b.add(3);
        b.add(6);
        b.add(10);
    	
        List<Integer> result = compareTriplets(a, b);

    }
}
