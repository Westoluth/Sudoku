package sudokutest;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import java.util.Scanner;

public class SudokuTestClient {
	/*
	SudokuTestClient variables:
		-testng: TestNG client to run tests programatically
		-tla: TestListenerAdapter to get results from testng and manage tests
		-scanner: Scanner to manage terminal input
	*/
	TestNG testng;
	TestListenerAdapter tla;
	Scanner scanner;

	public static void main(String[] args) {
		new SudokuTestClient();
	}

	//Initializes SudokuTestClient
	public SudokuTestClient() {
		//Initializes Test Client
		System.out.println("Starting Sudoku Test Client...");
		testng = new TestNG();
		tla = new TestListenerAdapter();
		scanner = new Scanner(System.in);

		//Starts Test Client
		runClient();
	}

	//Begins main loop for running test client
	private void runClient() {
		//Begins test client main loop
		System.out.println("\nSudoku Test Client:\n");
		while(true) {			
			String input = scanner.nextLine().toUpperCase();

			System.out.println();

			if(input.equals("H") || input.equals("HELP")) {
				System.out.println("Commands:\n\n   -h or -help: Lists commands and paramaters\n   -q or -quit: Exits the test client\n");
			}
			else if(input.equals("Q") || input.equals("QUIT")) {
				System.exit(0);
			} else {
				System.out.println("Incorrect input, type 'h' or 'help' for commands\n");
			}
		}
	}
}