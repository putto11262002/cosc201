package cosc201.a1;

import java.util.ArrayList;

public class Experiment2 {

    public static void main(String[]args){
        ArrayList<Integer> dimensions = new ArrayList<Integer>();
        dimensions.add(10);
        dimensions.add(100);
        dimensions.add(1000);
        ArrayList<Double> water_probs = new ArrayList<Double>();
        int repLimit= 100;
        for(int dimension: dimensions){


                Map map;
                MapAnalyser analyser;
                double water_prob = 1;
                int seaNum = 0;
                while(true){
                    ArrayList<Integer> seaNums= new ArrayList<Integer>();
                    for(int i = 0; i < repLimit; i++){
                        map = new Map(dimension,dimension);
                        map.flood(water_prob);
                        analyser = new MapAnalyser(map);
                        seaNums.add(analyser.countSeas());
                    }

                    if(getMean(seaNums) >= 2){
                        System.out.println(water_prob);
                        break;
                    }
                    water_prob = water_prob - 0.01;
                }





        }

        System.out.println(water_probs.toString());

    }

    private static int getMean(ArrayList<Integer> list){
        int sum = 0;
        for(int item: list){
            sum+=item;
        }
        return sum/list.size();
    }


}
