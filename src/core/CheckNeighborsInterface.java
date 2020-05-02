package core;

public interface CheckNeighborsInterface {


    /**
     * get the total number of neighbors that are alive for a cell. In in all directions; north, south, east, west, and diagonals
     *
     * @return an array of integers that represent how many neighbors a cell has
     */
    abstract int[][] getCellNeighborData ();
    
}
