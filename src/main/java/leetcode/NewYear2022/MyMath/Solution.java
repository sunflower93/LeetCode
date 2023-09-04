package leetcode.NewYear2022.MyMath;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaobin on 2022/1/25.
 */
public class Solution {
    // 找出只出现过一次的数字，其他都出现过两次
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    //计算最多有多少个点在同一条直线上
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    //分数到小数
    public String fractionToDecimal(int numerator, int denominator) {
        long numeratorl = (long) numerator;
        long denominatorl = (long) denominator;
        StringBuilder sb = new StringBuilder();
        if((numerator>0 && denominator<0) ||(numerator<0 && denominator>0)){
            sb.append("-");
        }
        numeratorl = Math.abs(numeratorl);
        denominatorl = Math.abs(denominatorl);

        long res = numeratorl/denominatorl;
        numeratorl = (numeratorl%denominatorl)*10;
        sb.append(res);
        if(numeratorl!=0){
            sb.append(".");
        }

        HashMap<Long,Integer> hashmap = new HashMap<Long,Integer>();
        while(numeratorl!=0){
            if(hashmap.containsKey(numeratorl)){
                int index = hashmap.get(numeratorl);
                sb.insert(index,"(");
                sb.append(")");
                break;
            }
            else{
                res = numeratorl/denominatorl;
                hashmap.put(numeratorl,sb.length());
                numeratorl = (numeratorl%denominatorl)*10;
                sb.append(res);
            }
        }
        return sb.toString();
    }

    //阶乘后末尾0的个数
    public int trailingZeroes(int n) {
        int count = 0;
        while (n != 0){
            n /=5 ;
            count += n;
        }
        return count;
    }

    //颠倒二进制
    public int reverseBitsslow(int n) {
        int ans = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            ans |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return ans;
    }
    //颠倒二进制，掩码分治，O(1)时间和空间实现
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111
    public int reverseBits(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    //汉明重量，位1的个数
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans++;
            n &= (n - 1);
        }
        return ans;
    }

    //计算小于n的质数的个数
    //判断一个数是否为质数
    public boolean isPrime(int n) {
        if(n < 2){
            return false;
        }
        if(n == 2 || n == 3){
            return true;
        }
        else{
            int a = (int)Math.sqrt(n);
            for(int i = 2; i <= a ; i++){
                if(n % i == 0){
                    return false;
                }
            }
            return true;
        }
    }
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) ans++;
        }
        return ans;
    }

    //寻找确实的数字
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int arrSum = 0;
        for (int i = 0; i < n; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }

    //判断是否为3的幂
    public boolean isPowerOfThree(int n) {
        if (n == 1) return true;
        if (n % 3 != 0 || n == 0) return false;
        return isPowerOfThree(n / 3);
    }
    public boolean isPowerOfThreeFast(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] xy = {{-184,-551},{-105,-467},{-90,-394},{-60,-248},{115,359},{138,429},{60,336},{150,774},{207,639},{-150,-686},{-135,-613},{92,289},{23,79},{135,701},{0,9},{-230,-691},{-115,-341},{-161,-481},{230,709},{-30,-102}};
        System.out.println("answer is " + solution.maxPoints(xy));
        for (int i = 5; i <= 50; i+=5) {
            System.out.println(Math.floor(Math.log(i) / Math.log(5)));
        }
    }
}
