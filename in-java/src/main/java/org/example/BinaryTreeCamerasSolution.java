package org.example;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class BinaryTreeCamerasSolution {

    private int totalCameras = 0;

    // Define states
    private final int NEEDS_COVERAGE = 0;
    private final int HAS_CAMERA = 1;
    private final int IS_COVERED = 2; //This node Is Covered by a child's camera

    public int minCameraCover(TreeNode root) {
        this.totalCameras = 0; // Reset counter for each call
        // If the root itself needs coverage after the traversal, it has no parent
        // to help, so we must add one more camera on the root
        if (solve(root) == NEEDS_COVERAGE) {
            this.totalCameras++;
        }
        return this.totalCameras;
    }

     // Performs a post-order traversal. Returns the state of the current 'node'
    private int solve(TreeNode node) {
        // Base case: A null node doesn't need a camera and is considered "covered"
        if (node == null) {
            return IS_COVERED;
        }

        // Recursively find the state of the children first (post-order)
        int leftState = solve(node.left);
        int rightState = solve(node.right);

        // If one or both child needs coverage, this node MUST place a camera
        if (leftState == NEEDS_COVERAGE || rightState == NEEDS_COVERAGE) {
            this.totalCameras++;
            return HAS_CAMERA;
        }

        // If one or both child has a camera, this node is now covered
        if (leftState == HAS_CAMERA || rightState == HAS_CAMERA) {
            return IS_COVERED;
        }

        // If both children are covered and don't have cameras, this node
        // is not yet covered. It must ask its parent for coverage
        return NEEDS_COVERAGE;
    }
}
