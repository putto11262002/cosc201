package cosc201.a2;

import java.math.BigInteger;
import java.util.Arrays;


/**
 * Skeleton code for Part 1 of Assignment 2 in COSC201. Methods to be
 * filled in according to the specifications in the javadoc and/or the 
 * assignment PDF. Please check if it's at all unclear to you what is required.
 * 
 * @author Put Suthisrisinlpa 4893969
 */
public class RouteCounter {

  public static final boolean DAMAGED = true;
  public static final boolean SOUND   = false; // SOUND means UNDAMAGED

  private int rows;
  private int cols;
  private boolean[][] damaged;

  /**
   * This constructor builds a grid given the rows and columns, with 
   * no damage (since boolean defaults to false, i.e., SOUND)
   * 
   * @param rows the number of rows
   * @param cols the number of columns
   */
  public RouteCounter(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.damaged = new boolean[rows][cols];
  }

  /**
   * This constructor constructs the grid given the description as
   * a 2D-array of its damaged cells. Note that damaged[r][c] being true
   * means that location at row r and column c is damaged.
   * 
   * @param damaged the array describing damage locations
   */
  public RouteCounter(boolean[][] damaged) {
    this(damaged.length, damaged[0].length);
    for(int r = 0; r < rows; r++) {
      for(int c = 0; c < cols; c++) {
        this.damaged[r][c] = damaged[r][c];
      }
    }
  }

  /**
   * Make the given location damaged (you may assume the location
   * is valid for the grid).
   *
   * @param row the row index
   * @param col the column index
   */
  public void damage(int row, int col) {
    damaged[row][col] = DAMAGED;
  }

  /**
   * Repair the given location if necessary (you may assume the location
   * is valid for the grid).
   *
   * @param row the row index
   * @param col the column index
   */
  public void repair(int row, int col) {
    damaged[row][col] = SOUND;
  }

  /**
   * Counts the number of routes from the upper left to the lower right
   * of the corresponding grid (not using any damaged nodes).
   * 
   * @return the number of routes
   */
  public long countRoutes() {
    long[][] counts = new long[this.rows][this.cols];
    // initialise the first cell
    counts[0][0] = damaged[0][0] == DAMAGED ? 0 : 1;
    // populate the first row counts
    for(int r  =  1; r < this.rows; r++) counts[r][0] = (damaged[r][0] == DAMAGED || counts[r-1][0] == 0) ?  0 :  1;
    // populate first column counts
    for(int c = 1; c < this.cols; c++) counts[0][c] = (damaged[0][c] == DAMAGED || counts[0][c-1] == 0) ? 0 : 1;
    for(int r = 1; r < this.rows; r++){
      for(int c = 1; c < this.cols; c++){
        counts[r][c] = (damaged[r-1][c] == DAMAGED ? 0 : counts[r-1][c])+ (damaged[r][c-1] == DAMAGED ? 0 : counts[r][c-1]);
      }

    }
    return counts[this.rows-1][this.cols-1];
  }

  /**
   * Counts the number of routes from the upper left to the lower right
   * of the corresponding grid (not using any damaged nodes) using
   * BigInteger to avoid overflow.
   * 
   * @return the number of routes as a BigInteger
   */
  public BigInteger bigCountRoutes() {
    BigInteger[][] counts = new BigInteger[this.rows][this.cols];

    counts[0][0] = damaged[0][0] == DAMAGED ? BigInteger.ZERO : BigInteger.ONE;
    for(int r = 1; r < this.rows; r++) counts[r][0] = (damaged[r][0] == DAMAGED || counts[r-1][0].equals(BigInteger.ZERO)== DAMAGED) ? BigInteger.ZERO : BigInteger.ONE;
    for(int c = 1; c < this.cols; c++) counts[0][c] = (damaged[0][c] == DAMAGED || counts[0][c-1].equals(BigInteger.ZERO) == DAMAGED) ? BigInteger.ZERO : BigInteger.ONE;
    for(int r = 1; r < this.rows; r++){
      for(int c = 1; c < this.cols; c++){
        counts[r][c] = (damaged[r-1][c] == DAMAGED ? BigInteger.ZERO : counts[r-1][c]).add(damaged[r][c-1] == DAMAGED ? BigInteger.ZERO : counts[r][c-1]);
      }
    }
    return counts[this.rows-1][this.cols-1];
  }
  
  
}
