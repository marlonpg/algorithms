package org.example;

public class MaximalSquareSolution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dp = new int[cols + 1];
        int maxSide = 0, prev = 0;

        for (int i = 1; i <= rows; i++) {
            prev = 0;
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
                    maxSide = Math.max(maxSide, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }

        return maxSide * maxSide;
    }
}
