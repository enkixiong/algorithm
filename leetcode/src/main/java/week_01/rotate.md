# 旋转数组

## 189. 旋转数组

思路: 1. 暴力; 每次右移一位; 2. 跳跃K位移动; 3. 翻转; 4. 开辟新的数组

### 暴力解法

1. 移动K位,直接采取的是覆盖的方式; 则会有K位数据**丢失**, 就需要`O(K)`辅助空间; 
2. 题目要求的是使用O(1)的空间
3. 所以,当`K = 1`时,只需要O(1)的空间,并且实现了元素的移动; 那么循环K次，就可以获得结果
    
**代码**    
    
    /**
     * 暴力解决方案; 每次向右移动一位; 移动K次
     */
    private void rotateViolent(int[] nums, int k) {
        int endIndex = nums.length - 1;
        for (int i = 0; i < k; i++) {
            int tmp = nums[endIndex];
            System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            nums[0] = tmp;
        }
    }

### 跳跃(循环空间)

**证明:**
1. i --> (i+K)%len  --> (i+k*2)%len --> (i+k*3)%len ... 这样的方式,访问一个数组,并且有且只有一次访问,是否可行?
2. 在这样的思路里面,期望的是, 将原数组不断的复制,可以 以K的step的步伐访问所有的元素
3. 可行? 不可行; 原因是: (i+x*k)%len == i 时, 如果x != n时, 将会出现循环; 因为 k & len 之间,有公因子; 
4. 怎么解决这个问题? 进行分组;  组数: `k & len 最大公因子` 
5. 例如: `[1,2,3,4,5,6,7,8], 4 --> [1，5],[2,6],[3,7],[4,8]`
6. 例如: `[1,2,3,4,5,6,7,8,9,10], 4 --> [1,5,9,3,7],[2,6,10,4,8]`
7. **不能给出数学上严格的证明** 

**代码**

    /**
     * 选取任意一个元素, 放置在转换后放置的位置；<br/>
     * 被放置的位置元素，作为下一个待放置的元素; <br/>
     * 如何证明? 如何证明 以一个固定间隔跳跃式的访问一个数组, 访问元素有且仅有一次的访问数组?
     */
    private void rotateSwap(int[] nums, int k) {
        int start = -1;
        int count = 0;
        while(count < nums.length){
            int index = ++start;
            int value = nums[index];
            while(count < nums.length){
                int nextIndex = (index + k) % nums.length;
                int nextValue = nums[nextIndex];
                nums[nextIndex] = value;
                count++;
                index = nextIndex;
                value = nextValue;
                if (index == start){
                    break;
                }
            }
        }
    }      

### 反转

    private void rotateReverse(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, k, nums.length - 1);
        reverse(nums,0,k-1);
    }

    private void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int tmp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = tmp;
        }
    }

## 61. 旋转链表

思路: 
1. 特性: 链表时可以实现在O(1)的时间&空间复杂度上增加删除元素,但是,获取长度(不记录的情况下)/随机访问元素时,需要的时间复杂度为`O(n)`
2. 本题根据上题思路一致,**处理k,转化为真实的 step** 
3. 因为链表不具有`O(1)`的随机访问特性,所以上述思路都不成立, 不能在O(n)的时间复杂度内解决问题
4. 链表移动元素的特点: 将节点从链表中删除,重新加入链表
5. 本题的问题就是: 获取快速的一次性在链表尾部 `摘除K个元素`,并且转移到链表的头部？

步骤: 
1. 获取长度 len
2. 计算step
3. 对于`step == 0 || len <= 1`时,直接退出
4. 访问 len - step 个元素, 并且知道前 `[0,len-step)` 元素的头尾
5. 将 `[len-step, len)` 从原链表摘除,并且反转
6. 将 `[0, len-step)` 加入到 **第5步**的尾部

**代码**

    public ListNode rotateRight(ListNode head, int k) {

        // 获取长度
        int len = 0;
        ListNode node = head;
        // 获取尾节点
        ListNode tail = node;
        while (node != null) {
            len++;
            tail = node;
            node = node.next;
        }

        // 退出
        if (len <= 1) {
            return head;
        }

        // 重新计算 k
        int step = k % len;
        if (step == 0) {
            return head;
        }

        // 找到指向开始旋转的节点
        ListNode newTail = head;
        for (int i = len - step - 1; i > 0; i--) {
            newTail = newTail.next;
        }

        ListNode kthHead = newTail.next;
        // 断开
        newTail.next = null;
        // 指向
        tail.next = head;


        return kthHead;
    }
