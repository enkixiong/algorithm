#### 滑动窗口问题

##### 思路: 

    1. 右指针往右滑动; 当满足条件时停止; 
    2. 左指针往右滑, 直到不满足条件为止
    3. 此时获取局部最优解； 暂存；
    4. 继续步骤1