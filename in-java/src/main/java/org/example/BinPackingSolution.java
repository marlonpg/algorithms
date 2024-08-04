package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinPackingSolution {
    public static void main(String[] args) {
        System.out.println(firstFitDecreasing(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 5}, 9));
    }

    public static int firstFit(int[] items, int binCapacity) {
        List<Integer> bins = new ArrayList<>();

        for (int item : items) {
            boolean placed = false;

            for (int i = 0; i < bins.size(); i++) {
                if (bins.get(i) + item <= binCapacity) {
                    bins.set(i, bins.get(i) + item);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                bins.add(item);
            }
        }

        return bins.size();
    }

    public static int firstFitDecreasing(int[] items, int binCapacity) {
        Arrays.sort(items);
        reverse(items);
        return firstFit(items, binCapacity);
    }

    private static void reverse(int[] items) {
        int i = 0, j = items.length - 1;
        while (i < j) {
            int temp = items[i];
            items[i] = items[j];
            items[j] = temp;
            i++;
            j--;
        }
    }
}
