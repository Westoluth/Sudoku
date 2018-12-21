package sudokutest;

import sudokutest.tests.*;

import org.testng.TestListenerAdapter;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.*;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/*
A test client to manage running tests
*/
@SuppressWarnings("rawtypes")
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

    /*
    Initializes SudokuTestClient
    */
    public SudokuTestClient() {
        //Initializes Test Client
        System.out.println("Starting Sudoku Test Client...");
        scanner = new Scanner(System.in);

        //Starts Test Client
        runClient();
    }

    /*
    Begins main loop for running test client
    */
    private void runClient() {
        //Begins test client main loop
        System.out.println("\nSudoku Test Client:\n");
        while(true) {           
            //Intializes variables for this loop cycle
            testng = new TestNG();
            tla = new TestListenerAdapter();
            testng.addListener((ITestNGListener) tla);

            //Gets input and converts to upper case
            String input = scanner.nextLine().toUpperCase();
            System.out.println();

            //Input parsing program
            if(input.equals("-F") || input.equals("-FULL")) {
                System.out.println("Beginning Full Test...\n");
                
                //Passes fullTest.xml to testng
                List<String> testSuites = new ArrayList<String>();
                testSuites.add("test/data/fullTest.xml");
                testng.setTestSuites(testSuites);

                //Begins tests
                testng.run(); 
            } else if(input.equals("-C") || input.equals("-CHECK")) {
                System.out.println("Beginning Check-in Test...\n");

                //Passes checkkTest.xml to testng
                List<String> testSuites = new ArrayList<String>();
                testSuites.add("test/data/checkinTest.xml");
                testng.setTestSuites(testSuites);

                //Begins tests
                testng.run(); 
            } else if(input.equals("-T") || input.equals("-TEST")) {
                System.out.println("Specific Test not available at this time\n");
            } else if(input.equals("-H") || input.equals("-HELP")) {
                System.out.println("Commands:\n" +
                    "\n    -f or -full:   Runs every test available" +
                    "\n    -c or -check:  Runs all checkin tests(Faster than full test)" +
                    "\n    -t or -test:   Runs a specific test in the sudokutest.tests package (E.g. -test BoardTest)" +
                    "\n    -h or -help:   Lists commands and paramaters" +
                    "\n    -q or -quit:   Exits the test client\n");
            } else if(input.equals("-Q") || input.equals("-QUIT")) {
                System.exit(0);
            } else {
                System.out.println("Incorrect input, type '-h' or '-help' for commands\n");
            }
        }
    }
}