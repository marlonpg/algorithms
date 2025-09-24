package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SpiralMatrixTest {
    
    private final SpiralMatrix solution = new SpiralMatrix();
    
    @Test
    void testBasicCase() {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> expected = List.of(1,2,3,6,9,8,7,4,5);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    void testRectangularMatrix() {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> expected = List.of(1,2,3,4,8,12,11,10,9,5,6,7);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    void testSingleRow() {
        int[][] matrix = {{1,2,3,4}};
        List<Integer> expected = List.of(1,2,3,4);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    void testSingleColumn() {
        int[][] matrix = {{1},{2},{3}};
        List<Integer> expected = List.of(1,2,3);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    void testEmptyMatrix() {
        int[][] matrix = {};
        List<Integer> expected = List.of();
        assertEquals(expected, solution.spiralOrder(matrix));
    }
}