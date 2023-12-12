package Tests;

import Algorithm.ActivitySelection;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCases {

    @Test
    public void test1() {

        int[][] activities = new int[][] {
                {1, 2, 1},
                {2, 3, 2},
                {3, 4, 5}
        };

        assertEquals(8, ActivitySelection.getMaxWeight(activities));

    }

    @Test
    public void test2() {

        int[][] activities = new int[][] {
                {1, 3, 2},
                {2, 5, 4},
                {4, 6, 3}
        };

        assertEquals(5, ActivitySelection.getMaxWeight(activities));

    }

    @Test
    public void test3() {

        int[][] activities = new int[][] {
                {1, 3, 30},
                {1, 4, 40},
                {1, 5, 50},
                {1, 6, 60},
                {1, 7, 70},
                {1, 5, 30}
        };

        assertEquals(70, ActivitySelection.getMaxWeight(activities));

    }

    @Test
    public void test4() {

        int[][] activities = new int[][] {
                {1, 3, 10},
                {1, 3, 10},
                {1, 3, 10}
        };

        assertEquals(10, ActivitySelection.getMaxWeight(activities));

    }

    @Test
    public void test5() {

        int[][] activities = new int[][] {
                {1, 3, 3},
                {2, 4, 1},
                {3, 6, 5},
                {3, 5, 2}
        };

        assertEquals(8, ActivitySelection.getMaxWeight(activities));

    }
}
