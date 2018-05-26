import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;

//Suppresses serial warnings
@SuppressWarnings("serial")

public class SudokuGUI extends JFrame {
	/*
	Main elements:
		mainPanel: Main panel of JFrame. Contains all other panels/elements.
		boardPanel: Panel containing all board pieces.
		buttonPanel: Panel containing all buttons.

	Board elements:
		boardTextFields: Array containing every all input text fields. Placed into boardSubPanels.
		boardSubPanels: Sub panels containing board text fields. Placed into boardPanel.

	ButtonElements:
		solveButton: Button that triggers solving of current puzzle.
		clearButton: Button that clears the board

	Utilities:
		gameSolver: SudokuSolver instance that handles solving the game.
	*/

	//Main elements
	private JPanel mainPanel;
	private JPanel boardPanel;
	private JPanel buttonPanel;

	//Board elements
	private JTextField[] boardTextFields;
	private JPanel[] boardSubPanels;

	//Button elements
	private JButton solveButton;
	private JButton clearButton;

	//Utilities
	private SudokuSolver gameSolver;

	public static void main(String[] args) {
		new SudokuGUI();
	}

	public SudokuGUI() {
		//Sets up window
		setupWindow();

		//Sets up utilities
		setupUtilites();
	}

	/*
	Handles the creation and initialization of window and all sub panels/elements
	*/
	private void setupWindow() {
		//Sets basic window parameters
		setSize(450, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sudoku");

		//Sets up main panel and adds it to frame
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		//Creates and sets the main panel constraints
		GridBagConstraints mainConstraints = new GridBagConstraints();
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 0;
		mainConstraints.gridwidth = 1;
		mainConstraints.gridheight = 1;
		mainConstraints.weightx = 100;
		mainConstraints.weighty = 100;
		mainConstraints.insets = new Insets(10, 10, 10, 10);
		mainConstraints.anchor = GridBagConstraints.CENTER;
		mainConstraints.fill = GridBagConstraints.BOTH;

		//Sets up board panel and adds it to frame
		setupBoard();
		mainPanel.add(boardPanel, mainConstraints);

		//Sets up button panel and adds it to frame
		setupButtons();
		mainConstraints.gridy = 1;
		mainConstraints.weightx = 100;
		mainConstraints.weighty = 25;
		mainPanel.add(buttonPanel, mainConstraints);

		//Adds mainPanel to frame
		this.add(mainPanel);

		//Reveals window
		setVisible(true);
	}

	/*
	Handles the creation and initialization of all the board panels
	*/
	private void setupBoard() {
		//Initializes board panel, sub panels, grid layouts and text fields
		boardPanel = new JPanel();
		boardSubPanels = new JPanel[9];
		boardTextFields = new JTextField[81];

		//Sets up boardPanelLayout
		GridLayout boardPanelLayout = new GridLayout(3, 3);
		boardPanel.setLayout(boardPanelLayout);
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		//Fills boardSubPanel and boardSubPanelLayouts
		for(int boardSubPanelNum = 0; boardSubPanelNum < boardSubPanels.length; boardSubPanelNum++) {
			boardSubPanels[boardSubPanelNum] = new JPanel();
			GridLayout boardSubPanelLayout = new GridLayout(3, 3);
			boardSubPanels[boardSubPanelNum].setLayout(boardSubPanelLayout);
			boardSubPanels[boardSubPanelNum].setBorder(BorderFactory.createLineBorder(Color.black));
		}

		//Fills boardTextFields
		for(int boardTextFieldNum = 0; boardTextFieldNum < boardTextFields.length; boardTextFieldNum++) {
			boardTextFields[boardTextFieldNum] = new JTextField("", 1);
		}

		//Places all text fields into sub panels
		for(int boardTextFieldNum = 0; boardTextFieldNum < boardTextFields.length; boardTextFieldNum++) {
			boardSubPanels[boardTextFieldNum/9].add(boardTextFields[boardTextFieldNum]);
		}

		//Places all sub panels into panels
		for(int boardSubPanelNum = 0; boardSubPanelNum < boardSubPanels.length; boardSubPanelNum++) {
			boardPanel.add(boardSubPanels[boardSubPanelNum]);
		}
	}

	/*
	Handles the creation and initialization of the button panels and all contained buttons
	*/
	private void setupButtons() {
		//Initializes buttonPanel
		buttonPanel = new JPanel();

		//Sets layout for buttonPanel and creates constraints
		buttonPanel.setLayout(new GridBagLayout());

		GridBagConstraints buttonConstraints = new GridBagConstraints();

		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = 0;
		buttonConstraints.gridwidth = 1;
		buttonConstraints.gridheight = 1;
		buttonConstraints.weightx = 100;
		buttonConstraints.weighty = 100;
		buttonConstraints.insets = new Insets(0, 0, 0, 0);
		buttonConstraints.anchor = GridBagConstraints.CENTER;
		buttonConstraints.fill = GridBagConstraints.BOTH;

		//Creates clearButton and attatches listener
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new clearButtonListener());

		//Creates solveButton and attatches listener
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new SolveButtonListener());
	
		//Adds clear button to buttonPanel
		buttonPanel.add(clearButton, buttonConstraints);

		//Adds solve button to buttonPanel
		buttonConstraints.gridx = 1;
		buttonPanel.add(solveButton, buttonConstraints);
	}

	/*
	Sets up utilities that interface with the game
	*/
	private void setupUtilites() {
		gameSolver = new SudokuSolver();
	}

	/*
	Solves sudoku board
	*/
	private void solveSudoku() {
		//Gathers all board values into array
		int[] boardValues = new int[81];
		for(int boardTileNum = 0; boardTileNum < boardValues.length; boardTileNum++) {
			if(boardTextFields[boardTileNum].getText().equals("") || boardTextFields[boardTileNum].getText().equals("0")) {
				boardValues[boardTileNum] = 0;
			} else {
				boardValues[boardTileNum] = Integer.valueOf(boardTextFields[boardTileNum].getText());
			}
		}

		//Passes array to gameSolver and checks output for result
		gameSolver.updateGame(boardValues.clone());
		try {
			int[] newBoardValues = gameSolver.solve();
			//Sets board values to solved values
			for(int boardTileNum = 0; boardTileNum < 81; boardTileNum++) {
				boardTextFields[boardTileNum].setText(Integer.toString(newBoardValues[boardTileNum]));
			}
		} catch (IncompletePuzzleException e) {
			JOptionPane.showMessageDialog(mainPanel, "Unable to solve puzzle! Either your input was incorrect or the program cannot handle the puzzle's difficulty.", "Sudoku Error", JOptionPane.ERROR_MESSAGE);
		} catch (IncorrectPuzzleException e) {
			JOptionPane.showMessageDialog(mainPanel, "Puzzle produced incorrect output! Either your input was incorrect or there is an error in the program.", "Sudoku Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	Clears sudoku board
	*/
	private void clearBoard() {
		//Sets all text fields to an empty string
		for(int boardTextFieldNum = 0; boardTextFieldNum < boardTextFields.length; boardTextFieldNum++) {
			boardTextFields[boardTextFieldNum].setText("");
		}
	}

	/*
	Private class to check for clicks on the solve button
	*/
	private class SolveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			solveSudoku();
		}
	}

	/*
	Private class to check for clicks on the clear button
	*/
	private class clearButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			clearBoard();
		}
	}
}