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
  
  public ConwayGameOfLife ()
  {
    gameBoard = new GameBoard(DEFAULT_ROW, DEFAULT_COLUMN);
    }
}
