package cosc201.a2;


import cosc201.week02.Timer;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Put Suthisrisinlpa 4893969
 */
public class TestEx {
    static Random R = new Random();
    static final int REPs = 50;
    static Warehouse[] warehouses= new Warehouse[]{new PriorityWarehouse(), new LoadAwareWarehouse(), new BasicWarehouse(), new StackWarehouse()};
    static String[] managerNames= new String[]{"Priority manager", "Load aware manager", "Basic manager", "Stack manager"};

    static int[] sizes = new int[]{10, 20, 40};
    static StringBuilder s = new StringBuilder();
    public static void main(String[] args){
      System.out.println(Experiment1(new LoadAwareWarehouse(100,100)));

    }

    private static double mean(ArrayList<Double> list){
        double sum = 0;
        for(Double item: list){
            sum+=item;
        }
        return sum / list.size();
    }





    /**
     * The number of packets and cells is the same. All the packets start in the upper left
     * corner, and each packet has a different destination.
     * @param warehouse the warehouse class that the experiment is going to be conduct on
     */
    private static double Experiment1(Warehouse warehouse){

        warehouse.addPacketManagers();
        Packet maxP = new Packet();
        maxP.current = new Location(0, 0);
        maxP.destination = new Location(0, 0);

        for(int i = 0; i < warehouse.cols * warehouse.rows; i++){
            Packet p = new Packet();
            p.current = new Location(0, 0);
            p.destination = new Location(R.nextInt(warehouse.rows), R.nextInt(warehouse.rows));
            p.contents = "" + p.ID;
            warehouse.addPacket(p);
            if(Math.abs(p.current.row - p.destination.row) + Math.abs(p.current.col - p.destination.col) > Math.abs(maxP.current.row - maxP.destination.row) + Math.abs(maxP.current.col - maxP.destination.col)){
                maxP = new Packet();
                maxP.current = new Location(p.current.row, p.current.col);
                maxP.destination = new Location(p.destination.row, p.destination.col);
                maxP.contents ="" +  p.ID;
            }
        }
        warehouse.startSimulation();
        return warehouse.deliveryEfficiencyTracker.getMedianEfficiency();

    }

    private static double Experiment2(Warehouse warehouse){
        warehouse.addPacketManagers();

        for(int r = 0; r < warehouse.rows; r++){
            for(int c = 0; c < warehouse.cols; c++){
                Packet p = new Packet();
                p.current = new Location(r, c);
                p.destination = new Location(R.nextInt(warehouse.rows), R.nextInt(warehouse.rows));
                p.contents = "" + p.ID;
                warehouse.addPacket(p);

            }
        }
        warehouse.startSimulation();
        return warehouse.deliveryEfficiencyTracker.getMedianEfficiency();

    }

    private static double Experiment3(Warehouse warehouse){
        warehouse.addPacketManagers();

        for(int r = 0; r < warehouse.rows; r++){
            for(int c = 0; c < warehouse.cols; c++){
                if(R.nextInt(4) != 0) continue;
                Packet p = new Packet();
                p.current = new Location(r, c);
                p.destination = new Location(R.nextInt(warehouse.rows), R.nextInt(warehouse.rows));
                p.contents = "" + p.ID;
                warehouse.addPacket(p);


            }
        }
        warehouse.startSimulation();
        return warehouse.deliveryEfficiencyTracker.getMedianEfficiency();

    }


}
