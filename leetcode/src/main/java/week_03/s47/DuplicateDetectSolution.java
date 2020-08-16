package week_03.s47;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DuplicateDetectSolution {

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
            // 判重 TODO 在这里判重的成本很高
            results.add(result);
            return;
        }

        int tmp = nums[offset];
        List<Integer> swapped = new ArrayList<>(nums.length);
        for(int i = offset; i < nums.length; i++){
            // handle current level
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
            swapped.add(nums[i]);
        }
    }

}
