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

        arr = sorting.countingSort(new int[]{2, 1, 3, 5, 4}, 5);
        System.out.println(Arrays.toString(arr));

        arr = sorting.radixSort(new int[]{2, 111, 3, 50, 4}, 3);
        System.out.println(Arrays.toString(arr));

        arr = sorting.bucketSort(new int[]{33, 55, 22, 11, 55}, 56);
        System.out.println(Arrays.toString(arr));

        arr = sorting.bubbleSort(new int[]{33, 55, 22, 11, 55});
        System.out.println(Arrays.toString(arr));

        arr = sorting.mergeSort(new int[]{33, 55, 22, 11, 55}, 0, 4);
        System.out.println(Arrays.toString(arr));

        arr = sorting.heapSort(new int[]{33, 55, 22, 11, 55});
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
        int[] countArr = new int[10];
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

    private int[] countingSort(int[] arr, int maxValue) {
        int[] countArr = new int[maxValue + 1];
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

    //top down
    int[] mergeSort(int[] arr, int init, int end) {
        if (init < end) {
            int middle = (init + end)/2;
            mergeSort(arr, init, middle);
            mergeSort(arr, middle +1, end);
            merge(arr, init, middle, end);
        }
        return arr;
    }

    int[] merge(int[] arr, int init, int middle, int end) {
        int[] L = new int[middle - init +1];
        int[] R = new int[end - middle];

        for (int i=0; i<L.length; i++) {
            L[i] = arr[init + i];
        }

        for (int i=0; i<R.length; i++) {
            R[i] = arr[middle + 1 + i];
        }

        int left = 0;
        int right = 0;
        System.out.println("Merge >> Left: " + Arrays.toString(L) + ", Right: " + Arrays.toString(R));
        for (int i=init; i < end+1; i++) {
            if (left<L.length && right<R.length) {
                if (L[left] <= R[right]) {
                    arr[i] = L[left];
                    left++;
                } else {
                    arr[i] = R[right];
                    right++;
                }
            } else if (left>=L.length) {
                arr[i] = R[right];
                right++;
            } else {
                arr[i] = L[left];
                left++;
            }
        }
        return arr;
    }

    //TODO: bottom up


    int[] heapSort(int[] arr){
        for (int i= arr.length/2 - 1; i >=0; i--) {
            maxheapify(arr, arr.length, i);
        }
        System.out.println("Heap sort: " + Arrays.toString(arr));
        for (int i = arr.length - 1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxheapify(arr, i, 0);
            System.out.println("Heap sort: " + Arrays.toString(arr));
        }
        return arr;
    }

    int[] maxheapify(int[] arr, int heapSize, int rootIndex){
        int l = (rootIndex+1)*2 -1;
        int r = (rootIndex+1)*2;
        int largest;
        if (l < heapSize && arr[l] > arr[rootIndex]) {
            largest = l;
        } else {
            largest = rootIndex;
        }
        if (r < heapSize && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != rootIndex) {
            int temp = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = temp;
            maxheapify(arr, heapSize, largest);
        }
        return arr;
    }
}
