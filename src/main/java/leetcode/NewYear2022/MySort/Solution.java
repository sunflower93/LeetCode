package leetcode.NewYear2022.MySort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shaobin on 2022/1/20.
 */
public class Solution {

    //最大数，不能拆数，用数组中非负整数组成最大数字
    public String largestNumber(int[] nums) {
        quickSortString(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int num : nums) {
            if (num != 0) flag = false;
            sb.append(String.valueOf(num));
        }
        return flag ? "0" : sb.toString();
    }
    public void quickSortString(int[] data, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = data[hi];
        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            String xy = data[i] + "" + pivot;
            String yx = pivot + "" + data[i];
            if (xy.compareTo(yx) > 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (xy.compareTo(yx) < 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }
        quickSortString(data, lo, less - 1);
        quickSortString(data, great +1, hi);
    }
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public int compareByString(int x, int y) {
        String str1 = Integer.toString(x) + Integer.toString(y);
        String str2 = Integer.toString(y) + Integer.toString(x);
        return str2.compareTo(str1);
    }

    // 找出唯一一个重复的数字
    public int findDuplicate(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[nums[i] - 1]++;
            if (ans[nums[i] - 1] > 1) return nums[i];
        }
        return 0;
    }

    // 寻找峰值，可能有多个，返回一个即可
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) return i;
            if (nums[i] > nums[i + 1]) i++;
        }
        if (nums[0] > nums[1]) return 0;
        return nums.length - 1;
    }

    // 计算小于当前元素的个数
    private int[] index;
    private int[] temp;
    private int[] tempIndex;
    private int[] ans;

    public List<Integer> countSmaller(int[] nums) {
        this.index = new int[nums.length];
        this.temp = new int[nums.length];
        this.tempIndex = new int[nums.length];
        this.ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        int l = 0, r = nums.length - 1;
        mergeSort(nums, l, r);
        List<Integer> list = new ArrayList<Integer>();
        for (int num : ans) {
            list.add(num);
        }
        return list;
    }

    public void mergeSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(a, l, mid);
        mergeSort(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    public void merge(int[] a, int l, int mid, int r) {
        int i = l, j = mid + 1, p = l;
        while (i <= mid && j <= r) {
            if (a[i] <= a[j]) {
                temp[p] = a[i];
                tempIndex[p] = index[i];
                ans[index[i]] += (j - mid - 1);
                ++i;
                ++p;
            } else {
                temp[p] = a[j];
                tempIndex[p] = index[j];
                ++j;
                ++p;
            }
        }
        while (i <= mid)  {
            temp[p] = a[i];
            tempIndex[p] = index[i];
            ans[index[i]] += (j - mid - 1);
            ++i;
            ++p;
        }
        while (j <= r) {
            temp[p] = a[j];
            tempIndex[p] = index[j];
            ++j;
            ++p;
        }
        for (int k = l; k <= r; ++k) {
            index[k] = tempIndex[k];
            a[k] = temp[k];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {1,4,7,2,5,8,0,3,6,9};
        System.out.println(solution.compareByString(10, 2));
        System.out.println(solution.largestNumber(a));
        System.out.println(1^2);
    }
}
