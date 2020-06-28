package easy.diagonal_difference;

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

class Solution {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     *
     *  <<<<<<<<<<<        >>>>>>>>>
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
    // Write your code here    	
    	int firstDiagonal = 0;
    	int secondDiagonal = 0;
    	
    	int counter = arr.size() - 1;
    	for (int i = 0; i < arr.size(); i++) {
    		firstDiagonal += arr.get(i).get(i);
    		secondDiagonal += arr.get(i).get(counter);
    		counter--;
		}
    	
    	System.out.println(firstDiagonal);
    	System.out.println(secondDiagonal);

    	return Math.abs(firstDiagonal+secondDiagonal);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Solution.diagonalDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}