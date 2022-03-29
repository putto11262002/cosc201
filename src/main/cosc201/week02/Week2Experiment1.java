package cosc201.week02;

import cosc201.week02.Timer;

import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;
/**
 * Week 2, Experiment 1
 * 
 * a. What is the (typical) ratio between the time taken for sum(1000) 
 *    and sum(100)? Does that make sense? What \textit{should} the ratio be?
 * b. How large do you need to make $n$ before the ratio between the time 
 *    taken for sum(10*n) and sum(n) approaches what it should be? When you
 *    reach that point, how much total time (roughly) is being used?
 * c. What does this say about the design of wall-clock experiments to test 
 *    efficiency?
 * 
 * @author Michael Albert
 */
public class Week2Experiment1 {
  
  static final Random R = new Random();
  public final double MILLION = 1000000.00;
  private static final DecimalFormat df = new DecimalFormat("0.00");
  
  public static void main(String[] args) {
   
  
    averageRation(100, 100);
    

    

    

  }


  static void averageRation(int n,int  runs){
    double totalRatio = 0;
    double totalTime = 0;
    for(int i = 0; i <= 100; i++){
      Timer timer = new Timer();
      timer.start();
      double ratio = timedSum(10 *n)/timedSum(n);
      double time = timer.stop();
     System.out.println(ratio + " --- " +  time);
     totalRatio += ratio;
     totalTime += time;


    }
    System.out.println("Average ration: " + totalRatio/n + " --- Average time: " + totalTime/n);

  }


  
  
  static double sum(int n) {
    double result = 0;
    for(int i = 0; i < n; i++) result += R.nextDouble();
    return result;
  }
  
  static double timedSum(int n) {
    Timer timer = new Timer();
    timer.start();
    sum(n);
    return timer.stop();
  
  }
  
}
