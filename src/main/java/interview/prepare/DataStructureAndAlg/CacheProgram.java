package interview.prepare.DataStructureAndAlg;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Rika on 2017/9/18.
 */

/**
 * Microsoft interview
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
