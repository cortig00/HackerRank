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

public class Solution {

	private static int rows;
	private static int columns;
	private static List<List<Integer>> matrix;
	
    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {
    	rows = matrix.size();
    	columns = matrix.get(0).size();
    	setMatrix(matrix);
    	
    	int numberLevels = getNumberLevels(rows,columns);
    	    	
    	int auxiliarElement = 0;
    	//Hacer r veces
	    	//Guardamos el primer elemento de la lista en el auxiliar.
	    	//Copiamos el de su derecha en el sitio que deja vacio el primer elemento
	    	//While true hasta llegar al principio donde se guardara el elemento auxiliar en su nuevo sitio
    	//Pasar al siguiente nivel y volver a empezar
    		//Comprobar que el siguiente nivel tanto de fila y de columna es al menos la mitad 
    	 	//if (currentLevel )
    	
    	boolean finish = false;
    	int currentLevel = 0;
    	while (!finish) {
    		for (int i = 0; i < r; i++) {
        		permuteOneLoop(currentLevel);
			}
        	System.out.println(currentLevel);   		
    		currentLevel++;
    		if (currentLevel == numberLevels) {
    			finish = true;
			}
		}
    	
    	printMatrix();

    }
   

	private static void setMatrix(List<List<Integer>> matriz) {
		matrix = matriz;
	}

	private static void permuteOneLoop(int currentLevel) {
    	int totalSwaps = getNumberOfSwaps(currentLevel);

    	int firstRow = currentLevel;
    	int lastRow = rows - currentLevel - 1;
    	int firstColumn = currentLevel;
    	int lastColumn = columns - currentLevel - 1;
   			
    	int firstElement = matrix.get(firstColumn).get(firstRow);
    	
    	int counterSwaps = 1;
    	
    	int[] currentElement = {firstRow,firstColumn}; //Saves X & Y
    	    	
    	while (counterSwaps < totalSwaps) {
    		
    		if (currentElement[0] == firstRow && currentElement[1] < lastColumn) { //Estamos en la primera fila
				//System.out.println("Primera iteracion");

				matrix.get(currentElement[0]).set(currentElement[1], matrix.get(currentElement[0]).get(currentElement[1]+1));
				
				currentElement[1]++;
				
			}else if (currentElement[0] == lastRow && currentElement[1] > firstColumn) { // Estamos en la ultima fila
				//System.out.println("Tercera iteracion");
				matrix.get(currentElement[0]).set(currentElement[1], matrix.get(currentElement[0]).get(currentElement[1]-1));
				currentElement[1]--;
			}else if (currentElement[1] == lastColumn &&  currentElement[0] < lastRow ) {// Estamos en la primera columna que itera (la de la derecha) 
				//System.out.println("Segunda iteracion");

				matrix.get(currentElement[0]).set(currentElement[1], matrix.get(currentElement[0]+1).get(currentElement[1]));

				currentElement[0]++;
			}else if (currentElement[1] == firstColumn &&  currentElement[0] > firstRow ) {
				//System.out.println("Cuarta iteracion");

				matrix.get(currentElement[0]).set(currentElement[1], matrix.get(currentElement[0]-1).get(currentElement[1]));
				
				currentElement[0]--;
			}else {
				//System.out.println("Error: x = "+currentElement[0]+" / y = "+currentElement[1]);
			}
    		
    		//System.out.println("x = "+currentElement[0]+"  y = "+currentElement[1]);
    		counterSwaps++;
		}
    	
    	matrix.get(currentElement[0]).set(currentElement[1], firstElement);


    	
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
    
    
	private static void printMatrix() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(matrix.get(i).get(j)+" ");
			}
			System.out.println();
		}
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




*/