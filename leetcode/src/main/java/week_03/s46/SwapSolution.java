package week_03.s46;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SwapSolution {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        generate(nums,0, result);

        return result;
    }

    // 交换大法
    private void generate(int[] nums, int currentIndex, List<List<Integer>> result){

        // terminator
        if (currentIndex == nums.length -1){
            List<Integer> r = new ArrayList<>(nums.length);
            for (int num : nums){
                r.add(num);
            }
            result.add(r);
            return;
        }

        for(int i = currentIndex; i < nums.length; i++){
            // current level handle
            int tmp = nums[currentIndex];
            nums[currentIndex] = nums[i];
            nums[i] = tmp;

            // drill down
            generate(nums,currentIndex+1, result);

            // remove current level affect
            nums[i] = nums[currentIndex];
            nums[currentIndex] = tmp;

        }
    }
}
