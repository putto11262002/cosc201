package cosc201.a1;

import cosc201.Timer;
import cosc201.UnionFind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class UFEMapExperiment {
    private ArrayList<Long> times;
    private int repetitions;
    private long timeLimit;
    private double water_prob;
    private UnionFind uf;
    private ArrayList<Integer> dimensions = new ArrayList<Integer>();





    /**
     *
     * @param repetitions
     * @param timeLimit
     */
    public UFEMapExperiment(int repetitions, long timeLimit, UnionFind uf) {
        this.repetitions = repetitions;
        this.timeLimit = timeLimit;
        this.uf = uf;
    }

    public void run(){
        Timer timer = new Timer();
        Map map;
        MapAnalyser analyser;
        long time;


       for(int r = 0; r < repetitions; r++){
           int dimension = 0;

           do {
               map = new Map(dimension, dimension);
               map.flood(water_prob);

               timer.start();
               analyser = new MapAnalyser(map, uf);
               analyser.countSeas();
               time = timer.stop();
               dimension++;
           }while (time  <= timeLimit);
           dimensions.add(dimension);





       }




    }

    public int getDimensionMedian(){
        Collections.sort(dimensions);
        int media;

        int length = dimensions.size();
        if(length == 1){
            return dimensions.get(0);
        }
        if(length % 2 == 0){
            int sumOfMiddleDimensions = dimensions.get(length / 2) + dimensions.get((length / 2) - 1);
            media = sumOfMiddleDimensions / 2;
        }else {
            media = dimensions.get(length / 2);
        }

        return media;
    }




}
