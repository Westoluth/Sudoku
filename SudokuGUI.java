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
		for(int i=0; i < boardSubPanels.length; i++) {
			boardSubPanels[i] = new JPanel();
			GridLayout boardSubPanelLayout = new GridLayout(3, 3);
			boardSubPanels[i].setLayout(boardSubPanelLayout);
			boardSubPanels[i].setBorder(BorderFactory.createLineBorder(Color.black));
		}

		//Fills boardTextFields
		for(int i=0; i<boardTextFields.length; i++) {
			boardTextFields[i] = new JTextField(String.valueOf(i), 1);
		}

		//Places all text fields into sub panels
		for(int i=0; i<boardTextFields.length; i++) {
			boardSubPanels[i/9].add(boardTextFields[i]);
		}

		//Places all sub panels into panels
		for(int i=0; i<boardSubPanels.length; i++) {
			boardPanel.add(boardSubPanels[i]);
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

		//Creates solveButton and attatches listener
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new SolveButtonListener());
	
		//Adds solve button to buttonPanel
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
		for(int i=0; i < 81; i++) {
			if(boardTextFields[i].getText().equals("") || boardTextFields[i].getText().equals("0")) {
				boardValues[i] = 0;
			} else {
				boardValues[i] = Integer.valueOf(boardTextFields[i].getText());
			}
		}

		//Passes array to gameSolver
		gameSolver.updateGame(boardValues.clone());
		int[] newBoardValues = gameSolver.solve();

		//Sets board values to solved values
		for(int i=0; i < 81; i++) {
			boardTextFields[i].setText(Integer.toString(newBoardValues[i]));
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
}