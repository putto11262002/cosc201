package cosc201.week0709;

public class TestBST {
    public static void main(String[] args){
        BST t = new BST();

      t.add("p");
      t.add("t");
      t.add("a");
      t.add("h");
      t.add("r");
      t.add("b");


      System.out.println(t.next("a"));



        System.out.println(t.toString());

    }
}
