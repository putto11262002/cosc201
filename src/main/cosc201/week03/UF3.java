package cosc201.week03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The third union-find implementation using local representatives augmented
 * by a rank function.
 *
 * Time for union is big-Theta of time for find.
 * Time for find is Theta(log n) in worst case.
 *
 * @author Michael Albert
 */
public class UF3 implements UnionFind {

  int[] reps; // Now just 'immediate' representative.
  int[] rank; // The rank, i.e., length of longest chain below a representative
  int[] sizes;
  int numSet;

  /**
   * Constructs a new uninitialised union-find instance
   * 
   */
  public UF3() {
  }

  /**
   * Makes a new set of singletons (erases all current information).
   * 
   * @param n the number of singletons
   */
  @Override
  public void make(int n) {
    reps = new int[n];
    rank = new int[n];
    sizes = new int[n];
    for (int i = 0; i < n; i++) {
      reps[i] = i;
      rank[i] = 0;
      sizes[i] = 1;
    }
  }

  /**
   * Finds the representative of a group and compresses the path to the
   * representative.
   * 
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
   * Join the groups containing two elements. The new representative is
   * the previous representative of larger rank. This ensures that the rank
   * increases only if the two ranks are equal. If the ranks are equal, the
   * representative of the second element is the new representative.
   * 
   * @param x the first element
   * @param y the second element
   */
  @Override
  public void union(int x, int y) {
    rootUnion(find(x), find(y));
  }

  @Override
  public String toString() {
    return "UF3" + Arrays.toString(reps);
  }

  @Override
  public int size() {
    return reps.length;
  }

  private void rootUnion(int x, int y) { // x and y are known to be representatives
    if (rank[x] > rank[y]) {
      reps[y] = x;

      sizes[x] = sizes[x] + sizes[y];
      sizes[y] = 0;

    } else if (rank[y] > rank[x]) {
      reps[x] = y;

    } else {
      reps[x] = y;
      rank[y]++;
      sizes[y] = sizes[y] + sizes[x];
      sizes[x] = 0;
      numSet++;
    }
  }

  public int[] all(int x) {
    int rx = find(x);

  
    int[] members = new int[sizes[rx]];
    int memberIdx = 0;
    for (int i = 0; i < reps.length; i++) {
      if (find(i) == rx) {
        members[memberIdx] = i;
        memberIdx++;
      }
    }
    return members;
  }

  public int[][] summary() {
    int[][] summary;
    summary = new int[numSet][];
    Set<Integer> addedReps = new HashSet<Integer>();

    int idx = 0;
    for (int i = 0; i < rank.length; i++) {
      if(i != reps[i]){
        if(addedReps.contains(i)) continue;
        int ri = find(i);
        int[] members = new int[sizes[ri]];
        int memberIdx = 0;
        // similar implementation to the all method but add the nodes that has been explored to addedReps set
        for (int j = 0; j < reps.length; j++) {
          if (find(j) == ri) {
            members[memberIdx] = j;
            memberIdx++;
            addedReps.add(j);
          }
        }
        summary[idx] = members;
        idx++;

      }
      
     

      }

    
    return summary;
  }

}
