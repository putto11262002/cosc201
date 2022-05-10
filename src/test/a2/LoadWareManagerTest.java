package a2;

import cosc201.a2.LoadAwareWarehouse;
import cosc201.a2.Location;
import cosc201.a2.Packet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoadWareManagerTest {
//    @Test
//    public  void loadAwareManagerTestCase1(){
//        LoadAwareWarehouse warehouse = new LoadAwareWarehouse(5, 5);
//        warehouse.addRobots();
//        // adding packet to 0, 0
//        warehouse.addPacket(new Location(0, 0), new Location(3, 3));
//        // add two packets to 0, 1
//        warehouse.addPacket(new Location(0, 1), new Location(3, 3) );
//        warehouse.addPacket(new Location(0, 1), new Location(3, 3) );
//        // add one packet to  1, 0
//        warehouse.addPacket(new Location(1, 0), new Location(3, 3));
//        // sending packet from 0, 0
//        Packet p = warehouse.packetManagers.get(new Location(0, 0)).sendPacket();
//        Assertions.assertEquals(p.current, new Location(1, 0));
//
//    }
//
//    @Test
//    public  void loadAwareManagerTestCase2(){
//        LoadAwareWarehouse warehouse = new LoadAwareWarehouse(5, 5);
//        warehouse.addRobots();
//        // adding packet to 0, 0
//        warehouse.addPacket(new Location(0, 0), new Location(3, 3));
//        // add one packet to 0, 1
//        warehouse.addPacket(new Location(0, 1), new Location(3, 3) );
//        // add one packet to  1, 0
//        warehouse.addPacket(new Location(1, 0), new Location(3, 3));
//        // sending packet from 0, 0
//        Packet p = warehouse.packetManagers.get(new Location(0, 0)).sendPacket();
//        Assertions.assertEquals(p.current, new Location(1, 0));
//
//    }
//
//    @Test
//    public  void loadAwareManagerTestCase3(){
//        LoadAwareWarehouse warehouse = new LoadAwareWarehouse(5, 5);
//        warehouse.addRobots();
//        // adding packet to 2, 2
//        warehouse.addPacket(new Location(2, 2), new Location(4, 4));
//        // add one packet to 2, 3
//        warehouse.addPacket(new Location(2, 3), new Location(3, 3) );
//        // add two packets to  3, 2
//        warehouse.addPacket(new Location(3, 2), new Location(3, 3));
//        warehouse.addPacket(new Location(3, 2), new Location(3, 3));
//        // sending packet from 2, 2
//        Packet p = warehouse.packetManagers.get(new Location(2, 2)).sendPacket();
//        Assertions.assertEquals(p.current, new Location(2, 3));
//
//    }
//
//    @Test
//    public  void loadAwareManagerTestCase4(){
//        LoadAwareWarehouse warehouse = new LoadAwareWarehouse(5, 5);
//        warehouse.addRobots();
//        // adding packet to 2, 2
//        warehouse.addPacket(new Location(2, 2), new Location(4, 4));
//        // add one packet to 2, 3
//        warehouse.addPacket(new Location(2, 3), new Location(3, 3) );
//        // add two packets to  3, 2
//        warehouse.addPacket(new Location(3, 2), new Location(3, 3));
//        warehouse.addPacket(new Location(3, 2), new Location(3, 3));
//        // sending packet from 2, 2
//        Packet p = warehouse.packetManagers.get(new Location(2, 2)).sendPacket();
//        Assertions.assertEquals(p.current, new Location(2, 3));
//
//    }
//
//    @Test
//    public  void loadAwareManagerTestCase5(){
//        LoadAwareWarehouse warehouse = new LoadAwareWarehouse(5, 5);
//        warehouse.addRobots();
//        // adding packet to 2, 2
//        warehouse.addPacket(new Location(2, 2), new Location(4, 4));
//        // add one packet to 2, 3
//        warehouse.addPacket(new Location(2, 3), new Location(3, 3) );
//        // add two packets to  3, 2
//        warehouse.addPacket(new Location(3, 2), new Location(3, 3));
//        warehouse.addPacket(new Location(3, 2), new Location(3, 3));
//
//
//
//
//
//        Assertions.assertEquals(warehouse.packetManagers.get(new Location(2, 2)).sendPacket().current, new Location(2, 3));
//        Assertions.assertEquals(warehouse.packetManagers.get(new Location(2, 3)).sendPacket().current, new Location(3, 3));
//
//    }


}
