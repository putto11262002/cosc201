package a2;

import cosc201.a2.RouteCounter;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class RouteCounterTest {

    @Test
    public void testRouteCounterOn3By3Grid(){
        boolean[][] damages = new boolean[][]{
                {false, false, true},
                {true, false, false},
                {false, false, false}
        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.countRoutes(), 2);
    }
    @Test
    public void testRouteCounterOn5By5Grid(){
        boolean[][] damages = new boolean[][]{{false, false, false, false, true},
                {true, false, false, true, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false}
        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.countRoutes(), 22);
    }

    @Test
    public  void testRouteCounterOn4by7Grid(){
        boolean[][] damages = new boolean[][]{{false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false},
                {true, false, false, false, false, true, false},
                {false, false, false, false, false, false, false},

        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.countRoutes(), 16);

    }

    @Test
    public  void testRouteCounterOn4b4GridNoBlocks(){
        boolean[][] damages = new boolean[][]{{false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},

        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.countRoutes(), 20);

    }
    @Test
    public void testBigRouteCounterOn3By3Grid(){
        boolean[][] damages = new boolean[][]{
                {false, false, true},
                {true, false, false},
                {false, false, false}
        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.bigCountRoutes(), BigInteger.TWO);
    }

    @Test
    public void testBigRouteCounterOn5By5Grid(){
        boolean[][] damages = new boolean[][]{{false, false, false, false, true},
                {true, false, false, true, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false}
        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.bigCountRoutes(), BigInteger.valueOf(22));
    }

    @Test
    public  void testBigRouteCounterOn4by7Grid(){
        boolean[][] damages = new boolean[][]{{false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false},
                {true, false, false, false, false, true, false},
                {false, false, false, false, false, false, false},

        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.bigCountRoutes(), BigInteger.valueOf(16));

    }


    @Test
    public  void testBigRouteCounterOn4b4GridNoBlocks(){
        boolean[][] damages = new boolean[][]{{false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},

        };
        RouteCounter counter = new RouteCounter(damages);
        Assertions.assertEquals(counter.bigCountRoutes(), BigInteger.valueOf(20));

    }

}
