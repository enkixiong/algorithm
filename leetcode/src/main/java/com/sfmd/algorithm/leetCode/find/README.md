#### 题目标书

    给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
    
    请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    
    你可以假设 nums1 和 nums2 不会同时为空。
    
     
    
    示例 1:
    
    nums1 = [1, 3]
    nums2 = [2]
    
    则中位数是 2.0
    示例 2:
    
    nums1 = [1, 2]
    nums2 = [3, 4]
    
    则中位数是 (2 + 3)/2 = 2.5

##### 思路1: 暴力破解; 合并排序&O(1)查找
    
    合并排序时,空间复杂度为O(1)    

##### 思路1: 二分查找
    
    二分查找: 1）在一个有序列表中找到一个值为X的索引；主要应用的方法是indexOf()
    中位数查找,主要是By位数查找； 所以二分查找的思路比较难应用在中位数查找上；
    二分查找&中位数查找 是解决不同的问题

##### 思路2: 寻找第K/K+1大的元素
    
    1. 双指针思路; 两个指针都从左往右走;指针当前的位置是:  
    2. 
    
    
##### 总结:
    1. 看题时,第一件事: 这个问题本质是什么问题;
    2. 剩下看，现有的算法是哪些是解决这一类问题的；
    3. 如果没有明确的算法解决这一类问题，那么这些算法哪些变形可以解决这个问题；
    4. 变形的前提: 算法与问题的本质是契合的；
    5. 自测用例; 