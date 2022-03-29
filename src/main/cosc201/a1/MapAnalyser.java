package cosc201.a1;


import cosc201.UF4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Skeleton code for Assignment 1 in COSC201. Methods to be filled in according
 * to the specifications in the javadoc and/or the assignment PDF. Please check
 * if it's at all unclear to you what is required.
 * 
 * @author <your name and ID number here>
 */
public class MapAnalyser {

  private Map m;
  
  public MapAnalyser(Map m) {
    this.m = m;
  }

  /**
   * Counts the seas in the map, m.
   * 
   * The 'sea' that a given water cell belongs to consists of all of its water
   * neighbours, all their water neighbours, etc. That is, it is a region of
   * water so that every other cell that has a neighbour belonging to the
   * region must be land.
   *
   * @return the number of different seas in m.
   */
  public int countSeas() {
    int seaNum = 0;
    UF4 seaSet = new UF4();
    Set<Integer> visitedReps = new HashSet<Integer>();
    seaSet.make(this.m.cols * this.m.rows);
    for(int  r = 0; r < m.rows; r++){
      for(int c = 0; c < m.cols; c++){
        int centerIndex = m.index(r, c);
        if(m.isLand(centerIndex)) continue;
        explore(centerIndex, seaSet);
      }
    }

    for(int  r = 0; r < m.rows; r++){
      for(int c = 0; c < m.cols; c++){
        int idx = m.index(r, c);
        int rep = seaSet.find(idx);
        if(m.isLand(idx) || visitedReps.contains(rep)) continue;
        visitedReps.add(rep);
        seaNum++;
      }
    }
    return seaNum;
  }

  private void explore(int centerIndex, UF4 seaSet){
    ArrayList<Integer> neighbours = m.allNeighbours(centerIndex);
    for(int neighbour: neighbours){
      seaSet.union(neighbour, centerIndex);
    }

  }
  
  /**
   * Determines the size of the sea containing the cell in row r and
   * column c in the map, m.
   * 
   * The 'sea' that a given water cell belongs to consists of all of its water
   * neighbours, all their water neighbours, etc. That is, it is a region of
   * water so that every other cell that has a neighbour belonging to the
   * region must be land.
   *
   * @return the size of the sea containing the given cell.
   */
  public int seaSize(int r, int c) {
    // Fill in your code here.
    return -1; // Just so the skeleton code compiles
  }

  
}
