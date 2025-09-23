package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class New21GameSolutionV2Test {
    
    private final New21GameSolutionV2 solution = new New21GameSolutionV2();
    
    @Test
    void testEdgeCaseKZero() {
        assertEquals(1.0, solution.new21Game(21, 0, 10), 0.0001);
    }
    
    @Test
    void testEdgeCaseNGreaterThanKPlusMaxPts() {
        assertEquals(1.0, solution.new21Game(30, 17, 10), 0.0001);
    }
    
    @Test
    void testBasicCase() {
        assertEquals(0.6, solution.new21Game(6, 1, 10), 0.0001);
    }
    
    @Test
    void testAnotherCase() {
        assertEquals(0.73278, solution.new21Game(21, 17, 10), 0.0001);
    }
}