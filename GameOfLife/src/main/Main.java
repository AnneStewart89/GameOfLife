package main;

public class Main {
	 //Update if gameMatrix dimensions change
	final static int rowCount = 9;
	final static int colCount = 9;
		
	public static void main(String[] args) {
		//Initial Matrix (0 = dead, 1 = alive)
		int[][] gameMatrix =  {
				{0, 0, 1, 0, 0, 0, 1, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 1, 0},
				{1, 0, 0, 0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 1, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0, 1},
				{0, 1, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 1, 0, 0, 0, 1, 0, 0}
		};
		
		int[][] tempMatrix = new int[rowCount][colCount];
		
		int livingNeighbours = 0;
		
		int currentGeneration = 0;
		int numberOfGenerations = 12;
		
		while (currentGeneration < numberOfGenerations) {
			System.out.println("Current generation: " + (currentGeneration+1));
			System.out.println("===");
			for (int[] row : gameMatrix) {
				for (int element : row) {
					System.out.print(element);
				}
				System.out.println("");
			}
			System.out.println("===");
			
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					
					/** Get the value of all of the elements surrounding the target element at gameMatrix[i][j] */
					//Get sum of 3 neighbours above the target element
					for (int colIndex = j - 1; colIndex <= j + 1; colIndex++) {
						int rowIndex = i - 1;
						if (isValidRow(rowIndex) && isValidCol(colIndex))
							livingNeighbours += gameMatrix[rowIndex][colIndex];
					}
					
					//Get sum of 2 neighbours adjacent to the target element
					if (isValidCol(j - 1))
						livingNeighbours += gameMatrix[i][j - 1];
					if (isValidCol(j + 1))
						livingNeighbours += gameMatrix[i][j + 1];
					
					//Get sum of 3 neighbours below the target element
					for (int colIndex = j - 1; colIndex <= j + 1; colIndex++) {
						int rowIndex = i + 1;
						if (isValidRow(rowIndex) && isValidCol(colIndex))
							livingNeighbours += gameMatrix[rowIndex][colIndex];
					}
					
					/** Determine the new value of the target element according to the rules of life */
					if (gameMatrix[i][j] == 1 && (livingNeighbours == 2 || livingNeighbours == 3))
						tempMatrix[i][j] = 1;
					else if (gameMatrix[i][j] == 0 && livingNeighbours == 3)
						tempMatrix[i][j] = 1;
					else
						tempMatrix[i][j] = 0;
					
					livingNeighbours = 0;
				}
			}
			
			/** Displays new matrix and updates global + class variables */
			gameMatrix = tempMatrix;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			currentGeneration++;
		}
	}
	
	public static boolean isValidRow(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= rowCount)
			return false;
		else
			return true;
	}
	
	public static boolean isValidCol(int colIndex) {
		if (colIndex < 0 || colIndex >= colCount)
			return false;
		else
			return true;
	}
}
