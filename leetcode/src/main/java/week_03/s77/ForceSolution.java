package week_03.s77;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ForceSolution {

    private List<List<Integer>> result;

    public List<List<Integer>> combine(int n, int k) {
        result = new LinkedList<>();
        List<Integer> seed = new ArrayList<>(k);

        generate(seed, 1, n, k);

        return result;
    }

    private void generate(List<Integer> seed, int start, int end, int endLevel){

        // termonator :
        if( seed.size() == endLevel ){
            result.add(new ArrayList<>(seed));
            return;
        }

        for(int i = start; i <= end; i++){
            // handle current level solution
            seed.add(i);
            // drill down
            generate(seed, i+1, end, endLevel);
            // 清楚该层元素
            seed.remove(seed.size()-1);
        }
    }
}
