package cosc201.a2;

import java.util.ArrayDeque;

/**
 * @author Put Suthisrisinlpa 4893969
 */

public class LoadAwareWarehouse extends Warehouse {

    public LoadAwareWarehouse(){super();}
 public LoadAwareWarehouse(int row, int col){
     super(row, col);
 }


    public void addPacketManagers(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                packetManagers.put(new Location(r, c), new LoadAwareManager(new Location(r, c), packetManagers));
            }
        }
    }


    public void addPacket(Packet p){
        if(!isValidLocation(p.current)) return;
        if(!isValidLocation(p.destination)) return;
        if(p.destination.equals(p.current)) return;
        packetManagers.get(p.current).receivePacket(p);
        deliveryEfficiencyTracker.trackPacket(p);
        totalPackets++;
    }

    public void startSimulation(){
         while(deliveredPackets < totalPackets){
             ArrayDeque<Packet> deliveryQueue = new ArrayDeque<>();

             for(int r = 0; r < rows; r++){
                 for(int c = 0; c < cols; c++){


                     for(Packet p: packetManagers.get(new Location(r, c)).packetsHeld()){
                         deliveryEfficiencyTracker.incrementTime(p);
                     }

                     for(Packet p: deliveryQueue){
                         if(p.current.equals(new Location(r, c))){
                             deliveryEfficiencyTracker.incrementTime(p);
                         }
                     }


                     Packet movedPacket = packetManagers.get(new Location(r, c)).sendPacket();
                     if(movedPacket != null){

                         if(movedPacket.current.equals(movedPacket.destination)){
                             deliveredPackets++;
                             System.out.println("Packet " + movedPacket.ID + " has been delivered to " + movedPacket.current);
                         }else{
                             System.out.println("Packet " + movedPacket.ID + " is moving to " + movedPacket.current);
                             deliveryQueue.add(movedPacket);
                         }
                     }
                 }
             }

             for(Packet p : deliveryQueue) {
                 packetManagers.get(p.current).receivePacket(p);
             }

         }

    }






}
