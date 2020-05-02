import ui.*;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
* Entry class for Conway's Game of Life program. The program provides a main class that starts the program.
*
* @author Todd Martin
* @version 1.0
*/

public class ConwayGoL 
{

  /**
  * Main method, entry point of program.
  *
  * @param args Command line arguments passed into program
  */
  public static void main (String[] args) {
      System.out.println("Welcome to Conway's Game of Life");
      System.out.println("Please select a mode: \n\tT - Text");
      String mode = getMode();

      if (mode.equals("T")) {
          GameOfLifeTextConsole game = new GameOfLifeTextConsole();
          game.run();
      } else {
          System.out.println("Error.... Exiting");
          System.exit(0);
      }
  }

    private static String getMode () {
        Scanner scan = new Scanner(System.in);
        System.out.print(">> ");
        try {
            String choice = scan.nextLine();
            if (choice.equals("T")) return choice;
            else throw new InputMismatchException();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, try again");
            return getMode();
        }
    }

}
