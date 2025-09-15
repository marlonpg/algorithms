package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeCamerasSolutionTest {

    @Test
    public void testSingleNode() {
        BinaryTreeCamerasSolution solution = new BinaryTreeCamerasSolution();
        TreeNode root = new TreeNode(0);
        assertEquals(1, solution.minCameraCover(root));
    }

    @Test
    public void testTwoNodes() {
        BinaryTreeCamerasSolution solution = new BinaryTreeCamerasSolution();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        assertEquals(1, solution.minCameraCover(root));
    }

    @Test
    public void testComplexTree() {
        BinaryTreeCamerasSolution solution = new BinaryTreeCamerasSolution();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.right = new TreeNode(0);
        assertEquals(2, solution.minCameraCover(root));
    }

    @Test
    public void testNullRoot() {
        BinaryTreeCamerasSolution solution = new BinaryTreeCamerasSolution();
        assertEquals(0, solution.minCameraCover(null));
    }

    @Test
    public void testScenario1() {
        // [0,0,null,0,0]
        BinaryTreeCamerasSolution solution = new BinaryTreeCamerasSolution();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        assertEquals(1, solution.minCameraCover(root));
    }

    @Test
    public void testScenario2() {
        // [0,0,null,0,null,0,null,null,0]
        BinaryTreeCamerasSolution solution = new BinaryTreeCamerasSolution();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(0);
        assertEquals(2, solution.minCameraCover(root));
    }
}