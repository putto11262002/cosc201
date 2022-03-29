package cosc201.week05;

import org.junit.Assert;
import org.junit.Test;

public class TableauCheckerTest {
    int[][] validTable = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
    int[][] invalidTable = {{6, 4, 5, 10, 11}, {1, 6, 8, 3, 5, 6}, {3, 2, 12}, {15}};
    int[][] emptyTable = {};
    @Test
    public void testRowLengthsDecreaseOnInvalidTable(){
        Assert.assertEquals(false, TableauChecker.rowLengthsDecrease(invalidTable));
    }
    @Test
    public void testRowLengthsDecreaseOnValidTable(){
        Assert.assertEquals(true, TableauChecker.rowLengthsDecrease(validTable));
    }

    @Test
    public void testRowLengthsDecreaseOnEmptyTable(){
        Assert.assertEquals(true, TableauChecker.rowLengthsDecrease(emptyTable));
    }

    @Test
    public void testRowValuesIncreaseOnValidTable(){
        Assert.assertEquals(true, TableauChecker.rowValuesIncrease((validTable)));
    }

    @Test
    public void testRowValuesIncreaseOnInvalidTable(){
        Assert.assertEquals(false, TableauChecker.rowValuesIncrease((invalidTable)));
    }

    @Test
    public void testColumnValuesIncreaseOnValidTable(){
        Assert.assertEquals(true, TableauChecker.columnValuesIncrease((validTable)));
    }

    @Test
    public void testColumnValuesIncreaseOnInvalidTable(){
        Assert.assertEquals(false, TableauChecker.columnValuesIncrease((invalidTable)));
    }

    @Test
    public void testIsSetOf1toNOnValidTable(){
        Assert.assertEquals(true, TableauChecker.isSetOf1toN((validTable)));
    }

    @Test
    public void testIsSetOf1toNOnInvalidTable(){
        Assert.assertEquals(false, TableauChecker.isSetOf1toN((invalidTable)));
    }

    @Test
    public void testIsSetOf1toNOnEmptyTable(){
        Assert.assertEquals(true, TableauChecker.isSetOf1toN((emptyTable)));
    }



}
