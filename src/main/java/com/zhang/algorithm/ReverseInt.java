package com.zhang.algorithm;


import java.util.HashMap;
import java.util.Map;

public class ReverseInt {

    private static int NUM_10 = 10;

    public static void main(String[] args) {
        System.out.println("reverseInt(158) = " + reverseInt(158));
        System.out.println("reverseNoString(158) = " + reverseNoString(-158));
        int[] arr = {1, 2,4,5,7};
        int[] ints = twoSum(arr, 7);
        System.out.println("ints = " + ints[0] + "," + ints[1]);

    }

    public static int reverseInt(int i) {
        StringBuilder sb = new StringBuilder().append(i);
        String s = sb.reverse().toString();
        return Integer.valueOf(s);
    }

    public static int reverseNoString(int i) {
        int len = 0;
        int temp = i;
        while (temp > NUM_10) {
            len = ++len;
            temp /= NUM_10;
        }

        int k = i;
        int retValue = 0;
        while ((k > NUM_10)) {
            int model = k % NUM_10;
            retValue += model * Math.pow(NUM_10, len);
            k /= 10;
            len = --len;
        }
        retValue += k;
        return retValue;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < len; i++ ) {
            if(map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            }
            map.put(target-nums[i],i);
        }
        return null;
    }

    /**
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String s = "";
        ListNode listNode = l1;
        while (listNode != null) {
            s += l1.next.val;
        }
        return null;
    }
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}


