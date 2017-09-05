package interview.prepare;

import java.util.Arrays;

/**
 * Created by Rika on 2017/9/5.
 */
public class Sorting {
    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        int[] arr = sorting.insertionSort(new int[] {2,1,3,5,4});
        System.out.println(Arrays.toString(arr));

        arr = sorting.countingSort(new int[] {2,1,3,5,4}, 3);
        System.out.println(Arrays.toString(arr));

        arr = sorting.radixSort(new int[] {2,111,3,50,4}, 3);
        System.out.println(Arrays.toString(arr));
    }

    public int[] insertionSort(int[] arr) {
        for (int i=1 ; i< arr.length ; i++) {
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
        for (int i=1; i<=d; i++) {
            arr = countingSortforRadix(arr, i);
        }
        return arr;
    }

    private int[] countingSortforRadix(int[] arr, int radix) {
        int[] countArr = new int[200];
        for (int i=0; i<countArr.length; i++) {
            countArr[i] = 0;
        }
        for (int i=0; i<arr.length; i++) {
            int digit = getDigitForIndex(arr[i], radix);
            countArr[digit] = countArr[digit] + 1;
        }
        for (int i=1; i<countArr.length; i++) {
            countArr[i] =  countArr[i] + countArr[i-1];
        }
        int[] sortedArr = new int[arr.length];
        for (int i=arr.length-1; i>=0; i--) {
            int digit = getDigitForIndex(arr[i], radix);
            sortedArr[countArr[digit]-1] = arr[i];
            countArr[digit] = countArr[digit] - 1;
        }
        return sortedArr;
    }

    private int getDigitForIndex(int number, int index) {
        for (int i=index; i > 1; i --) {
            number = number/10;
        }
        return number % 10;
    }

    private int[] countingSort(int[] arr, int index) {
        int[] countArr = new int[100];
        for (int i=0; i<countArr.length; i++) {
            countArr[i] = 0;
        }
        for (int i=0; i<arr.length; i++) {
            countArr[arr[i]] = countArr[arr[i]] + 1;
        }
        for (int i=1; i<countArr.length; i++) {
            countArr[i] =  countArr[i] + countArr[i-1];
        }
        int[] sortedArr = new int[arr.length];
        for (int i=arr.length-1; i>=0; i--) {
            sortedArr[countArr[arr[i]]-1] = arr[i];
            countArr[arr[i]] = countArr[arr[i]] - 1;
        }
        return sortedArr;
    }
}
