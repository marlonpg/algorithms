package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaximalSquareSolutionTest {

    @Test
    public void testSingleOne() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {{'1'}};
        assertEquals(1, solution.maximalSquare(matrix));
    }

    @Test
    public void testSingleZero() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {{'0'}};
        assertEquals(0, solution.maximalSquare(matrix));
    }

    @Test
    public void testTwoByTwoSquare() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {
            {'1', '1'},
            {'1', '1'}
        };
        assertEquals(4, solution.maximalSquare(matrix));
    }

    @Test
    public void testThreeByThreeSquare() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {
            {'1', '1', '1'},
            {'1', '1', '1'},
            {'1', '1', '1'}
        };
        assertEquals(9, solution.maximalSquare(matrix));
    }

    @Test
    public void testMixedMatrix() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        assertEquals(4, solution.maximalSquare(matrix));
    }

    @Test
    public void testAllZeros() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '0'}
        };
        assertEquals(0, solution.maximalSquare(matrix));
    }

    @Test
    public void testEmptyMatrix() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {};
        assertEquals(0, solution.maximalSquare(matrix));
    }

    @Test
    public void testNullMatrix() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        assertEquals(0, solution.maximalSquare(null));
    }

    @Test
    public void testSingleRow() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {{'1', '1', '0', '1'}};
        assertEquals(1, solution.maximalSquare(matrix));
    }

    @Test
    public void testSingleColumn() {
        MaximalSquareSolution solution = new MaximalSquareSolution();
        char[][] matrix = {
            {'1'},
            {'1'},
            {'0'},
            {'1'}
        };
        assertEquals(1, solution.maximalSquare(matrix));
    }
}