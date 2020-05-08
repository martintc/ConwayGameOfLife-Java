package ui;

import core.*;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Text user Interface console class. This is the class invoked for running Conway's game of life in text mode.
 *
 * @author Todd Martin
 */

public class GameOfLifeTextConsole {

    private ConwayGameOfLife game;
    private long time = 15000;

    /**
     * Run method that controls the logic of the flow of the test UI.
     */
    public void run () {
        try {
            initGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initGame () throws InterruptedException {
        getSizeDefaultOrCustom();
        displayBoard();
        initBoard();
        int step = 0;
        
        while (step > -1) {
            step = playForward();
            while (step > 0) {
                game.stepGeneration();
                displayBoard();
                if (step > 1) {
                    Thread.sleep(time);
                }
                step--;
            }
        }
    }

    /**
     * Method where default of custom size is set for Conway's Game Of Life.
     */
    private void getSizeDefaultOrCustom () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like default size (100x100) or a custom size?\n\tD - Default\n\tC - Custom");
        System.out.print(">> ");
        try {
            String option = scan.nextLine();
            if (option.equals("D"))
                game = new ConwayGameOfLife();
            else if (option.equals("C"))
                getCustomSize(scan);
            else
                throw new InputMismatchException();
        } catch (InputMismatchException e) {
            System.out.println("That was not a correct input, please try again");
            getSizeDefaultOrCustom();
        }
    }

    /**
     * Method for getting user input for custom board size.
     *
     * @param scan Scanner object to get input
     * @throws InputMismatchException
     */
    private void getCustomSize (Scanner scan) throws InputMismatchException {
        System.out.println("Please enter a row size");
        System.out.print(">> ");
        int row = scan.nextInt();
        System.out.println("Please enter a column size");
        System.out.print(">> ");
        int column = scan.nextInt();
        game = new ConwayGameOfLife(row, column);
    }

    private void displayBoard() {
        Status[][] board = game.getGameBoard();
        System.out.println("Current Generation: " + game.getGeneration());
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++) {
                String condition = getRepresentationOfCondition(board[r][c]);
                System.out.print("|" + condition);
                if (c == board[0].length-1)
                    System.out.print("|\n");
            }
    }

    /**
     * Take the board piece representation and returns the appropriate representaion for text console.
     * @param s the internal representation from the gameboard
     * @return Returns a representation for a gamepeice for the text console
     */
    private String getRepresentationOfCondition (Status s) {
        if (s == Status.ALIVE) return "o";
        else if (s == Status.DEAD) return " ";
        else return "E";
    }

    /**
     * Method to initialize the board and game.
     */
    private void initBoard () {
        Scanner scan = new Scanner(System.in);
        boolean isInitializingCells = true;
        while (isInitializingCells) {
            System.out.println("Do you want to initialize a cell?");
            System.out.println("\tY - yes\n\tN -no");
            System.out.print(">> ");
            try {
                String option = scan.nextLine();
                if (option.equals("Y")) {
                    int row = getCellRowForInit(scan);
                    int column = getCellColumnForInit(scan);
                    game.initializeCell(row, column);
                } else if (option.equals("N")) {
                    isInitializingCells = false;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("try again....");
            }
            displayBoard();
        }
        return;
        
    }

    /**
     * Method to get input for user initialize board peice's row
     * @param scan Scanner object
     * @return row for new peice location
     */
    private int getCellRowForInit (Scanner scan) {
        System.out.println("Enter a row location");
        System.out.print(">> ");
        try {
            int selection = scan.nextInt();
            return selection;
        } catch (InputMismatchException e) {
            System.out.println("Try again...");
            return getCellRowForInit(scan);
        }
    }

    /**
     * Method to get input for user initialize board peice's column
     * @param scan Scanner Object
     * @return column for new piece location
     */
    private int getCellColumnForInit (Scanner scan) {
        System.out.println("Enter a column location");
        System.out.print(">> ");
        try {
            int selection = scan.nextInt();
            return selection;
        } catch (InputMismatchException e) {
            System.out.println("Try again...");
            return getCellColumnForInit(scan);
        }
    }

    /**
     * plays the game forward; returns a number if moving forward one generation, multiple, or none
     * @return number of generations to move forward
     */
    private int playForward () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to step forward a generation?");
        System.out.println("\tY - yes\n\tN - no\n\tM - multi-step");
        System.out.print(">>" );
        try {
            String choice = scan.nextLine();
            if (choice.equals("Y"))
                return 1;
            else if (choice.equals("N"))
                return 0;
            else if (choice.equals("M"))
                return multiStepForward(scan);
            else
                throw new Exception();
        } catch (Exception e) {
            System.out.println("Try again....");
            return playForward();
        }
    }

    /**
     * Gets user input for information for stepping forward multiple generations
     *
     * @param s Scanner object
     * @return number of generations to step forward
     */
    private int multiStepForward (Scanner s) {
        int numberOfGenerations = getNumberOfGenerations(s);
        time = getTimeToPause(s);
        return numberOfGenerations;
    }

    /**
     * Method to get number of generations
     * @param s Scanner object
     * @return number of generations to move forward
     */
    private int getNumberOfGenerations (Scanner s) {
        System.out.println("Enter number of generations to step through.");
        System.out.print(">> ");
        try {
            int gen = s.nextInt();
            if (gen < 1)
                throw new InputMismatchException();
            else
                return gen;
        } catch (InputMismatchException e) {
            System.out.println("Try again....");
            return getNumberOfGenerations(s);
        }
    }

    /**
     * Get time that will be used to pause. Gets seconds then multiplies by 1000 to get mili seconds
     *
     * @param s Scanner Object
     * @return Returns number of miliseconds
     */
    private long getTimeToPause(Scanner s) {
        System.out.println("How mane seconds to pause between generations? [default: " + time/1000 + "s; -1 for default]");
        System.out.print(">> ");
        try {
            long t = s.nextLong();
            if (t == -1)
                return 15000;
            else if (t < -1)
                throw new InputMismatchException();
            else {
                return (t * 1000);
            }
        } catch (InputMismatchException e) {
            System.out.println("Try again.....");
            return getTimeToPause(s);
        }
    }
    
}
