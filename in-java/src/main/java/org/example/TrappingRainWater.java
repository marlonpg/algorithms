package org.example;

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int l = 0, r = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int trapped = 0;

        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= leftMax) {
                    leftMax = height[l];
                } else {
                    trapped += leftMax - height[l];
                }
                l++;
            } else {
                if (height[r] >= rightMax) {
                    rightMax = height[r];
                } else {
                    trapped += rightMax - height[r];
                }
                r--;
            }
        }
        return trapped;
    }
}
