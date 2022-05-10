package cosc201.a2;

import java.util.*;

/**
 * @author Put Suthisrisinlpa 4893969
 */

class DeliveryEfficiencyTracker {

    Map<Packet, Delivery> map = new HashMap<>();

    public DeliveryEfficiencyTracker(){
    }

    public void trackPacket(Packet p){
        map.put(p, new Delivery(p.current, p.destination));
    }


    public void incrementTime(Location location){
        for(Packet p : map.keySet()){
            if(!p.current.equals(location)) continue;
            if(p.current.equals(p.destination)) continue;

            map.get(p).incrementTime();
        }
    }

    public void incrementTime(Packet p){
        map.get(p).incrementTime();
    }

    public String getEfficiencySummary(){
        StringBuilder s = new StringBuilder();
        for(Packet p: map.keySet()){
            s.append("Packet " +  p.ID + " " + map.get(p).getEfficiency() + "%\n");

        }

        return s.toString();

    }

    public double getMeanEfficiency(){
        double sum = 0;
        for(Delivery delivery : map.values()){
            sum+=delivery.getEfficiency();
        }
        return sum / map.size();
    }

    public double getMedianEfficiency(){
        double median;
        ArrayList<Double> list = new ArrayList<>();
        for(Delivery delivery: map.values()){
            list.add(delivery.getEfficiency());
        }
        Collections.sort(list);

        if (list.size() % 2 == 0) {
            double sumOfMiddleElements = list.get(list.size()/2) + list.get(list.size()/2 - 1);
            median = sumOfMiddleElements / 2;
        } else {

            median = list.get(list.size()/2);
        }

        return median;
    }


    class Delivery {
        double expectedTime;
        double actualTime;
        public Delivery(Location start, Location destination){
            this.expectedTime = Math.abs(start.row - destination.row) + Math.abs(start.col - destination.col);

        }

        public double getEfficiency(){
            if(actualTime == 0) return 0;
            return expectedTime/actualTime * 100;

        }

        public void incrementTime(){

            actualTime++;

        }

    }
}