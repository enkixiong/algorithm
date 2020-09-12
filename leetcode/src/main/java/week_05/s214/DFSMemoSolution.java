package week_05.s214;

/**
 * 中心开花的思路
 */
public class DFSMemoSolution {

    // 从中间位置开始不断的探测,从0开始的位置是否可以组成回文子串

    public String shortestPalindrome(String s) {
        if(s == null || s.length() < 2){
            return s;
        }

        char[] data = s.toCharArray();
        int half = (data.length-1) >> 1;

        int right = 0;
        // 奇数以half为中间节点或者偶数以左半边结束的节点 [0,half]||[half+1,length-1];
        for(; half >= 0; half--){
            // 奇数:以half为中间节点
            if(getLeft(data, half, half) == 0){
                right = (half << 1);
                break;
            }
            // 以half为左半侧节点
            if(half > 0 && data[half-1] == data[half] && getLeft(data, half-1, half) == 0){
                right = (half << 1)-1 ;
                break;
            }
        }

        // 产生结果
        // [0,right] 为回文子串
        int copyLength = data.length-1-right;
        char[] result = new char[data.length + copyLength];
        System.arraycopy(data,0,result,copyLength, data.length);
        for(int i = 0; i < copyLength; i++){
            result[i] = data[data.length-1-i];
        }
        return new String(result);
    }

    private int getLeft(char[] data, int left, int right) {
        while(left >= 0 && data[left] == data[right]){
            left--;
            right++;
        }
        return left+1;
    }
}
