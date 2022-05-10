package cosc201.a2;

import java.util.ArrayDeque;

/**
 * @author Put Suthisrisinlpa 4893969
 */
public class BasicWarehouse extends  Warehouse{

    public BasicWarehouse(){super();}
    public BasicWarehouse(int row, int col){
        super(row, col);
    }

    public void addPacketManagers(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                packetManagers.put(new Location(r, c), new PriorityManager(new Location(r, c), packetManagers));
            }
        }
    }
    public void addPacket(Packet p ){
        if(!isValidLocation(p.destination)) return;
        if(!isValidLocation(p.current)) return;
        if(p.current.equals(p.destination)) deliveredPackets++;
        packetManagers.get(p.current).receivePacket(p);
        deliveryEfficiencyTracker.trackPacket(p);
        totalPackets++;
    }

    public void startSimulation(){
        while (deliveredPackets < totalPackets){
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
                    Packet p = packetManagers.get(new Location(r, c)).sendPacket();
                    if(p != null){
                        if(p.current.equals(p.destination)){
                            deliveredPackets++;
                            System.out.println("Packet " + p.ID + " has been delivered to " + p.current);
                        }else{
                            deliveryQueue.add(p);
                            System.out.println("Packet " + p.ID + " has been moved to " + p.current) ;
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
