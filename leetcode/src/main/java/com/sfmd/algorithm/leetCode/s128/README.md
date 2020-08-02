#### 题解

`给定一个未排序的整数数组，找出最长连续序列的长度。`

要求算法的时间复杂度为 O(n)。

示例:

    输入: [100, 4, 200, 1, 3, 2]
    输出: 4
    解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。


#### 思路

##### 暴力查找

    1. 针对于每一个数字,查找下一个比该值大的值； 
    2. 同时维护当前值的子序列长度以及历史已经寻找到的最大长度; 
    3. 如果能找到, 则继续步骤1；
    4. 如果找不到, 则以下一个未访问的索引来进行寻找;(需要用额外的空间来进行)
    

##### 插入排序

    空间复杂度: O(1)
    时间复杂度: O(nlogn)
    
    快排的思想: 
    
#### hash表

    
    
##### 有序的数据结构: 

    1. 访问一遍数据,将数据丢入已排序好的数据结构; O(n)
    2. 访问该数据结构; O(n)
    3. 平衡二叉树! 
    4. 如何构造平衡二叉树？O(n)