package week_06.offer.offer38;

import java.util.ArrayList;
import java.util.List;

class BacktraceSolution {

    public String[] permutation(String s) {
        List<String> res = new ArrayList<>();
        backTrace(res, s.toCharArray(), 0);
        return res.toArray(new String[0]);
    }

    public void backTrace(List<String> res, char[] chars, int index) {
        if (index == chars.length - 1) {
            res.add(String.valueOf(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            if (isSwap(chars, index, i)) {
                swap(chars, index, i);
                backTrace(res, chars, index + 1);
                swap(chars, index, i);
            }
        }
    }

    public void swap(char[] chars, int i, int j) {
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }

    public boolean isSwap(char[] chars, int start, int end) {
        for (int i = start; i < end; i++) {
            if (chars[i] == chars[end]) {
                return false;
            }
        }
        return true;
    }

}
