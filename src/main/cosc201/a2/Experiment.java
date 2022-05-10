package cosc201.a2;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Put Suthisrisinlpa 4893969
 */
public class Experiment {
   static Random R = new Random();
   static final int REPs = 10;
    static Warehouse[] warehouses= new Warehouse[]{new PriorityWarehouse(), new LoadAwareWarehouse(), new BasicWarehouse(), new StackWarehouse()};
   static String[] managerNames= new String[]{"Priority manager", "Load aware manager", "Basic manager", "Stack manager"};

   static int[] sizes = new int[]{25, 50, 100};
   static StringBuilder s = new StringBuilder();
    public static void main(String[] args){
        ArrayList<Double> results;
     s.append("packet-manager, size, experiment1, experiment2, experiment3\n");
        for(int i = 0; i  < managerNames.length; i++){
            String managerName = managerNames[i];

            for(int size: sizes){
                s.append(managerName);
                s.append(", ");
                s.append(size);
                s.append(", ");
                // experiment 1
                results = new ArrayList<>();
                for(int r = 0; r < REPs; r++){

                    Warehouse warehouse = warehouses[i];

                    warehouse.rows = size;
                    warehouse.cols = size;
                    results.add(Experiment1(warehouse));
                }
                s.append(mean(results));
                s.append(", ");


                // experiment 2
                results = new ArrayList<>();
                for(int r = 0; r < REPs; r++){

                    Warehouse warehouse = warehouses[i];


                    warehouse.rows = size;
                    warehouse.cols = size;
                    results.add(Experiment2(warehouse));
                }
                s.append(mean(results));
                s.append(", ");


                // experiment 3
                results = new ArrayList<>();
                for(int r = 0; r < REPs; r++){
                    Warehouse warehouse = warehouses[i];

                    warehouse.rows = size;
                    warehouse.cols = size;
                    results.add(Experiment3(warehouse));
                }
                s.append(mean(results));
                s.append("\n");


            }
        }
        System.out.println(s.toString());
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


        for(int i = 0; i < warehouse.cols * warehouse.rows; i++){
            Packet p = new Packet();
            p.current = new Location(0, 0);
            p.destination = new Location(R.nextInt(warehouse.rows), R.nextInt(warehouse.rows));
            p.contents = "" + p.ID;
            warehouse.addPacket(p);

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
