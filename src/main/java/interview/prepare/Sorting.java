package interview.prepare;

import java.util.Arrays;

/**
 * Created by Rika on 2017/9/5.
 */
public class Sorting {
    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        int[] arr = sorting.insertionSort(new int[]{2, 1, 3, 5, 4});
        System.out.println(Arrays.toString(arr));

        arr = sorting.countingSort(new int[]{2, 1, 3, 5, 4}, 10);
        System.out.println(Arrays.toString(arr));

        arr = sorting.radixSort(new int[]{2, 111, 3, 50, 4}, 3);
        System.out.println(Arrays.toString(arr));

        arr = sorting.bucketSort(new int[]{33, 55, 22, 11, 55}, 56);
        System.out.println(Arrays.toString(arr));

        arr = sorting.bubbleSort(new int[]{33, 55, 22, 11, 55});
        System.out.println(Arrays.toString(arr));
    }

    public int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public int[] radixSort(int[] arr, int d) {
        for (int i = 1; i <= d; i++) {
            arr = countingSortforRadix(arr, i);
        }
        return arr;
    }

    private int[] countingSortforRadix(int[] arr, int radix) {
        int[] countArr = new int[200];
        for (int i = 0; i < countArr.length; i++) {
            countArr[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) {
            int digit = getDigitForIndex(arr[i], radix);
            countArr[digit] = countArr[digit] + 1;
        }
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i - 1];
        }
        int[] sortedArr = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = getDigitForIndex(arr[i], radix);
            sortedArr[countArr[digit] - 1] = arr[i];
            countArr[digit] = countArr[digit] - 1;
        }
        System.out.println("radix sort>> " + Arrays.toString(sortedArr));
        return sortedArr;
    }

    private int getDigitForIndex(int number, int index) {
        for (int i = index; i > 1; i--) {
            number = number / 10;
        }
        return number % 10;
    }

    private int[] countingSort(int[] arr, int range) {
        int[] countArr = new int[range];
        for (int i = 0; i < countArr.length; i++) {
            countArr[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]] = countArr[arr[i]] + 1;
        }
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i - 1];
        }
        int[] sortedArr = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            sortedArr[countArr[arr[i]] - 1] = arr[i];
            countArr[arr[i]] = countArr[arr[i]] - 1;
        }
        return sortedArr;
    }

    public int[] bucketSort(int[] arr, int maxValue) {
        // bucket Sort
        int[] bucket = new int[maxValue + 1];
        int[] sorted_sequence = new int[arr.length];

        for (int i = 0; i < arr.length; i++)
            bucket[arr[i]]++;

        System.out.println("Bucket sort >>" + Arrays.toString(bucket));
        int outPos = 0;
        for (int i = 0; i < bucket.length; i++)
            for (int j = 0; j < bucket[i]; j++)
                sorted_sequence[outPos++] = i;

        return sorted_sequence;
    }

    int[] bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }
}
