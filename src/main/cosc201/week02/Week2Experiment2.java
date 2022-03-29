package cosc201.week02;

import cosc201.week02.Timer;

import java.util.Random;


/**
 * Week 2 Experiment 2
 * 
 * The skeleton outline for this experiment includes three methods for
 * constructing a random string of lower case letters. 
 * 
 * a. Read the code for those methods - what are the differences? Which one
 *    would you have written if we'd asked you to?
 * b. Compare the time required by the three methods for various values of n. 
 *    Are they always similar? Remember to make n large enough that 
 *    significant time is required.
 * c. The first method (using basic string concatenation) slows down a lot
 *    as n gets large. Why should that be? What does it say about using string
 *    concatenation in programs in general?
 *
 * @author Michael Albert
 */
public class Week2Experiment2 {

  static final Random R = new Random();

  public static void main(String[] args) {
    Timer timer = new Timer();
    double totalTime1 = 0;
    double totalTime2 = 0;
    double totalTime3 = 0;
    int runs = 100000;
    for(int n = 1; n <= runs; n+=1000){
      timer.start();
      randString1(n);
      double time1 = timer.stop();

      timer.start();
      randString2(n);
      double time2 = timer.stop();

      timer.start();
      randString3(n);
      double time3= timer.stop();

      System.out.println("randString1: "+ time1 + ", randString2: " + time2 
      + ", randString3: " + time3);
      totalTime1+=time1;
      totalTime2+=time2;
      totalTime3+=time3;


    }
    System.out.println("Average randString1: " + totalTime1/runs+ ", Average randString2: " + totalTime2/runs + ", Average randString3: "+ totalTime3/runs);
  }

  static String randString1(int n) {
    String result = "";
    for (int i = 0; i < n; i++) {
      result += randChar();
    }
    return result;
  }

  static String randString2(int n) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < n; i++) {
      result.append(randChar());
    }
    return result.toString();
  }
  
  static String randString3(int n) {
    char[] result = new char[n];
    for (int i = 0; i < n; i++) {
      result[i] = randChar();
    }
    return new String(result);
  }

  static char randChar() {
    return (char) ('a' + R.nextInt(26));
  }

}
