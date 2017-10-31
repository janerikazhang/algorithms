package interview.prepare.DataStructureAndAlg;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rika on 2017/10/26.
 */
public class ArrayListManipulate {

    public static void main(String[] args) {
        ArrayListManipulate arrayListManipulate = new ArrayListManipulate();

        int r = arrayListManipulate.findSecondLargestNumber(Arrays.asList(1,2,3,4,5,6,7));
        System.out.println("Second largest number: " + r);

        int r2 = arrayListManipulate.findRotateTimes(Arrays.asList(5,4,1,2,3));
        System.out.println("Rotate times: " + r2);
    }

    public Integer findSecondLargestNumber(List<Integer> arr) {
        Integer largest = null;
        Integer secondLargest = null;

        for (Integer i : arr) {
            if (largest == secondLargest && largest == null) {
                largest = secondLargest = i;
            } else if (i > largest) {
                secondLargest = largest;
                largest = i;
            } else if (i <= largest && i > secondLargest) {
                secondLargest = i;
            }
        }
        return secondLargest;
    }

    /**
     * Consider an array of distinct numbers sorted in increasing order.
     * The array has been rotated (anti-clockwise) k number of times.
     * Given such an array, find the value of k.
     */
    public Integer findRotateTimes(List<Integer> arr) {
        int minIndex = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (minIndex == -1) {
                minIndex = i;
            } else if (arr.get(i) < arr.get(minIndex)) {
                minIndex = i;
            }
        }
        return  arr.size() - minIndex;
    }
}
