package diabolicTricksAndWickedCraft;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class ComputeNumLen {
    private static int getNumLengthByString(long num) {//4
        num = num > 0 ? num : 0 - num;
        return String.valueOf(num).length();
    }

    private static int getNumLengthByString2(long num) {//5
        num = num > 0 ? num : 0 - num;
        String s = num + "";
        return s.length();
    }

    private static int getNumLengthByLog(long num) {//3
        if (num == 0) return 1;
        num = num > 0 ? num : 0 - num;
        return (int)Math.log10(num) + 1;
    }

    private static int getNumLengthByLoop(long num) {//2
        if (num == 0) return 1;
        int ans = 0;
        while (num != 0) {
            ans++;
            num /= 10;
        }
        return ans;
    }

//    private static int getNumLengthByIfElse(long num) { // 缺比特位计数
//        if (num < 10) return 1;
//        if (num < 100) return 2;
//        if (num < 1000) return 3;
//        if (num < 1000000000000l) {
//            if (num < 100000000) {
//                if (num < 1000000) {
//                    if (num < 10000) return 4;
//                    else return 5 + ((num >= 100000) ? 1 : 0);
//                }
//                return 7 + (num >= 10000000 ? 1 : 0);
//            }
//            if (num < 10000000000l) return 9 + (num >= 1000000000 ? 1 : 0);
//            return 11 + (num >= 100000000000l ? 1 : 0);
//        }
//        return 12 + digits10(num / 1000000000000l);
//    }

    private static int getNumLengthByIfElse2(long num) {//1
        num = num > 0 ? num : 0 - num;
        if (num < 10) return 1;
        if (num < 100) return 2;
        if (num < 1000) return 3;
        if (num < 10000) return 4;
        if (num < 100000) return 5;
        if (num < 1000000) return 6;
        if (num < 10000000) return 7;
        if (num < 100000000) return 8;
        if (num < 1000000000) return 9;
        if (num < 10000000000L) return 10;
        if (num < 100000000000L) return 11;
        if (num < 1000000000000L) return 12;
        if (num < 10000000000000L) return 13;
        if (num < 100000000000000L) return 14;
        if (num < 1000000000000000L) return 15;
        if (num < 10000000000000000L) return 16;
        if (num < 100000000000000000L) return 17;
        if (num < 1000000000000000000L) return 18;
        return 19;
    }


    public static void main(String[] args) {
        Random random = new Random();
        long x = random.nextLong();
        int len = 0;
        System.out.println(x);
        System.out.println(LocalDateTime.now());
        System.out.println(ComputeNumLen.getNumLengthByString(x));
        System.out.println(LocalDateTime.now());
        System.out.println(ComputeNumLen.getNumLengthByString2(x));
        System.out.println(LocalDateTime.now());
        System.out.println(ComputeNumLen.getNumLengthByLog(x));
        System.out.println(LocalDateTime.now());
        System.out.println(ComputeNumLen.getNumLengthByLoop(x));
        System.out.println(LocalDateTime.now());
        System.out.println(ComputeNumLen.getNumLengthByIfElse2(x));
        System.out.println(LocalDateTime.now());
    }
}
