package cosc201.a2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Put Suthisrisinlpa 4893969
 */

public class PriorityManager implements PacketManager {
    private Location home;
    PriorityQueue<PriorityPacketWrapper> queue = new PriorityQueue<>();
    Map<Location, PacketManager> warehouse;
    public PriorityManager(Location home, Map<Location, PacketManager> warehouse){
        this.home = home;
        this.warehouse = warehouse;
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public void receivePacket(Packet p) {
        if(p.destination.equals(this.home)) return;
        queue.add(new PriorityPacketWrapper(p));
    }

    @Override
    public Collection<Packet> packetsHeld() {
        Collection<Packet> packets = new ArrayList<>();
        for(PriorityPacketWrapper pPW:queue){
            packets.add(pPW.toPacket());
        }
        return packets;
    }

    @Override
    public Packet sendPacket() {
        if (this.isEmpty()) return null;
        PriorityPacketWrapper pPW = queue.remove();
        Packet p = pPW.toPacket();
        // check if the destination is either a straight line left or right
        if (p.destination.row == this.home.row && p.destination.col != this.home.col) {
            if (this.home.col < p.destination.col) p.current.col += 1;
            if (this.home.col > p.destination.col) p.current.col -= 1;
            return p;
        }

        // check if the destination is either a straight line upwards or downwards

        if (p.destination.col == this.home.col && p.destination.row != this.home.row) {
            if (this.home.row > p.destination.row) p.current.row -= 1;
            if (this.home.row < p.destination.row) p.current.row += 1;
            return p;
        }

        // two reasonable paths
        Location adjacentColLocation = new Location(this.home.row,  this.home.col > p.destination.col ? this.home.col - 1 : this.home.col +1);
        Location adjacentRowLocation = new Location(this.home.row > p.destination.row ? this.home.row - 1 : this.home.row + 1, this.home.col);
        PacketManager adjacentColNeighbour = warehouse.get(adjacentColLocation);
        PacketManager adjacentRowNeighbour = warehouse.get(adjacentRowLocation);

        if (adjacentColNeighbour.size() > adjacentRowNeighbour.size()) p.current.row = adjacentRowLocation.row;
        else if (adjacentRowNeighbour.size() > adjacentColNeighbour.size()) p.current.col = adjacentColLocation.col;
        else p.current.row = adjacentRowLocation.row;
        return p;
    }

     class PriorityPacketWrapper implements Comparable<PriorityPacketWrapper>{
        private Packet p;
        private PriorityPacketWrapper(Packet p){
            this.p = p;
        }

        @Override
        public int compareTo(PriorityPacketWrapper otherPPW){
            int selfDis = getTotalDistanceToDestination(this.p);
            int otherDis = getTotalDistanceToDestination(otherPPW.p);
            if(selfDis > otherDis) return -1;
            if(selfDis < otherDis) return 1;
            return 0;
        }
        private int getTotalDistanceToDestination(Packet p){
            int rowDiff = Math.abs(p.current.row - p.destination.row);
            int colDiff = Math.abs(p.current.col - p.destination.col);
            return rowDiff + colDiff;
        }

        public Packet toPacket(){
            return this.p;
        }
    }
}
