##### 思路: 

    1. 利用快慢指针; 快指针每次走两个Node； 慢指针往后走一个Node
    2. 根据题目定义: 偶数时,取后一个指针时: 
    3. 每次都是取值下一个Node
    
    4. 循环退出条件: 快指针下一个节点为空(偶数个); 快指针.next.next == NULL 奇数个