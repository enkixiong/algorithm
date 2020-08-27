package week_04.homework.s860_lemonChange;

public class Solution {

    public boolean lemonadeChange(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;
        for(int num : bills){
            if(num == 5){
                fiveCount++;
            } else {
                fiveCount--;
                tenCount += num == 10 ? 1 : -1;
                if(tenCount < 0){
                    tenCount++;
                    fiveCount -= 2;
                }
                if(fiveCount < 0){
                    return false;
                }
            }

        }
        return true;
    }

}
