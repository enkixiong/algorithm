package week_04.homework.s45_jumpGame2;

class Solution {

    public int jump(int[] nums) {
        int position = 0;
        int count = 0;
        while(true){
            // 已经跳到目标值
            if(position+1 >= nums.length){
                return count;
            }
            count++;
            // 这一次可以跳出数组
            if(position + nums[position] + 1 >= nums.length){
                return count;
            }
            // 调到下一步最好的选择
            int next = 0;
            int nextReachPosition = 0;
            for(int i = nums[position] + position; i > position; i--){
//                nums[i] + i  表示该点,可以跳跃到的最远位置
                if (nums[i] + i > nextReachPosition){
                    next = i;
                    nextReachPosition = nums[i] + i;
                }
            }
            position = next;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().jump(new int[]{1,1,2,1,1}));
        System.out.println(new Solution().jump(new int[]{0}));
        System.out.println(new Solution().jump(new int[]{2,1}));
    }

}
