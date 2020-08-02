# 移除相同的元素


## 相似的题型整体思路

  思路:双指针解法
  1. **待更新指针**指向待更新的位置
  2. **扫描指针**指向当前扫描的位置
  3. 是否允许更新: 题目中的**判断条件**
  4. 如果不允许更新,则继续扫描; 
  5. 如果允许更新,则更新，并且更新**更新指针**,指向**下一个待更新**的位置;
  6. 看是否存在边界条件 && 初始值定义
  7. NOTE!!! _先判断普适性规律_,再考虑边界值以及初始条件的定义

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


## 快排的思想: 移动元素
