package cosc201.week05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Skeleton code for checking whether arrays represent Young tableaux.
 *
 * @author Michael Albert
 */
public class TableauChecker {

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        return rowLengthsDecrease(t) && rowValuesIncrease(t) && columnValuesIncrease(t) && isSetOf1toN(t);
    }

    /**
     *  Returns a string representation of an array based tableau.
     *
     * @param t a two-dimensional array which represents a tableau.
     *
     * @return a string representation of an array based tableau.
     */
    public static String toString(int[][] t) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                result.append(String.format("%-4s", t[i][j]));
            }
            if (i < t.length-1) {
                result.append("\n");
            }
        }
        return result.toString();
    }


    public static boolean rowLengthsDecrease(int[][] t) {
          for(int i = 1; i < t.length; i++){
              if(t[i].length > t[i - 1].length) return false;
          }
          return true;
    }

    public static boolean rowValuesIncrease(int[][] t) {
        for(int i = 1; i < t.length; i++){
            for(int j = 1; j < t[i].length; j++){
                if(t[i][j - 1] > t[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean columnValuesIncrease(int[][] t) {
        for(int i = 1; i < t.length; i++){
            for(int j = 1; j < t[i].length; j++){
               if(t[i - 1][j] > t[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean isSetOf1toN(int[][] t) {
        Set<Integer> exploredInt = new HashSet<Integer>();
        // get set size
        int n = 0;
        for(int i = 0; i < t.length; i++){
            n += t[i].length;
        }

        for(int i = 0; i < t.length; i++){
            for(int j = 0; j < t[i].length; j++){
                int targetInt = t[i][j];
                if(targetInt < 1 || targetInt > n || exploredInt.contains(targetInt)) return false;
                exploredInt.add(targetInt);
            }
        }



        return true;
    }


}