package week_05.s214;

public class DFSSolution2 {

    public String shortestPalindrome(String s) {

        if(s.length() < 2){
            return s;
        }

        char[] data = s.toCharArray();

        int right = data.length-1;
        while(right >= 0 && !isPalindrome(data,0,right)){
            right--;
        }

        int copyLength = data.length-1-right;
        char[] result = new char[data.length + copyLength];
        System.arraycopy(data,0,result,copyLength, data.length);
        for(int i = 0; i < copyLength; i++){
            result[i] = data[data.length-1-i];
        }
        return new String(result);

    }

    private boolean isPalindrome(char[] data, int left, int right) {
        while(left < right && data[left] == data[right]){
            left++;
            right--;
        }
        return left >= right;
    }

}
