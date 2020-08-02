package week_01.moveElement.s283;

import com.alibaba.fastjson.JSON;
import com.sfmd.algorithm.leetCode.s126.Solution2;

class MoveZeroes {

    // 思路: 将元素0换至后面; 保持扫描指针&更新指针之间的元素为0
    public void moveZeroes(int[] nums) {
        // 待写入非0元素的位置
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // swap元素
            if (nums[i] != 0){
                int tmp = nums[j];
                nums[j++] = nums[i];
                nums[i] = tmp;
            }
        }
    }

    // 扫描指针与更新指针不同,才可以直接使用赋值元素
    public void moveZeroes2(int[] nums){
        int j = nums.length-1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                j = i;
                break;
            }
        }
        for (int i = j+1; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[j++] = nums[i];
                nums[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,1};
        new MoveZeroes().moveZeroes2(array);
        System.out.println(JSON.toJSONString(array));
    }

}
