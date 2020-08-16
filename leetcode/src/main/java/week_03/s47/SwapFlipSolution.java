package week_03.s47;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SwapFlipSolution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new LinkedList<>();

        generate(nums,0,results);

        return results;
    }

    private void generate(int[] nums, int offset, List<List<Integer>> results){

        // terminator : 如果所有的数据全部都选择过了
        if (offset == nums.length -1){
            List<Integer> result = new ArrayList<>(nums.length);
            for (int num : nums){
                result.add(num);
            }
            results.add(result);
            return;
        }

        int tmp = nums[offset];
        // 每一次只选择新的数字进行交换
        List<Integer> swapped = new ArrayList<>(nums.length);

        // 注意这里是从offset开始,也就是说，需要做一次自己与自己进行交换
        for(int i = offset; i < nums.length; i++){
            // handle current level 这里有一个关键点: 不能与重复元素进行交换; 例如: 1,1,2,2 第一个: [1,1,2,2], 第二个: [2,1,1,2], 但是不能生成: [2,1,2,1];
            // 原因是: [2,1,2,1] 可以由: [2,1,1,2]生成; 也就是说, 带有重复元素的全排列时,最核心的是,重复元素之间的交换，不能执行两次
            if (swapped.contains(nums[i])) {
                continue;
            }
            nums[offset] = nums[i];
            nums[i] = tmp;

            // drill down
            generate(nums,offset+1, results);

            // remove current level affect
            nums[i] = nums[offset];
            nums[offset] = tmp;
            // 本次选择会对下一轮选择产生影响
            swapped.add(nums[i]);
        }
    }

}
