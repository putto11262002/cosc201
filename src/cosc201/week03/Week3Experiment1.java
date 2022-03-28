package cosc201.week03;

import cosc201.Timer;

/**
 * Week 3, Experiment 1
 * 
 * a. Look at the times required to compute fibRec(n) for various values of n.
 *    How do these times behave? How large a value of n can you (realistically)
 *    apply this method to?
 * b. Do the other implementations produce the same sequence of values? Aside
 *    from "running the code and checking" are you confident of this (based
 *     on reading the code)?
 * c. How large a value of n can you apply the other methods to in a
 *    reasonable length of time? What's the limiting condition?
 * d. Which of the other methods is "best"?
 * 
 * @author Michael Albert
 */
public class Week3Experiment1 {

  public static void main(String[] args) {
    Timer timer = new Timer();
    // for(int n = 1; n < 100; n++){
    // timer.start();
    // fibRec(n);
    // long time = timer.stop();
    // System.out.println("n: " + n + " time: " + time);
    // }

    // long time;
    // long res;
    // for(int n = 1; n < 10000; n++){
    //   System.out.println("n: "+ n);
    //   timer.start();
    //   res = fibRec(n);
    //   time = timer.stop();
    //   System.out.println(" fibRec: " + res + ", " + time);

    //   timer.start();
    //   res = fibA(n);
    //   time = timer.stop();
    //   System.out.println(" fibA: " + res + ", " + time);

    //   timer.start();
    //   res = fibB(n);
    //   time = timer.stop();
    //   System.out.println(" fibB: " + res + ", " + time);

    //   timer.start();
    //   res = fibC(n);
    //   time = timer.stop();
    //   System.out.println(" fibC: " + res + ", " + time);

     

    
    // }

    
    // long time;
    // final double LIMIT = 500000.00; // half milisecond
    // boolean fibARunning = true;
    // boolean fibBRunning = true;
    // boolean fibCRunning = true;
    // int n= 1;
    // while(fibARunning || fibBRunning || fibCRunning) {
    
    //   if (fibARunning) {
    //     timer.start();
    //     fibA(n);
    //     time = timer.stop();
     
    //     if (time > LIMIT) {
    //       System.out.println("n: " + n+ " fibA: " + time);
    //       fibARunning = false;
    //     }
    //   }

    //   if (fibBRunning) {
    //     timer.start();
        
    //     fibB(n);
    //     time = timer.stop();
       
    //     if (time > LIMIT) {
    //       System.out.println("n: " + n + " fibB: " + time);
    //       fibBRunning = false;
    //     }
    //   }

    //   if (fibCRunning) {
    //     timer.start();
    //     fibC(n);
    //     time = timer.stop();
        
    //     if (time > LIMIT) {
    //       System.out.println("n: " + n+ " fibC: " + time);
    //       fibCRunning = false;
    //     }

    //   }

    //   n++;
    // }

    
    for(int n = 1; n < 1000; n++){
    timer.start();
    fibA(n);
    long fibATime = timer.stop();
    

    timer.start();
    fibB(n);
    long fibBTime = timer.stop();
   

    timer.start();
    fibC(n);
    long fibCTime = timer.stop();
   

    if(n % 100 == 0){
      System.out.println("n: " + n + " fibA: " + fibATime+ " fibB: "+ fibBTime + " fibC: " + fibCTime);
    }
 
  }
  }
   
  static long fibRec(int n) {
    if (n <= 1) return 1;
    return fibRec(n-1) + fibRec(n-2);
  }
  
  static long fibA(int n) {
    long a = 1;
    long b = 1;
    for(int i = 0; i < n; i++) {
      long c = a + b;
      a = b;
      b = c;
    }
    return a;
  }
  
  static long fibB(int n) {
    long[] result = new long[n+1];
    result[0] = 1;
    result[1] = 1;
    for(int k = 2; k <= n; k++) {
      result[k] = result[k-1] + result[k-2];
    }
    return result[n];
  }
  
  static long fibC(int n) {
    return fibC(1, 1, n);
  }
  
  static long fibC(int a, int b, int n) {
    if (n == 0) return a;
    return fibC(b, a+b, n-1);
  }
  
}
