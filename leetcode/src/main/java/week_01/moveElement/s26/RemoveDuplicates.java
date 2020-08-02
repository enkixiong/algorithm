package week_01.moveElement.s26;

class RemoveDuplicates {

    //https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
    public int removeDuplicates(int[] nums) {
        // 边界条件
        if(nums.length == 0){
            return 0;
        }
        // j指向当前需要去重的元素;
        int j = 0;
        // i指向扫描的元素,如果当前元素一致,则不做任何处理;
        for(int i = 1; i < nums.length; i++){
            // 如果遇见了新的元素,则将数据复制;并且j指向新的待去重的元素
            if(nums[i] != nums[j]){
                nums[++j] = nums[i];
            }
        }
        // 返回长度; 因为j指向的是元素下标,所以长度需要+1
        return j+1;
    }

}
