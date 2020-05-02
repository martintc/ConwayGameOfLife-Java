package core;

public class NeighborChecker implements CheckNeighborsInterface {

    private GameBoard board;
    private int[][] cellNeighborData;
    private int rowLength;
    private int columnLength;
    
    public NeighborChecker (GameBoard board) {
	this.board = board;
	rowLength = board.getRowLength();
	columnLength = board.getColumnLength();
	cellNeighborData = new int[rowLength][columnLength];
	initializeCellNeighborData();
    }

    private void initializeCellNeighborData () {
	for (int row = 0; row < rowLength; row++)
	    for (int column = 0; column < columnLength; column++)
		cellNeighborData[row][column] = 0;
    }

    public int[][] getCellNeighborData () {
	for (int row = 0; row < rowLength; row++)
	    for (int column = 0; column < columnLength; column++) {
		checkNorthNeighbor(row, column);
		checkNorthEastNeighbor(row, column);
		checkEastNeighbor(row, column);
		checkSouthEastNeighbor(row, column);
		checkSouthNeighbor(row, column);
		checkSouthWestNeighbor(row, column);
		checkWestNeighbor(row, column);
		checkNorthWestNeighbor(row, column);
	    }
	return cellNeighborData;
    }

    private boolean isSpaceValid (int row, int column) {
	if (row >= 0 && row < rowLength)
	    if (column >= 0 && column < columnLength)
		return true;
	return false;
    }

    private void incrementData (int row, int column) {
	cellNeighborData[row][column] = cellNeighborData[row][column] + 1;
    }

    private void checkNorthNeighbor (int row, int column) {
	if (isSpaceValid(row-1, column))
	    if (board.getCell(row-1, column) == Status.ALIVE)
		incrementData(row, column);
    }

    private void checkNorthEastNeighbor (int row, int column) {
	if (isSpaceValid(row-1, column+1))
	    if (board.getCell(row-1, column+1) == Status.ALIVE)
		incrementData(row, column);
    }

    private void checkEastNeighbor (int row, int column) {
	if (isSpaceValid(row, column+1))
	    if (board.getCell(row, column+1) == Status.ALIVE)
		incrementData(row, column);
    }

    private void checkSouthEastNeighbor (int row, int column) {
        if (isSpaceValid(row+1, column+1))
            if (board.getCell(row+1, column+1) == Status.ALIVE)
                incrementData(row, column);
    }

    private void checkSouthNeighbor (int row, int column) {
	if (isSpaceValid(row+1, column))
	    if (board.getCell(row+1, column) == Status.ALIVE)
		incrementData(row, column);
    }

    private void checkSouthWestNeighbor (int row, int column) {
	if (isSpaceValid(row+1, column-1))
	    if (board.getCell(row+1, column-1) == Status.ALIVE)
		incrementData(row, column);
    }

    private void checkWestNeighbor (int row, int column) {
	if (isSpaceValid(row, column-1))
	    if (board.getCell(row, column-1) == Status.ALIVE)
		incrementData(row, column);
    }

    private void checkNorthWestNeighbor (int row, int column) {
	if (isSpaceValid(row-1, column-1))
	    if (board.getCell(row-1, column-1) == Status.ALIVE)
		incrementData(row, column);
    }
    
}
