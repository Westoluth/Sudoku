package SudokuGUI;

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

//Suppresses serial warnings
@SuppressWarnings("serial")

public class SudokuGUI extends JFrame {
	JPanel mainPanel;
	JPanel boardPanel;
	JPanel buttonPanel;
	JButton solveButton;
	JTextField[] boardTextFields;

	public static void main(String[] args) {
		new SudokuGUI();
	}

	public SudokuGUI() {
		//Sets up window
		setupWindow();
	}

	private void setupWindow() {
		//Sets basic window parameters
		setSize(450, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sudoku");

		//Sets up main panel and adds it to frame
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		//Creates and sets default GridBagLayout constraints
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 100;
		constraints.weighty = 100;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;

		//Sets up board panel and adds it to frame
		setupBoard();
		mainPanel.add(boardPanel, constraints);

		//Sets up solve button and adds it to frame
		setupSolve();
		constraints.gridy = 1;
		constraints.weightx = 25;
		constraints.weighty = 25;
		mainPanel.add(solveButton, constraints);

		//Adds mainPanel to frame
		this.add(mainPanel);

		//Reveals window
		setVisible(true);

	}

	
	private void setupBoard() {
		boardPanel = new JPanel();

		GridLayout boardLayout = new GridLayout(9, 9);
		boardPanel.setLayout(boardLayout);

		boardTextFields = new JTextField[81];

		for(int i=0; i<boardTextFields.length; i++) {
			boardTextFields[i] = new JTextField("", 1);
			boardPanel.add(boardTextFields[i]);
		}
	}

	private void setupSolve() {
		solveButton = new JButton("Solve");
	}
}