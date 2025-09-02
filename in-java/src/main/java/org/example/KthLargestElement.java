package org.example;

import java.util.PriorityQueue;

/**
 * Finding the Kth largest element in an unsorted array.
 * A Heap is a specialized tree-based data structure. In Java, the PriorityQueue class provides a min-heap implementation by default. 
 * A min-heap ensures that the smallest element is always at the root, making it efficient to access. We can leverage this to track the K largest elements we've seen.
 * The strategy is to maintain a min-heap of size K. As we iterate through the array, we compare each element to the smallest one in our heap (the root). 
 * If the current element is larger, it deserves a spot among the top K elements, so we remove the smallest element from the heap and add the new, larger one. 
 * After iterating through the entire array, the root of the heap will be the Kth largest element overall.
 *
 */
public class KthLargestElement {

    /**
     * Finds the Kth largest element in an array using a min-heap.
     *
     * @param nums An array of numbers.
     * @param k    The Kth position to find.
     * @return The Kth largest element.
     */
    public static int findKthLargest(int[] nums, int k) {
        // PriorityQueue is a min-heap by default in Java.
        // It will store the K largest elements encountered so far.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            if (minHeap.size() < k) {
                // If the heap isn't full yet, just add the element.
                minHeap.add(num);
            } else if (num > minHeap.peek()) {
                // If the current number is larger than the smallest number in the heap
                // (which is at the root, accessed by peek()), it belongs in our group.
                // So, we remove the smallest element.
                minHeap.poll();
                // And add the new, larger element.
                minHeap.add(num);
            }
        }

        // After iterating through all numbers, the root of the min-heap
        // is the Kth largest element.
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] myArray = {3, 2, 1, 5, 6, 4};
        int kValue = 2;
        int result = findKthLargest(myArray, kValue);
        System.out.println("The array is: [3, 2, 1, 5, 6, 4]");
        System.out.println("The " + kValue + "nd largest element is: " + result); // Expected output: 5

        System.out.println("--------------------");

        int[] myArray2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int kValue2 = 4;
        int result2 = findKthLargest(myArray2, kValue2);
        System.out.println("The array is: [3, 2, 3, 1, 2, 4, 5, 5, 6]");
        System.out.println("The " + kValue2 + "th largest element is: " + result2); // Expected output: 4
    }
}
