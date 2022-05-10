package a1;

import cosc201.a1.Map;
import cosc201.a1.MapAnalyser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MapAnalyserTest {
    final static long SEED = 1234;
    static Map  map02, map03, map04, map01, map05;


    @BeforeAll
    public static void setUpMaps(){
         map01 = new Map(10, 10, SEED);
         map02= new Map(5, 5, SEED);
        map03 = new Map(5, 5, SEED);
        map04 = new Map(7, 7, SEED);
        map05 = new Map(0, 0, SEED);


       map02.flood(0.02);
       map03.flood(0.5);
       map04.flood(0.6);
        map01.flood(0.3);
        map05.flood(0.5);

        System.out.println("map01 has 3 seas");
        System.out.println(map01.toString());

        System.out.println("map02 has 4 seas");
        System.out.println(map02.toString());

        System.out.println("map03 has 3 seas");
        System.out.println(map03.toString());

        System.out.println("map04 has 3 seas");
        System.out.println(map04.toString());

        System.out.println("map05 has 0 seas");


    }

    @Test
    public  void testCountSeasOnMap01(){
        MapAnalyser analyser = new MapAnalyser(map01);
        Assertions.assertEquals(analyser.countSeas(), 3);

    }

    @Test
    public  void testCountSeasOnMap02(){
        MapAnalyser analyser = new MapAnalyser(map02);
        Assertions.assertEquals(analyser.countSeas(), 4);

    }
    @Test
    public void testCountSeasOnMap03(){
        MapAnalyser analyser = new MapAnalyser(map03);
        Assertions.assertEquals(analyser.countSeas(), 3);

    }

    @Test
    public void testCountSeasOnMap04(){
        MapAnalyser analyser = new MapAnalyser(map04);
        Assertions.assertEquals(analyser.countSeas(), 3);

    }

    @Test
    public void testCountSeasOnMap05(){
        MapAnalyser analyser = new MapAnalyser(map05);
        Assertions.assertEquals(analyser.countSeas(), 0);

    }

    @Test
    public void testSeaSizeOnSeaSize7(){
        MapAnalyser analyser = new MapAnalyser(map01);
        Assertions.assertEquals(analyser.seaSize(1, 2), 7);

    }

    @Test
    public void testSeaSizeOnSeaSize1(){
        MapAnalyser analyser = new MapAnalyser(map01);
        Assertions.assertEquals(analyser.seaSize(5, 0), 1);

    }

    @Test
    public void testSeaSizeOnSeaSize11(){
        MapAnalyser analyser = new MapAnalyser(map04);
        Assertions.assertEquals(analyser.seaSize(6, 0), 20);

    }

    @Test
    public void testSeaSizeOnSeaSize3(){
        MapAnalyser analyser = new MapAnalyser(map04);
        Assertions.assertEquals(analyser.seaSize(0, 0), 3);

    }


    @Test
    public void testSeaSizeOnLand(){
        MapAnalyser analyser = new MapAnalyser(map01);
        Assertions.assertEquals(analyser.seaSize(0, 0), 0);

    }








}
