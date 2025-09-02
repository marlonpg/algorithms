package org.example;

import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        // maxHeap stores the smaller half (largest at top)
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // minHeap stores the larger half (smallest at top)
        minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            addNum(nums[i]);

            // if window exceeded size k, remove the outgoing element
            if (i >= k) {
                removeNum(nums[i - k]);
            }

            // once we have a valid window, add median
            if (i >= k - 1) {
                result[i - k + 1] = getMedian();
            }
        }

        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((long) maxHeap.peek() + (long) minHeap.peek()) / 2.0;
        } else {
            return (double) maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums, k)));
        // Expected: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }
}
