package com.sfmd.algorithm.leetCode.s239;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        return dequeSolution(nums, k);
    }


    /**
     * <ul>
     *     <li>
     *         1. 将前K-1个元素加入队列;
     *     </li>
     *     <li>
     *         2. 队列中,维护单调递减
     *     </li>
     *     <li>
     *         3. 循环加入后续元素;<br/>
     *         3.1 看看当前元素是否可以加入;<br/>
     *         3.2 如果不能加入,删除队尾元素;<br/>
     *         3.3 维护队列单调递减;<br/>
     *         3.4 队尾是当前窗口内最大的元素;<br/>
     *     </li>
     * </ul>
     */
    private int[] dequeSolution(int[] nums, int k) {
        // 边界条件
        if (k == 1) {
            return nums;
        }
        Deque<Integer> deque = new ArrayDeque<Integer>();
        int[] result = new int[nums.length - k + 1];
        deque.addFirst(0); // 哨兵, 后续不用做判空处理
        for (int i = 0; i < nums.length; i++) {
            // 所有元素需要在滑动窗口内
            if (i - deque.getLast() == k) { // 因为一定会有元素在内部，所以，不需要做判空处理
                deque.removeLast();
            }
            // 维护单调递减
            while (!deque.isEmpty() && nums[deque.peekFirst()] <= nums[i]) {
                deque.removeFirst();
            }
            // 将当前元素加入单调双端队列
            deque.addFirst(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.getLast()];
            }
        }
        return result;
    }

    public static int[] slidingWindowMax(final int[] in, final int w) {
        final int[] max_left = new int[in.length];
        final int[] max_right = new int[in.length];

        max_left[0] = in[0];
        max_right[in.length - 1] = in[in.length - 1];

        for (int i = 1; i < in.length; i++) {
            max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

            final int j = in.length - i - 1;
            max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
        }

        final int[] sliding_max = new int[in.length - w + 1];
        for (int i = 0, j = 0; i + w <= in.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
        }
        System.out.println(JSON.toJSONString(max_left));
        System.out.println(JSON.toJSONString(max_right));
        return sliding_max;
    }

    public static int[] dpSolution(int[] nums, int k) {
        if (k == 1 || nums.length == 0) {
            return nums;
        }

        int[] left = new int[nums.length];
        left[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            left[i] = i % k == 0 ? nums[i] : Math.max(nums[i], left[i - 1]);
        }
        //int reminder = (nums.length-1) % k;
        int[] right = new int[nums.length];
        right[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = (i+1) % k == 0 ? nums[i] : Math.max(nums[i], right[i + 1]);
        }

        //System.out.println(JSON.toJSONString(left));
        //System.out.println(JSON.toJSONString(right));
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.max(left[i + k - 1], right[i]);
        }
        return result;
    }

    public static int[] simpleSolution(int[] nums, int k){

        int[] result = new int[nums.length-k+1];
        int maxOffset = findMaxOffset(nums, 0, k);
        result[0] = nums[maxOffset];
        for (int i = k; i < nums.length; i++) {
            // 结果集的索引: i-k+1
            if (nums[i] >= nums[maxOffset]){
                maxOffset = i;
            }else{
                // 表明: maxOffset已经不在当前窗口内部
                if (i - maxOffset >= k){
                    maxOffset = findMaxOffset(nums,i-k+1, k);
                }
            }
            result[i-k+1] = nums[maxOffset];
        }
        // 后一个元素 >=  前一个元素 和小于前一个元素的概率为0.5
        // 那么, 访问任何一个元素,从前往后访问: 调用 findMaxOffset 的概率时: 1/pow(2,k)
        // 均摊？ O(n) ?
        return result;
    }

    private static int findMaxOffset(int[] nums, int start, int k) {
        int endOffset = start+k;
        int maxOffset = start;
        for (int i = start+1; i < endOffset; i++) {
            if (nums[i] >= nums[maxOffset]){
                maxOffset = i;
            }
        }
        return maxOffset;
    }

    public static int[] dpSolution2(int[] nums, int k) {
        // 1. 分格子
        // 2. 每一个格子中, 计算从左开始，当前下标所能达到的最大值；不同的格子，最大值不具有传递性
        // 3. 每一个格子中, 计算从右开始，当前下标所能达到的最大值；不同的格子，最大值不具有传递性
        int[] maxLeft = new int[nums.length];
        maxLeft[0] = nums[0];

        int[] maxRight = new int[nums.length];
        maxRight[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            maxLeft[i] = i % k == 0 ? nums[i] : Math.max(nums[i], maxLeft[i - 1]);
            int rightIdx = nums.length - 1 - i;
            maxRight[rightIdx] = (rightIdx + 1) % k == 0 ?
                    nums[rightIdx] : Math.max(nums[rightIdx], maxRight[rightIdx + 1]);
        }

        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < result.length; i++) {
            // 第i个滑动窗口, 所在区间是: [i, i+k-1]
            // 那么，如果i是格子的起点，那么最大值肯定是: left[i+k-1] || right[i]
            // 如果跨两个格子,那么, 第一个格子的分割点记为m --> 最大值要么在:[i,m],[m+1, i+k-1] 这两个格子内部
            // 那么，[i,m] 属于一个格子，最大值是多少呢？ right[i]
            // [m+1,i+k-1] 这个区间的最大值是多少呢？ left[i+k-1]
            // --> 结果: max(right[i],left[i+k-1])
            result[i] = Math.max(maxRight[i], maxLeft[i + k - 1]);
        }

        return result;

    }

    public static void main(String[] args) {
//        System.out.println(JSON.toJSONString(new Solution().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)));
//        System.out.println(JSON.toJSONString(new Solution().maxSlidingWindow(new int[]{1,-1},1)));
//        System.out.println(JSON.toJSONString(new Solution().maxSlidingWindow(new int[]{1,3,1,2,0,5},3)));
//        System.out.println(JSON.toJSONString(Solution.slidingWindowMax(new int[]{9,10,9,-7,-4,-8,2,-6},5)));
        System.out.println(JSON.toJSONString(Solution.dpSolution(new int[]{-7,-8,7,5,7,1,6,0}, 4)));
        System.out.println(JSON.toJSONString(Solution.simpleSolution(new int[]{-7,-8,7,5,7,1,6,0}, 4)));



    }

}
