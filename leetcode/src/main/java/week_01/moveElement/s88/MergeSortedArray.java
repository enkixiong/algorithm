package week_01.moveElement.s88;

class MergeSortedArray {

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

}
