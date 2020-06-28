package hard.matrix_layer_rotation;

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

/**
 * NOTA FINAL -> 60/80 - Falla el algoritmo por time out. Buscar una manera más eficiente para obtener el 100%
 * @author Cesar Ortigueira
 *
 */
public class SolutionQueue {

	private static int rows;
	private static int columns;
	private static List<List<Integer>> matrix;
	private static List<Queue<Integer>> queueData;

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {
    	rows = matrix.size();
    	columns = matrix.get(0).size();
    	setMatrix(matrix);
    	
    	int numberLevels = getNumberLevels(rows,columns);
    	    	    	
    	queueData = new ArrayList<Queue<Integer>>();
    	
    	boolean finish = false;
    	int currentLevel = 0;
    	while (!finish) {
    		Queue<Integer> cola = getNewQueue(currentLevel); 	//Tenemos la cola guardada
    		//
    		if (r < cola.size()) {
    			r = r % (cola.size() + 1);
			}
    		
    		permuteQueueNTimes(cola, r);						//Permutamos r veces la cola
    		currentLevel++;
    		queueData.add(cola);
    		if (currentLevel == numberLevels) {
    			finish = true;
			}
		}

    	//Convert the list of queues in a Matrix
    	int[][] finalMatrix = createMatrixPermuted();
	
    	//Print Matrix
    	System.out.println(printMatrix(finalMatrix));
    }
   
	private static String printMatrix(int[][] finalMatrix) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				output.append(finalMatrix[i][j]+" ");
			}
			output.append("\n");

		}
		return output.toString();
	}
	
    private static int[][] createMatrixPermuted() {

    	int[][] finalMatrix = new int [rows][columns];
    	
    	int size = queueData.size();
    	int size_queue = 0;
    	int currentLevel = 0;
    	
    	int firstRow = 0;
    	int lastRow = 0;
    	int firstColumn = 0;
    	int lastColumn = 0;
    	int[] currentElement = {0,0}; 
    	
    	int value = 0;
    	
    	for (int i = 0; i < size; i++) {
        	size_queue = queueData.get(i).size();

        	firstRow = currentLevel;
        	lastRow = rows - currentLevel - 1;
        	firstColumn = currentLevel;
        	lastColumn = columns - currentLevel - 1;
    		currentElement[0] = firstRow;
    		currentElement[1] = firstColumn;
    				
			for (int j = 0; j < size_queue; j++) {
				value = queueData.get(i).poll();
	    		finalMatrix[currentElement[0]][currentElement[1]] = value;

	    		if (currentElement[0] == firstRow && currentElement[1] < lastColumn) { //Estamos en la primera fila
					currentElement[1]++;		
				}else if (currentElement[0] == lastRow && currentElement[1] > firstColumn) { // Estamos en la ultima fila
					currentElement[1]--;
				}else if (currentElement[1] == lastColumn &&  currentElement[0] < lastRow ) {// Estamos en la primera columna que itera (la de la derecha) 
					currentElement[0]++;
				}else if (currentElement[1] == firstColumn &&  currentElement[0] > firstRow ) {		
					currentElement[0]--;
				}	
			}
			currentLevel++;
		}
    	
    	return finalMatrix;
	}

	private static void permuteQueueNTimes(Queue<Integer> cola, int n) {
    	int pivotElement = 0;
    	for (int i = 0; i < n; i++) {
    		pivotElement = cola.poll();
    		cola.add(pivotElement);
		}
    }
   
    
	private static void setMatrix(List<List<Integer>> matriz) {
		matrix = matriz;
	}

	private static Queue<Integer> getNewQueue(int currentLevel) {
		System.out.println(currentLevel);
    	int totalSwaps = getNumberOfSwaps(currentLevel);

    	int firstRow = currentLevel;
    	int lastRow = rows - currentLevel - 1;
    	int firstColumn = currentLevel;
    	int lastColumn = columns - currentLevel - 1;
   			
    	int firstElement = matrix.get(firstColumn).get(firstRow);
    	//int counterSwaps = 1;
    	
    	int[] currentElement = {firstRow,firstColumn}; //Saves X & Y
    	
    	Queue<Integer> queue = new LinkedList<Integer>();

    	int exit = 0;
    	queue.add(firstElement);
    	while (exit <= 1) {

    		if (currentElement[0] == firstRow && currentElement[1] == firstColumn && (exit == 0 ||exit == 1)) {
				exit++;
			}
    		if (currentElement[0] == firstRow && currentElement[1] != lastColumn ) {
				for (int i = 0; i < lastRow  - firstRow ; i++) {
					currentElement[1]++;
		    		queue.add(matrix.get(currentElement[0]).get(currentElement[1]));
				}
			}
    		
    		else if (currentElement[0] == lastRow && currentElement[1] != firstColumn) {
    			for (int i = 0; i < lastColumn  - firstColumn ; i++) {
    				currentElement[1]--;
		    		queue.add(matrix.get(currentElement[0]).get(currentElement[1]));
				}
			}
    		
    		else if (currentElement[1] == lastColumn && currentElement[0] != lastRow) { // Estamos en la ultima fila
    			for (int i = 0; i < lastRow  - firstRow  ; i++) {
        			currentElement[0]++;
		    		queue.add(matrix.get(currentElement[0]).get(currentElement[1]));
				}
			}
    		
    		else if (currentElement[1] == firstColumn &&  currentElement[0] != firstRow ) {
				currentElement[0]--;
    			if (currentElement[0] == firstRow && currentElement[1] == firstColumn)
    				exit = 2;
    			else
    				queue.add(matrix.get(currentElement[0]).get(currentElement[1]));
				
			}else {
				System.out.println("ERROR");
				break;
			}
		}
    	
    	return queue;
    }

	private static int getNumberLevels(int rows, int columns) {
    	int numberSquares = 0;

    	while (true) {
			numberSquares++;
    		if (rows >= 3 && columns >= 3) {
				rows -= 2;
				columns -= 2;
			}else {
				break;
			}
		}
    	return numberSquares;
    }

	
    private static int getNumberOfSwaps(int currentLevel) {
    	int currentRow = rows - currentLevel * 2;
    	int currentColumn = columns - currentLevel * 2;
    	
    	return currentRow * 2 + (currentColumn - 2 )* 2 ;
    }
    
      
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}


/*
4 (row) - 4 (columns) - 2 (rotaciones a la izquierda/abajo)
4 4 2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16




5 4 7
1 2 3 4
7 8 9 10
13 14 15 16
19 20 21 22
25 26 27 28


    5 4 7
    1 2 3 4
    7 8 9 10
    13 14 15 16

    19 20 21 22

    25 26 27 28

*/