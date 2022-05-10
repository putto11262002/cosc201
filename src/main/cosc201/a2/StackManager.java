package cosc201.a2;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Map;

/**
 * A packet manager that maintains packet in a first in first out order, stack.
 * This means that the last packet that the manager received will be the first
 * one to be sent. If the destination fo the packet that is to be sent is
 * a straight line from the current location, it just sends it one step in
 * the appropriate direction. If it has two reasonable choices, it will
 * send the packet to the packet manager that held the least packets. If two
 * are equal change in column is preferred
 * @author Put Suthisrisinlpa 4893969
 */
public class StackManager implements  PacketManager {
    private Location home;
    private ArrayDeque<Packet> stack = new ArrayDeque<>();
    Map<Location, PacketManager> warehouse;

    /**
     *
     * @param home the home location of the packet manager
     * @param warehouse the warehouse that the packet manager is in
     */
    public StackManager(Location home, Map<Location, PacketManager> warehouse){
        this.home = home;
        this.warehouse = warehouse;
    }

    /**
     * @return the number of packets in the packet manager's queue
     */
    @Override
    public int size() {
        return this.stack.size();
    }

    /**
     *
     * @param p the packet received
     */
    @Override
    public void receivePacket(Packet p) {
        if(p.destination.equals(this.home)) return;
        stack.push(p);
    }

    /**
     *
     * @return all the packets in the packet manager's queue
     */
    @Override
    public Collection<Packet> packetsHeld() {
        return stack;
    }

    /**
     *
     * @return the packet that was sent. if no more packet is to be sent null is return
     */
    @Override
    public Packet sendPacket() {
        if(this.isEmpty()) return null;
        Packet p = stack.pop();
        if(p.destination.row == this.home.row && p.destination.col != this.home.col){
            if(this.home.col < p.destination.col) p.current.col+=1;
            if(this.home.col > p.destination.col) p.current.col-=1;
            return p;
        }

        if(p.destination.col == this.home.col && p.destination.row != this.home.row){
            if(this.home.row > p.destination.row) p.current.row-=1;
            if(this.home.row < p.destination.row) p.current.row+=1;
            return p;
        }

        Location adjacentColLocation = new Location(this.home.row,  this.home.col > p.destination.col ? this.home.col - 1 : this.home.col +1);
        Location adjacentRowLocation = new Location(this.home.row > p.destination.row ? this.home.row - 1 : this.home.row + 1, this.home.col);
        PacketManager adjacentColNeighbour = warehouse.get(adjacentColLocation);
        PacketManager adjacentRowNeighbour = warehouse.get(adjacentRowLocation);
        if (adjacentColNeighbour.size() > adjacentRowNeighbour.size()) p.current.row = adjacentRowLocation.row;
        else if (adjacentRowNeighbour.size() > adjacentColNeighbour.size()) p.current.col = adjacentColLocation.col;
        else p.current.row = adjacentRowLocation.row;
        return p;

    }
}
