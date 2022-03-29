package cosc201.a1;

import java.util.ArrayList;
import java.util.Random;

/**
 * For our purposes, a map is just a grid consisting of identified water
 * and land tiles.
 * 
 * @author Michael Albert
 */
public class Map {

  static final boolean LAND = true;
  static final boolean WATER = false;
  static final char WATER_CHAR = '.';
  static final char LAND_CHAR = '#';
  static final int[] DIFFS = {-1, 0, 1};

  static final Random R = new Random();



  boolean[][] map;
  int rows;
  int cols;
  

  public Map(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.map = new boolean[rows][cols];
  }

  /**
   * Set seed for RNG for flood function
   * @param seed
   */
  public void setSeed(long seed){
    R.setSeed(seed);
  }

  /**
   * Floods the map so that each cell is water with a given probability.
   * This is a convenience method to help with answering question 2.
   * 
   * @param water_prob The probability that a cell is water.
   */
  public void flood(double water_prob) {
    for(int r = 0; r < rows; r++) {
      for(int c = 0; c < cols; c++) {

        if (R.nextDouble() < water_prob) {
          map[r][c] = WATER;
        } else {
          map[r][c] = LAND;
        }
      }
    }
  }

  /**
   * Returns a string representation of this map.
   * 
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    for(int r = 0; r < rows; r++) {
      for(int c = 0; c < cols; c++) {
        if (map[r][c] == WATER) {
          result.append(WATER_CHAR);
        } else {
          result.append(LAND_CHAR);
        }
      }
      result.append('\n');
    }
    result.deleteCharAt(result.length()-1);
    return result.toString();
  }

  private boolean isValidCoordinate(int r, int c) {
    return r >= 0 && r < rows && c >= 0 && c < cols;
  }

  private boolean isValidIndex(int i) {
    return 0 <= i && i < rows*cols;
  }

  /**
   * Determines the index of the given pair where the index starts from 0 
   * in the upper left corner (row 0, col 0) and increases along each row 
   * and then down through columns.
   * 
   * @param r The row number
   * @param c The column number
   * @return The index of the cell at (r,c)
   */
  public int index(int r, int c) {
    if (!isValidCoordinate(r,c)) {
      throw new IndexOutOfBoundsException("Row index " + r + " and/or column index " + c + " out of bounds.");
    }
    return r*cols + c;
  }

  /**
   * Returns the indices of all neighbours of the cell of index i.
   * @param i The index of a cell
   * @return The indices of all its neighbours
   */
  public ArrayList<Integer> allNeighbours(int i) {
    ArrayList<Integer> result = new ArrayList<>();
    int[] rc = coords(i);
    int r = rc[0];
    int c = rc[1];
    for(int dr : DIFFS) { 
      for(int dc: DIFFS) {
        if (dr == 0 && dc == 0) continue;
        if (isValidCoordinate(r+dr, c+dc)) {
          result.add(index(r + dr, c + dc));
        }
      } 
    }
    return result;
  }

  /**
   * Generates a row-column coordinate pair for an index if possible.
   * 
   * @param index The index
   * @return A two-element array representing the coordinates of that index.
   */
  public int[] coords(int index) {
    if (!isValidIndex(index)) {
      throw new IndexOutOfBoundsException(index + " out of bounds as an index.");
    }
    return new int[] {index/cols, index % cols};
  }

  /**
   * Determines whether the cell at a given index is water.
   * 
   * @param i The index of a cell
   * @return true if the cell is water.
   */
  public boolean isWater(int i) {
    int[] coords = coords(i);
    return map[coords[0]][coords[1]] == WATER;
  }

  /**
   * Determines whether the cell at a given index is land.
   * 
   * @param i The index of a cell
   * @return true if the cell is land.
   */
  public boolean isLand(int i) {
    return !isWater(i);
  }

  
}
