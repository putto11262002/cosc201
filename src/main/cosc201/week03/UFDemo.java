package cosc201.week03;

/**
 *
 * @author Michael Albert
 */
public class UFDemo {

  public static void main(String[] args) {

    int n = 9;
    UnionFind[] ufs = new UnionFind[] {new UF1(), new UF2(), new UF3(), new UF4()};
    for(UnionFind u : ufs) {
      u.make(n);
      u.union(6,8);
      u.union(3,4);
      u.union(7,1);
      u.union(7,4);
      u.union(1,8);
      for(int i = 0; i < n; i++) System.out.print(u.find(i) + " ");
      System.out.println();
    }
    for(UnionFind u : ufs) System.out.println(u);
  }

}
