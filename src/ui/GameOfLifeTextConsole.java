package ui;

import core.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class GameOfLifeTextConsole {

    private ConwayGameOfLife game;

    public void run () {
        initGame();
    }

    private void initGame () {
        getSizeDefaultOrCustom();
        displayBoard();
        initBoard();
        boolean step = true;
        
        while (step) {
            game.stepGeneration();
            displayBoard();
            step = playForward();
        }
    }

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

    private String getRepresentationOfCondition (Status s) {
        if (s == Status.ALIVE) return "o";
        else if (s == Status.DEAD) return " ";
        else return "E";
    }

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

    private boolean playForward () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to step forward a generation?");
        System.out.println("\tY - yes\n\tN - no");
        System.out.print(">>" );
        try {
            String choice = scan.nextLine();
            if (choice.equals("Y"))
                return true;
            else if (choice.equals("N"))
                return false;
            else
                throw new Exception();
        } catch (Exception e) {
            System.out.println("Try again....");
            return playForward();
        }
    }
    
}
