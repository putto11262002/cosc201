package cosc201.a2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Put Suthisrisinlpa 4893969
 */
public abstract class Warehouse {
    final  int DEFAULT_DIMENSION = 10;
    int rows;
    int cols;
    int deliveredPackets = 0;
    int totalPackets = 0;
    double maxDistance = 0;
    double totalTimeRequired = 0;
    Map<Location, PacketManager> packetManagers = new HashMap<>();
    DeliveryEfficiencyTracker deliveryEfficiencyTracker = new DeliveryEfficiencyTracker();

    public Warehouse(){
        this.cols = DEFAULT_DIMENSION;
        this.rows = DEFAULT_DIMENSION;
    }

    public Warehouse(int rows, int cols){
        this.cols = cols;
        this.rows = rows;
    }

    /**
     * Add packet manger to the warehouse
     */
    abstract void addPacketManagers();

    /**
     * Add packet to the warehouse
     * @param p the packet to be added to the warehouse
     */
    abstract void addPacket(Packet p);
    /**
     * Run a simulation on the warehouse
     */
    abstract void startSimulation();

    protected   boolean isValidLocation(Location l) {
        return 0 <= l.row && l.row < rows && 0 <= l.col && l.col < cols;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                s.append(packetManagers.get(new Location(r, c)).size());
                s.append(" |");
            }
            s.append("\n");
            for(int c = 0; c < cols; c++){
                s.append("--");
            }
        }
        return s.toString();
    }


}
