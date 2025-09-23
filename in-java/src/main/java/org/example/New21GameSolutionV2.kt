package org.example;

class New21GameSolutionV2 {
    
    // n: maximum points
    // k: points threshold to stop drawing
    // maxPts: maximum points that can be drawn in one turn
    fun new21Game(n: Int, k: Int, maxPts: Int): Double {
        // edge cases
        if (k == 0 || n >= k + maxPts - 1) {
            return 1.0
        }
        
        // dp[i] = probability of winning starting with i points
        val dp = DoubleArray(k + maxPts)
        var sum = 0.0
        
        // base cases scores from k to n are winning (if <= n)
        for (i in k until minOf(n + 1, k + maxPts)) {
            dp[i] = 1.0
            sum += dp[i]
        }
        
        // fill dp array backwards from k-1 to 0
        for (i in k - 1 downTo 0) {
            dp[i] = sum / maxPts
            sum += dp[i] - dp[i + maxPts]
        }
        
        return dp[0]
    }
}