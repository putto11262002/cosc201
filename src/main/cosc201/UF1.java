package cosc201;

import java.util.Arrays;

/**
 * The first union-find implementation using a simple linear array of
 * representatives.
 *
 * Time for find is O(1) but time for union is O(n).
 *
 * @author Michael Albert
 */
public class UF1 implements UnionFind {

  int[] reps;

  /**
   * Constructs a new uninitialised union-find instance
   * 
   */
  public UF1() {}
  
  /**
   * Makes a new set of singletons (erases all current information).
   * @param n the number of singletons
   */
  @Override
  public void make(int n) {
    reps = new int[n];
    for(int i = 0; i < n; i++) {
      reps[i] = i;
    }
  }

  /**
   * Finds the representative of a group
   * @param x an element of the union-find structure
   * @return the representative for the element x
   */
  @Override
  public int find(int x) {
    return reps[x];
  }

  /**
   * Join the groups containing two elements by setting all the representatives
   * for elements of the first group to the representative of the second group.
   * @param x the first element
   * @param y the second element
   */
  @Override
  public void union(int x, int y) {
    int rx = reps[x];
    int ry = reps[y];
    if (rx == ry) {
      return;
    }
    for (int i = 0; i < reps.length; i++) {
      if (reps[i] == rx) {
        reps[i] = ry;
      }
    }
  }
  
  @Override
  public String toString() {
    return "UF1" + Arrays.toString(reps);
  }
  
  @Override
  public int size() {
    return reps.length;
  }


}
