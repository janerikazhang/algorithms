package interview.prepare;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Rika on 2017/9/18.
 */
public class CacheProgram {
    HashMap<String, Store> cache = new HashMap<>();
    int size = 100;

    public static void main(String[] args) {
        //do something.
    }

    public void putValue(String k, String v) {
        if (cache.size() == size) {
            // cache is full, remove oldest one
            Store old = null;
            for (Store s : cache.values()) {
                if (old == null || old.timeStamp.after(s.timeStamp)) {
                    old = s;
                }
            }
            cache.remove(old);
        }

        cache.put(k, new Store(v));
    }

    private Store[] quickSort(Store[] arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot-1);
            quickSort(arr, pivot+1, end);
        }
        return arr;
    }

    private int partition(Store[] arr, int start, int end) {
        Store pivot = arr[end];
        int index = start - 1;
        for (int i=start; i < end; i++) {
            if (arr[i].timeStamp.before(pivot.timeStamp)) {
                index++;
                Store temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
        Store temp = arr[index + 1];
        arr[index + 1] = arr[end];
        arr[end] = temp;
        return index + 1;
    }

    public String getValue(int k) {
        cache.get(k).update();
        return cache.get(k).value;
    }

    class Store {
        String value;
        Date timeStamp = new Date();

        Store(String value) {
            this.value = value;
            this.timeStamp = new Date();
        }

        void update(){
            this.timeStamp = new Date();
        }
    }
}
