package cosc201.a2;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Map;

/**
 * @author Put Suthisrisinlpa 4893969
 */

public class LoadAwareManager implements PacketManager {
    Location home;
    Map<Location, PacketManager> wareHouse;
    ArrayDeque<Packet> queue = new ArrayDeque<>();

    public LoadAwareManager(Location home, Map<Location, PacketManager> wareHouse) {
        this.home = home;
        this.wareHouse = wareHouse;
    }

    @Override
    public int size() {
        return this.queue.size();
    }


    @Override
    public void receivePacket(Packet p) {
        if (p.destination.equals(this.home)) return;
        queue.add(p);
    }

    @Override
    public Collection<Packet> packetsHeld() {
        return queue;
    }

    @Override
    public Packet sendPacket() {
        if (this.isEmpty()) return null;
        Packet p = queue.remove();
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
        PacketManager adjacentColNeighbour = wareHouse.get(adjacentColLocation);
        PacketManager adjacentRowNeighbour = wareHouse.get(adjacentRowLocation);

        if (adjacentColNeighbour.size() > adjacentRowNeighbour.size()) p.current.row = adjacentRowLocation.row;
        else if (adjacentRowNeighbour.size() > adjacentColNeighbour.size()) p.current.col = adjacentColLocation.col;
        else p.current.row = adjacentRowLocation.row;
        return p;
    }
}
