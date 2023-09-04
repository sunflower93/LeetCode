package leetcode.NewYear2022.MyHashAndMapping;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by shaobin on 2022/1/17.
 */
public class Solution {
    //Excel表列序号，核心为26进制转数字，int范围内
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            ans = ans * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }
        return ans;
    }

    //四数相加II，四个大小一致的数组，每个数组取一个数相加，求和为0的组合个数
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int ans = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                int sum = n1 + n2;
                if (map1.containsKey(sum)) {
                    int t = map1.get(sum) + 1;
                    map1.remove(sum);
                    map1.put(sum, t);
                }
                else map1.put(sum, 1);
            }
        }
        for (int n1 : nums3) {
            for (int n2 : nums4) {
                int sum = -n1 - n2;
                if (map2.containsKey(sum)) {
                    int t = map2.get(sum) + 1;
                    map2.remove(sum);
                    map2.put(sum, t);
                }
                else map2.put(sum, 1);
            }
        }
        for (Integer key : map1.keySet()) {
            if (map2.containsKey(key)) ans += map1.get(key) * map2.get(key);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {-1,-1};
        int[] b = {-1,1};
        int[] c = {-1,1};
        int[] d = {1,-1};
        System.out.println(solution.fourSumCount(a,b,c,d));
    }
}
