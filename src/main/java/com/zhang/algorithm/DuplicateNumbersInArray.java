package com.zhang.algorithm;

import java.util.*;

/**
 * @author clark
 * @Description:
 * @date 2020/5/23 15:02
 */
public class DuplicateNumbersInArray {

    public static void main(String[] args) {
        int[] arrays = new int[]{1, 2, 3, 4, 5, 6, 3, 4};
        int[] bigArray = new int[]{10, 20, 30, 10};
        duplicate(bigArray, new int[1]);
    }

    /**
     * 详解:https://blog.nowcoder.net/n/808e31c3b2424647a3743aad6e2831e7?f=comment
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }

        return -1;
    }

    public static boolean duplicate(int[] arrays, int[] duplication) {
        if (arrays == null) {
            return false;
        }
        Arrays.sort(arrays);
        int length = arrays.length;
        for (int i = 0; i < length; i++) {
            if (i != arrays[i]) {
                while (arrays[i] == arrays[arrays[i]]) {
                    duplication[0] = arrays[i];
//                    return true;
                }
                int swap = arrays[i];
                arrays[i] = arrays[arrays[i]];
                arrays[arrays[i]] = swap;
            }
        }
        return false;
    }

//    public static boolean duplicate(int nums[], int[] duplication) {
//        if (nums == null) {
//            return false;
//        }
//        int length = nums.length;
//        for (int i = 0; i < length; i++) {
//            while (nums[i] != i) {
//                if (nums[i] == nums[nums[i]]) {
//                    duplication[0] = nums[i];
//                    return true;
//                }
//                // swap
//                int tmp = nums[i];
//                nums[i] = nums[tmp];
//                nums[tmp] = tmp;
//                System.out.println("nums = " + nums);
//            }
//        }
//        return false;
//    }
}
