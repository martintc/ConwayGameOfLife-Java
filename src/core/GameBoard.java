package core;

/**
* GameBoard class is a class file is used as an object to represent the implementation of the gameboard that Conway's Game Of Life is played on
*
* @author Todd Martin
* @version 1.0
*/

public class GameBoard 
{
 
  private Status[][] gameBoard;
  
  /**
  * Constructor that initializes and empty GameBoard at the start of gameplay.
  *
  * @param initRow The initial length of rows for the multideminsional array
  * @param initColumn The initial length of columns for the multideminsional array
  */
  public GameBoard (int initRow, int initColumn)
  {
    gameBoard = new Status[initRow][initColumn];
    
    for (int row = 0; row < gameBoard.length; row++)
      for (int column = 0; column < gameBoard[0].length; column++)
        gameBoard[row][column] = Status.DEAD;
  }
  
  /**
  * seCellAlive method takes a cell and turns it into a cell that is alive.
  * 
  * @param cRow The row location of the cell
  * @param cColumn the Column location of the cell
  */
  public void setCellAlive (int cRow, int cColumn)
  {
   gameBoard[cRow][cColumn] = Status.ALIVE; 
  }
  
  /**
  * setCellDead takes a cell and turns it into a cell that is dead.
  *
  * @param cRow The row location of the cell
  * @param cColumn The row location of the cell
  */
  public void setCellDead (int cRow, int cColumn)
  {
    gameBoard[cRow][cColumn] = Status.DEAD;
  }
  
  /**
  * getCallState method returns the state of a cell at specified location in the GameBoard
  *
  * @param cRow The row location of a cell
  * @param cColumn The column location of a cell
  * @return status The status token of a cell at the specified location
  */
  public Status getCell (int cRow, int cColumn) 
  {
    return gameBoard[cRow][cColumn];
  }
 
 /**
 * getGameBoard method returns the game board
 *
 * @return Returns the gameboar represenation
 */
 public Status[][] getGameBoard ()
 {
  return gameBoard;
 }

    public int getRowLength () {
	return gameBoard.length;
    }

    public int getColumnLength () {
	return gameBoard[0].length;
    }

    public void processGeneration (int[][] data) {
        for (int row = 0; row < this.getRowLength(); row++)
            for (int column = 0; column < this.getColumnLength(); column++) {
                if (data[row][column] < 2 && gameBoard[row][column] == Status.ALIVE)
                    gameBoard[row][column] = Status.DEAD;
                    else if (data[row][column] > 3 && gameBoard[row][column] == Status.ALIVE) gameBoard[row][column] = Status.DEAD;
                    else if (data[row][column] == 3 && gameBoard[row][column] == Status.DEAD) gameBoard[row][column] = Status.ALIVE;
                             
            }
    }
    
}
