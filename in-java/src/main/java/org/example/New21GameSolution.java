package org.example;

public class New21GameSolution {
    
    // n: maximum points
    // k: points threshold to stop drawing
    // maxPts: maximum points that can be drawn in one turn
    public double new21Game(int n, int k, int maxPts) {
        //edge cases
        if (k == 0 || n >= k + maxPts - 1){
            return 1.0;
        }
        
        // dp[i] = probability of winning starting with i points
        double[] dp = new double[k + maxPts];
        double sum = 0.0;
        
        // base cases scores from k to n are winning (if <= n)
        for (int i = k; i <= n && i < k + maxPts; i++) {
            dp[i] = 1.0;
            sum += dp[i];
        }
        
        // fill dp array backwards from k-1 to 0
        for (int i = k - 1; i >= 0; i--) {
            dp[i] = sum / maxPts;
            sum += dp[i] - dp[i + maxPts];
        }
        
        return dp[0];
    }
}