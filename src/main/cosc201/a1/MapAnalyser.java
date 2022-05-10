package cosc201.a1;


import cosc201.UF4;
import cosc201.UnionFind;


import java.util.*;

/**
 * Skeleton code for Assignment 1 in COSC201. Methods to be filled in according
 * to the specifications in the javadoc and/or the assignment PDF. Please check
 * if it's at all unclear to you what is required.
 * 
 * @author 4893969
 */
public class MapAnalyser {

  private Map m;
  private UnionFind seaSets;
  
  public MapAnalyser(Map m) {
    this.m = m;
    this.seaSets = new UF4();
  }

  // for experiment 1
  public MapAnalyser(Map m, UnionFind uf) {
    this.m = m;
    this.seaSets = uf;

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
    Set<Integer> visitedReps = new HashSet<Integer>();
    this.seaSets.make(this.m.cols * this.m.rows);
    for(int  r = 0; r < m.rows; r++){
      for(int c = 0; c < m.cols; c++){
        int centerIndex = m.index(r, c);
        if(m.isLand(centerIndex)) continue;
        explore(centerIndex, this.seaSets);
      }
    }

    for(int  r = 0; r < m.rows; r++){
      for(int c = 0; c < m.cols; c++){
        int idx = m.index(r, c);
        int rep = this.seaSets.find(idx);
        if(m.isLand(idx) || visitedReps.contains(rep)) continue;
        visitedReps.add(rep);

        seaNum++;
      }
    }

    return seaNum;
  }

  private void explore(int centerIndex, UnionFind seaSet){

    ArrayList<Integer> neighbours = m.allNeighbours(centerIndex);
    for(int neighbour: neighbours){
      if(m.isLand(neighbour)) continue;
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
    Stack<Integer> s = new Stack<Integer>();
    Set<Integer> visited = new HashSet<Integer>();
    int seaSize = 0;
    int current = m.index(r, c);
    if(m.isLand(current)) return seaSize;
    s.push(current);
    visited.add(current);
    while(!s.empty()){
      seaSize = seaSize + 1;
      current = s.pop();
      ArrayList<Integer> neighbours = m.allNeighbours(current);
      for(int neighbour: neighbours){
        if(m.isLand(neighbour)) continue;
        if(visited.contains(neighbour)) continue;
        visited.add(neighbour);
        s.push(neighbour);


      }
    }



    return seaSize;
  }

  
}
