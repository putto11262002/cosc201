package cosc201.week02;



public class TimerDemo{

  static final int MILLION = 1000000;

  public static void main(String[] args) {
    
    Timer t = new Timer();
    for(int i = 0; i < 10; i++) {
      t.start();
      System.out.println(sum(1000000000) + " " + (t.stop()/MILLION));
    }
  }
  
  public static long sum(int n) {
    long result = 0;
    for(int i = 0; i < n; i++) {
      result += i;
    }
    return result;
  }

}