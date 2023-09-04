package leetcode.NewYear2022.MyArray;

import java.util.*;

/**
 * Created by shaobin on 2022/1/10.
 */
public class Solution {

    // 乘积最大连续子数组
    public int maxProduct(int[] nums) {
        int ans, max, min;
        ans = max = min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmax = max, tmin = min;
            max = Math.max(tmax * nums[i], Math.max(tmin * nums[i], nums[i]));
            min = Math.min(tmax * nums[i], Math.min(tmin * nums[i], nums[i]));
            ans = Math.max(ans , max);
        }
        return ans;
    }

    // 多数元素，O(n)时间O(1)空间找到超过一半的数字
    public int majorityElement(int[] nums) {
        int ans = 0;
        int cnt = 0;
        for (int x : nums) {
            if (cnt == 0) {ans = x;cnt++;}
            else if (ans == x) cnt++;
            else cnt--;
        }
        return ans;
    }

    // 翻转数组
    public void reverse(int[] nums, int start, int end) {
        for (int i = start; i < (end + start + 1) / 2; i++) {
            int t = nums[i];
            nums[i] = nums[end + start - i];
            nums[end + start - i] = t;
        }
    }

    // 旋转数组，数组向右轮转k位，k为非负数，O(1)空间，三次翻转可实现
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    // 判断数组中是否有重复元素，不唯一，且不知道重复个数，Set实现
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (set.contains(x)) {
                return true;
            }
            set.add(x);
        }
        return false;
    }

    // 移动零，将零移动到末尾，其他顺序不变
    public void moveZeroes(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) zeroCount++;
            else nums[i - zeroCount] = nums[i];
        }
        for (int i = nums.length - zeroCount; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // 两个数组的交集，乱序，需要返回交集重复数字
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = countIntegerArray(nums1);
        HashMap<Integer, Integer> map2 = countIntegerArray(nums2);
        HashMap<Integer, Integer> ansmap = new HashMap<>();
        if (map1.size() < map2.size()) ansmap = getIntersect(map1, map2);
        else ansmap = getIntersect(map2, map1);
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer key : ansmap.keySet()) {
            int cnt = ansmap.get(key);
            for (int i = 0; i < cnt; i++) {
                list.add(key);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
    // 两个数组的交集，顺序，需要返回交集重复数字
    public int[] intersectII(int[] nums1, int[] nums2) {
        int i1 = 0;
        int i2 = 0;
        ArrayList<Integer> list = new ArrayList();
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                list.add(nums1[i1]);
                i1++;
                i2++;
            }
            else if (nums1[i1] < nums2[i2]) i1++;
            else i2++;
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
    public HashMap<Integer, Integer> countIntegerArray(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            if (map.containsKey(x)) {
                int t = map.get(x) + 1;
                map.remove(x);
                map.put(x, t);
            }
            else map.put(x, 1);
        }
        return map;
    }
    public HashMap<Integer, Integer> getIntersect(HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2) {
        HashMap<Integer, Integer> ans = new HashMap<>();
        for (Integer key : map1.keySet()) {
            if (map2.containsKey(key)) {
                int cnt1 = map1.get(key);
                int cnt2 = map2.get(key);
                int cnt = cnt1 < cnt2 ? cnt1 : cnt2;
                ans.put(key, cnt);
            }
        }
        return ans;
    }


    // 递增的三元子序列，乱序数组中有三个元素递增则返回true，三个元素不用相邻
    public boolean increasingTriplet(int[] nums) {
        int first,second;
        first = nums[0];
        second = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > first && nums[i] > second) {
                return true;
            }
            if (nums[i] > first) {
                second = nums[i];
            }
            else {
                first = nums[i];
            }
        }
        return false;
    }

    // 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        boolean oneZero = false;
        boolean twoZero = false;
        for (int num : nums) {
            if (num != 0) product *= num;
            else if (oneZero) twoZero = true;
            else oneZero = true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (twoZero) nums[i] = 0;
            else if (oneZero && nums[i] == 0) nums[i] = product;
            else if (oneZero) nums[i] = 0;
            else nums[i] = product / nums[i];
        }
        return nums;
    }

    // 合并区间
    public int[][] merge(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals.length - 1; j++) {
                if (intervals[j][0] > intervals[j + 1][0]) {
                    int[] t = intervals[j];
                    intervals[j] = intervals[j + 1];
                    intervals[j + 1] = t;
                }
            }
        }
        Stack<int[]> stack = new Stack<>();
        stack.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] temp = stack.peek();
            if (intervals[i][0] <= temp[1]) {
                temp = stack.pop();
                temp[1] = intervals[i][1] > temp[1] ? intervals[i][1] : temp[1];
                stack.push(temp);
            }
            else stack.push(intervals[i]);
        }
        int[][] ans = new int[stack.size()][2];
        int index = stack.size() - 1;
        while (!stack.isEmpty()) {
            int[] temp = stack.pop();
            ans[index--] = temp;
        }
        return ans;
    }

    //数组中第K大的数
    public void swap(int[] nums, int l, int r) {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }
    public int findKthLargestHelper(int[] nums, int k, int start, int end) {
        if (end - start == 1) {
            if (k == 1) return nums[end] > nums[start] ? nums[end] : nums[start];
            else return nums[end] > nums[start] ? nums[start] : nums[end];
        }
        int left = start + 1;
        int right = end;
        int index = start;
        while (left < right) {
            while (nums[right] > nums[index] && right > start) {
                right--;
            }
            while (nums[left] <= nums[index] && left < end) {
                left++;
            }
            if (left < right) swap(nums, left, right);
        }
        if (nums[right] < nums[index]) swap(nums, index, right);
        if (end - right + 1 > k) return findKthLargestHelper(nums, k, right + 1, end);
        if (end - right + 1 < k) return findKthLargestHelper(nums, k - end + right - 1, start, right - 1);
        return nums[right];
    }
    public int findKthLargest(int[] nums, int k) {
        return findKthLargestHelper(nums, k, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {1,2,3,4,5,6,7};
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        solution.rotate(a, 3);
    }
}
