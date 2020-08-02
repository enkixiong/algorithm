# 移除相同的元素


## 相似的题型整体思路

  思路:双指针解法
  1. **待更新指针**指向待更新的位置
  2. **扫描指针**指向当前扫描的位置
  3. 是否允许更新: 题目中的**判断条件**
  4. 如果不允许更新,则继续扫描; 
  5. 如果允许更新,则更新，并且更新**更新指针**,指向**下一个待更新**的位置;
  6. 看是否存在边界条件 && 初始值定义
  7. NOTE!!! **先判断普适性规律**,再考虑边界值以及初始条件的定义

### 26. 删除排序数组中的重复项
  1. 更新指针 _j_ 指向当前需要去重的元素
  2. 扫描指针 _i_ 就是数组元素的扫描下标
  3. 如果扫描的元素,为当前更新指针的元素,则说明出现了重复值,则不允许更新
  4. 如果元素不一致,则扫描到了新的元素,则搬移元素为下一个位置; `nums[++j] = nums[i]`
  5. 初始值: 如果 _j_ 为-1,则需要在循环中判断 j是否是合格的下标;代码需要新增一个判断; 如果定义数组*第一个元素*为待去重元素,则j始终都是有效值;
     但是: 空数组就是一个边界值,需要提前返回


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
  
  
### 27. 移除元素

    // 注意: 这里并未使用数组中的元素作为是否更新的判断依据,所以, 不需要定义边界条件
    public int removeElement(int[] nums, int val) {
        // 更新指针
        int j = -1;
        
        for (int i = 0; i < nums.length; i++){
            // 是否允许更新
            if ( nums[i] != val ) {
                nums[++j] = nums[i];
            }
        }
        return j+1;
    }


### 80. 删除排序数组中的重复项 II

    public int removeDuplicates(int[] nums) {
        // 边界条件
        if(nums.length == 0) {
            return 0;
        }
        // 初始值定义
        int j = 0;
        boolean hasTwo = false;

        for(int i = 1; i < nums.length; i++){
            // 新元素,允许更新
            if (nums[j] != nums[i]){
                 nums[++j] = nums[i];
                 hasTwo = false;
            }else if(!hasTwo){ // 已经出现,但是只出现一次的元素,允许更新;
                nums[++j] = nums[i];
                hasTwo = true;
            }
        }
        
        // 返回值
        return j+1;
    }

### 283. 移动零

思路: 将元素0换至后面; 保持扫描指针&更新指针之间的元素为0; 问题: 数组前K个非零元素,进行了没有必要的交换
    
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
    
思路: 1. 初始化更新指针&扫描指针; 2. 严格按照更新指针&扫描指针的思路进行处理

    public void moveZeroes(int[] nums){
        // 边界值: 全部非零
        int j = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            // 遇见第一个为零的元素,则说明当前元素为待更新指针
            if (nums[i] == 0){
                // 设置待更新元素
                j = i;
                break;
            }
        }
        
        // 从待更新指针之后开始扫描元素
        for (int i = j+1; i < nums.length; i++) {
            // 更新条件
            if (nums[i] != 0){
                // 更新并且维护新的更新指针
                nums[j++] = nums[i];
                // 更新
                nums[i] = 0;
            }
        }
    }
    
### 88. 合并两个有序数组

思路: 双指针: 扫描指针&待更新指针
这里与以上题目不同的点,在于,扫描指针是两个数组的扫描位置,每次从两个扫描位置,写入待更新位置

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 更新指针
        int j = m+n-1;

        // 扫描指针
        int ptr1 = m-1;
        int ptr2 = n-1;

        // 退出条件
        while(j != -1){
            
            // 取值
            int num1 = ptr1 == -1 ? Integer.MIN_VALUE : nums1[ptr1];
            int num2 = ptr2 == -1 ? Integer.MIN_VALUE : nums2[ptr2];
            
            // 更新 值,更新指针,扫描指针
            nums1[j--] = num1 < num2 ? nums2[ptr2--] : nums1[ptr1--];
        }
    }


## 快排的思想: 移动元素
