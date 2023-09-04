package leetcode.NewYear2022.MyHashAndMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by shaobin on 2022/1/19.
 */
public class RandomizedSet {
    int size = 0;
    HashMap<Integer, Integer> values = new HashMap<>();
    HashMap<Integer, Integer> dict = new HashMap<>();

    public RandomizedSet() {
    }

    public boolean insert(int val) {
        if (values.containsKey(val)) return false;
        values.put(val, size);
        dict.put(size++, val);
        return true;
    }

    public boolean remove(int val) {
        if (!values.containsKey(val)) return false;
        int index = values.get(val);
        values.remove(val);
        dict.remove(index);
        if (index != size - 1) {
            int value = dict.get(size - 1);
            dict.put(index, value);
            values.remove(value);
            values.put(value, index);
        }
        size--;
        return true;
    }

    public int getRandom() {
        int index = (int) Math.floor(size * Math.random());
        return dict.get(index);
    }
}
