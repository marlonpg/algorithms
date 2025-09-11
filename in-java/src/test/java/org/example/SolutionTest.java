package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final TrappingRainWater solution = new TrappingRainWater();

    @Test
    void testExampleCase() {
        int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};
        assertEquals(6, solution.trap(input));
    }

    @Test
    void testNoTrappingFlat() {
        int[] input = {1,1,1,1};
        assertEquals(0, solution.trap(input));
    }

    @Test
    void testNoTrappingMonotonicIncreasing() {
        int[] input = {1,2,3,4,5};
        assertEquals(0, solution.trap(input));
    }

    @Test
    void testNoTrappingMonotonicDecreasing() {
        int[] input = {5,4,3,2,1};
        assertEquals(0, solution.trap(input));
    }

    @Test
    void testSimpleValley() {
        int[] input = {2,0,2};
        assertEquals(2, solution.trap(input));
    }

    @Test
    void testSmallValley() {
        int[] input = {3,0,1,3};
        assertEquals(5, solution.trap(input));
    }

    @Test
    void testEmptyArray() {
        int[] input = {};
        assertEquals(0, solution.trap(input));
    }

    @Test
    void testSingleBar() {
        int[] input = {5};
        assertEquals(0, solution.trap(input));
    }

    @Test
    void testTwoBars() {
        int[] input = {5,1};
        assertEquals(0, solution.trap(input));
    }

    @Test
    void testLargeCase() {
        int[] input = {4,2,0,3,2,5};
        assertEquals(9, solution.trap(input));
    }
}
