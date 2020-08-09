package week_02.offer40;

import com.alibaba.fastjson.JSON;
import com.sfmd.algorithm.leetCode.s126.Solution2;

import java.util.Random;

class Solution {

    Random random = new Random();

    public int[] getLeastNumbers(int[] arr, int k) {
        // 边界条件
        if(arr.length == 0 || k == 0){
            return new int[]{};
        }
        if (arr.length == k){
            return arr;
        }
        quickSortK(arr, k);
        int[] result = new int[k];
        System.arraycopy(arr,0,result,0,k);
        return result;
    }

    private void quickSortK(int[] nums, int k){
        int start = 0;
        int end = nums.length -1;

        int pivot = -1;
        while(pivot != k){
            pivot = quickSwap(nums, start, end);
            if(pivot < k){
                start = pivot+1;
            }else if(pivot > k){
                end = pivot-1;
            }else{
                return;
            }
        }
    }

    // 将比 pivot 小的元素, 放在前面
    // 返回 pivot 的下标
    private int quickSwap(int[] nums, int start, int end){
        int pivot = nums[end];

        // j 指向下一个放置比 pivot 小的元素
        int j = start;
        int i = start;
        for(; i < end; i++){
            // 遇见比分区点小的元素,就交换
            if(nums[i] <= pivot){ // 如果这里是小于呢? 那指针左侧全部是小于, 右侧是 >= 该元素的值
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        return j;
    }



    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().getLeastNumbers(new int[]{3,1,2},2)));
        System.out.println(JSON.toJSONString(new Solution().getLeastNumbers(new int[]{3,2,1},2)));
        System.out.println(JSON.toJSONString(new Solution().getLeastNumbers(new int[]{1,2},2)));

        System.out.println(new Solution().random.nextInt());
        System.out.println(new Solution().random.nextInt());
        System.out.println(new Solution().random.nextInt());
        System.out.println(new Solution().random.nextFloat());

    }

}
