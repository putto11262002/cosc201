package cosc201;

import java.util.Arrays;

/**
 * The second union-find implementation using basic 'local' representatives.
 *
 * Time for union is big-O of time for find.
 * Unfortunately, time for find is Theta(n) in worst case.
 *
 * @author Michael Albert
 */
public class UF2 implements UnionFind {

  int[] reps; // Now just 'immediate' representative.

  /**
   * Constructs a new uninitialised union-find instance
   * 
   */
  public UF2() {}
  
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
    while (x != reps[x]) {
      x = reps[x];
    }
    return x;
  }

  /**
   * Join the groups containing two elements by setting all the representatives
   * for elements of the first group to the representative of the second group.
   * @param x the first element
   * @param y the second element
   */
  @Override
  public void union(int x, int y) {
    reps[find(x)] = find(y);
  }
  
  @Override
  public int size() {
    return reps.length;
  }

  
  @Override
  public String toString() {
    return "UF2" + Arrays.toString(reps);
  }

}
