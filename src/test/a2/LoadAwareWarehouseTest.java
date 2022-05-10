//package a2;
//
//import cosc201.a2.LoadAwareWarehouse;
//import cosc201.a2.Location;
//import org.junit.jupiter.api.Test;
//
//import java.util.Random;
//
//public class LoadAwareWarehouseTest {
//    @Test
//    public void LoadAwareWarehouseSimulation1(){
//        LoadAwareWarehouse warehouse = new LoadAwareWarehouse(100, 100);
//        warehouse.addRobots();
//        Random R  = new Random();
//
//        for(int i = 0; i < 100; i++){
//            warehouse.addPacket(new Location(0, 0), new Location(R.nextInt(100), R.nextInt(100)));
//        }
//
//
//        warehouse.startSimulation();
//    }
//
//    @Test
//    public void LoadAwareWarehouseSimulation2(){
//        LoadAwareWarehouse warehouse = new LoadAwareWarehouse(20, 20);
//        warehouse.addRobots();
//        Random R  = new Random();
//
//        for(int r = 0; r < 20 ; r++){
//            for(int c = 0; c < 20; c++){
//                warehouse.addPacket(new Location(r, c), new Location(R.nextInt(20), R.nextInt(20)));
//            }
//        }
//
//
//        warehouse.startSimulation();
//    }
//}
