package cosc201.a2;

/**
 * Counts routes from upper left to lower right in an r x c grid.
 * Simple illustration of dynamic programming techniques.
 */
public class RC {

  int rows;
  int cols;

  public RC(int r, int c) {
    this.rows = r; 
    this.cols = c;
  }

  public long count() {
    long[][] counts = new long[rows][cols];
    for(int r = 0; r < rows; r++) counts[r][0] = 1;
    for(int c = 0; c < cols; c++) counts[0][c] = 1;
    for(int r = 1; r < rows; r++) {
      for(int c = 1; c < cols; c++) {
        counts[r][c] = counts[r-1][c] + counts[r][c-1];
      }
    }
    return counts[rows-1][cols-1];
  }

  public static void main(String[] args) {
    int r = Integer.parseInt(args[0]);
    int c = Integer.parseInt(args[1]);
    System.out.println("Paths in a " + r + "x" + c + " grid = " + (new RC(r,c)).count());
  }
  
}
