package sudokutest;

import org.testng.TestNG;

public class SudokuTestClient {
	public static void main(String[] args) {
		new SudokuTestClient();
	}

	public SudokuTestClient() {
		System.out.println("Starting Sudoku Test Client...");
		TestNG testng = new TestNG();
	}
}