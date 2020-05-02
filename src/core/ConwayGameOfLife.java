package core;

/** ConwayGameOfLife class controls the logic and gameboard for the Game of Life. It performs game operations in accordance
* with the rules of the game. It has option to step 1 generation, or multiple generations and the ability to pause between each
* each generation if wanted.
*
* @author Todd Martin
* @version 1.0
*/

public class ConwayGameOfLife
{

  private GameBoard gameBoard;
  private int generation; // number generation the game is on
  private long sleepDuration; // how long to pause between generations in miliseconds
  private final int DEFAULT_ROW = 100;
  private final int DEFAULT_COLUMN = 100;
  
  /**
  * Default constructor that initializes the GameBoard with a default row and column length of 100.
  */
  public ConwayGameOfLife ()
  {
    gameBoard = new GameBoard(DEFAULT_ROW, DEFAULT_COLUMN);
    generation = 0;
  }
  
  /** 
  * Constructor that initializes a gameboard based on user choice, sleep duration is not set.
  * 
  * @param initRow User choosen row length
  * @param initColumn User choosen column length
  */
  public ConwayGameOfLife (int initRow, int initColumn) 
  {
    gameBoard = new GameBoard(initRow, initColumn);
    generation = 0;
  }
  
  /**
  * Constructor that initializes a gameboard based on user choice and sleep duration between generations is set for when
  * stepping through multiple generations
  *
  * @param initRow User choosen row length
  * @param initColumn User choosen column length
  * @param initSleepDuraction User choosen duration in milliseconds for the program to sleep (pause) between generations
  */
  public void ConwayGameOfLife (int initRow, int initColumn, long initSleepDuration)
  {
    gameBoard = new GameBoard(initRow, initColumn);
    generation = 0;
    sleepDuration = initSleepDuration;
  }

    public void initializeCell (int row, int column) {
        gameBoard.setCellAlive(row, column);
    }
  
  /**
   * getGameBoard method returns the gameboard
   *
   * @return returns the gameboard representation in the GameBoard object
   */
  public Status[][] getGameBoard () 
  {
   return gameBoard.getGameBoard(); 
  }
  
  /**
  * getGeneration method returns the number of generation the game is currently in.
  *
  * @return Returns the integer number of the generation the game is currently in.
  */
  public int getGeneration ()
  {
    return generation;
  }
  
    public void stepGeneration () {
	NeighborChecker checker = new NeighborChecker(gameBoard);
	int[][] neighborData = checker.getCellNeighborData();
        gameBoard.processGeneration(neighborData);
        generation++;
    }
  
}
