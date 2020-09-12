package week_06.offer.offer38;

class NoRepeatSolution {

    // 全排列生成方式: 递增法 // TODO 前提,无重复元素

    public String[] permutation(String s) {
        char[] main = s.toCharArray();
        //Arrays.sort(main);

        int count = 1;
        for(int i = 2; i <= main.length; i++){
            count *= i;
        }

        String[] result = new String[count];
        int current = 0;
        int[] seed = new int[s.length()-1];
        while(current < count){
            result[current] = generate(seed, main);
            current++;
            plusOne(seed);
        }
        return result;
    }

    private String generate(int[] seed, char[] main){
        char[] result = new char[main.length];
        for(int i = 0; i < seed.length; i++){
            setValue(result, seed[i], main[main.length-1-i]);
        }
        setValue(result, 0, main[0]);
        return new String(result);
    }

    private void setValue(char[] result,int offset, char val){
        for(int i = result.length-1; i >= 0; i--){
            if(result[i] != 0){
                continue;
            }
            if(result[i] == 0 && offset == 0){
                result[i] = val;
                break;
            }
            offset--;
        }
    }

    private void plusOne(int[] seed){
        int carrier = 1;
        for(int i = seed.length-1; carrier == 1 && i >= 0; i--){
            int base = seed.length - i + 1;
            seed[i] += carrier;
            carrier = seed[i] / (base);
            seed[i] = seed[i] % (base);
        }
    }

}
