package cosc201.a1;

import cosc201.*;


import java.util.ArrayList;
import java.util.Collections;

public class Experiment1 {
    public static void main(String[] args){
        final long SECOND = 1000000000;
        Timer timer = new Timer();
        ArrayList<UnionFind> ufs = new ArrayList<UnionFind>();
        ArrayList<ArrayList<Integer>> dimensions = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Long>> times = new ArrayList<ArrayList<Long>>();
        ufs.add(new UF1());
        ufs.add(new UF2());
        ufs.add(new UF3());
        ufs.add(new UF4());

        dimensions.add(new ArrayList<Integer>());
        dimensions.add(new ArrayList<Integer>());
        dimensions.add(new ArrayList<Integer>());
        dimensions.add(new ArrayList<Integer>());

        times.add(new ArrayList<Long>());
        times.add(new ArrayList<Long>());
        times.add(new ArrayList<Long>());
        times.add(new ArrayList<Long>());



        long time;

        int repLimit= 50;


        for(int i = 0; i < ufs.size(); i++){
            for(int  rep = 0; rep < repLimit; rep++){
                int dimension = 10;
                do{
                    Map map = new Map(dimension, dimension);
                    map.flood(0.50);
                    timer.start();
                    MapAnalyser analyser = new MapAnalyser(map, ufs.get(i));
                    analyser.countSeas();
                    time = timer.stop();
                    dimension+= dimension *  0.10;
                    System.out.println("Dimension: " + dimension + " Time: " + toSecond(time) + "sec");

                }while(time <= SECOND);
                dimensions.get(i).add(dimension);
                times.get(i).add(time);
            }



        }

        for(int i = 0; i < dimensions.size(); i++){
            System.out.println(dimensions.get(i).toString());
            System.out.println(times.get(i).toString());

                System.out.println("UF" + (i+1) + " Time: " + toSecond( getMean(times.get(i))) + " Dimension: " + getMedian(dimensions.get(i)));


        }





    }

    private static double toSecond(long nanaSecond){
        return nanaSecond / 1000000000.00;
    }

    private static long getMean(ArrayList<Long> list){
        long sum = 0;
        for(long nanoSecond: list){
            sum+= nanoSecond;
        }
        return (long) sum/list.size();
    }

    private static int getMedian(ArrayList<Integer> list){
        Collections.sort(list);
        return list.get(list.size() / 2);
    }
}
