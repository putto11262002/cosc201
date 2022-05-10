package cosc201.week06;

import java.util.Arrays;
import java.util.Random;


/**
 *  Skeleton code for COSC201 week 6 lab on shuffling
 *
 *  @author Michael Albert
 *
 */

public class Shuffler{

    // NOT_FOUND is used for the obvious purpose (and shouldn't occur)
    // HEADS and TAILS may be convenient.
    public static final int NOT_FOUND = -1;
    public static final boolean HEADS = true;
    public static final boolean TAILS = false;
    public static final double HALF = 0.50;


    public static final Random R = new Random();

    /**
     * Use this to set the seed of the random number generator. This can be
     * used if you want to ensure that your experiments are repeatable.
     *
     */
    public static void setSeed(long seed) {
        R.setSeed(seed);
    }

    /**
     * Use this to get a 'new' i.e., sorted deck.
     *
     */
    public static int[] newDeck(int n) {
        int[] deck = new int[n];
        for(int i = 0; i < n; i++) deck[i] = i;
        return deck;
    }

    public static int[] shuffle(int[] d) {
        // Split (actually or implicitly) d into two halves a and b
        int[] res = new int[d.length];
        int  halfD = d.length/2;
        // a <- d[0...halfD-1]
        // b <- d[halfD...d.length - 1]
        int ri = 0, ai = 0, bi = halfD;
        // While a and b both have elements remaining
        while(ai < halfD && bi < d.length){
            // Flip a coin (R.nextBoolean() is a good idea for this)
            // If it's heads take the next element from a, else b
            if( R.nextDouble() > HALF){
                res[ri++] = d[ai++];
            }else{
                res[ri++] = d[bi++];
            }


        }

        while (ai < halfD) res[ri++] = d[ai++];
        while (bi < d.length) res[ri++] = d[bi++];

        return res;



        // (For the above, the 'merge' methods in the lecture code should be
        // good guidance -- instead of order we're using randomness to decide
        // the next element of the 'pile').




    }



    public static int[] shuffle(int[] d, int k ) {
        for(int i = 0; i < k; i++){
            d = shuffle(d);
        }
        return d;
    }

    public static int getPosition(int[] d, int v) {
        for(int i = 0; i < d.length; i++) {
            if (d[i] == v) return i;
        }
        return NOT_FOUND;
    }

}