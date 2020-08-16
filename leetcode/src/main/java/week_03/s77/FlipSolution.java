package week_03.s77;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlipSolution {

    private List<List<Integer>> result;
    private int endLevel;
    private int max;

    public List<List<Integer>> combine(int n, int k) {
        result = new LinkedList<>();
        endLevel = k;
        max = n;

        List<Integer> seed = new ArrayList<>(k);
        generate(seed, 1, 0);

        return result;
    }

    private void generate(List<Integer> seed, int start, int currentLevel){

        // 剪枝 能提供的元素 < 还需要的元素时,提前退出
        if(max-start+1 < endLevel - currentLevel){
            return;
        }

        // termonator :
        if( currentLevel == endLevel){
            result.add(new ArrayList(seed));
            return;
        }

        for(int i = start; i <= max; i++){
            // handle current level solution
            seed.add(i);
            // drill down
            generate(seed, i+1,  currentLevel+1);
            // 清楚该层元素
            seed.remove(currentLevel);
        }
    }

}
