package cosc201.a2;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * @author Put Suthisrisinlpa 4893969
 */
public class BasicManager implements PacketManager {

    private Location home;
    private ArrayDeque<Packet> queue = new ArrayDeque<>();

    BasicManager(Location home) {
        this.home = home;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public Collection<Packet> packetsHeld() {
        return queue;
    }


    @Override
    public Packet sendPacket() {
        Packet p = null;
        // find the packet that does not belong to this location
        while (!this.isEmpty()) {
            p = queue.remove();
            if (!this.home.equals(p.destination)) break;
            // found packet that need to be moved
        }

        if (p == null || this.home.equals(p.destination)) return null;

        // check if the packet could be moved down any further
        if (p.current.row < p.destination.row) p.current.row += 1;

            // check if could move up any further
        else if (p.current.row > p.destination.row) p.current.row -= 1;

            // check if could move any further to the right
        else if (p.current.col < p.destination.col) p.current.col += 1;

            // check if can move to the left
        else if (p.current.col > p.destination.col) p.current.col -= 1;

        return p;
    }

    @Override
    public void receivePacket(Packet p) {
        queue.add(p);
    }

}