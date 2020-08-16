package week_03.s46;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TransferSolution {

    public List<List<Integer>> permute(int[] nums) {

        // seed作为种子;
        List<Integer> seed = new ArrayList<>();
        for (int value : nums) {
            seed.add(value);
        }

        List<List<Integer>> result = new LinkedList<>();
        List<Integer> current = new ArrayList<>(nums.length);

        generate(seed, current, result);

        return result;
    }

    private void generate(List<Integer> seed, List<Integer> current, List<List<Integer>> result){

        int seedSize = seed.size();

        // terminator
        if (seedSize == 0){
            result.add( new ArrayList<>(current));
            return;
        }

        int currentSize = current.size();
        for (int i = 0; i < seedSize; i++){
            // handle current level
            current.add(seed.remove(i));
            // drill down
            generate(seed, current, result);
            // erase current level affect
            seed.add(i,current.remove(currentSize));
        }

    }

}
