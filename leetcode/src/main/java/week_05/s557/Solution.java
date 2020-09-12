package week_05.s557;

class Solution {
    public String reverseWords(String s) {
        char[] main = s.toCharArray();
        int start = 0; // 单词的起始位置
        int end = 0; // 指向下一个空格的位置
        while(end < main.length){
            start = end;
            while(start < main.length && main[start] == ' '){
                start++;
            }
            end = start+1;
            // 将end定位到空格,或者
            while(end < main.length && main[end] != ' '){
                end++;
            }
            swap(main,start, end-1);
        }
        return new String(main);
    }

    private void swap(char[] main, int start, int end){
        while(start < end){
            char tmp = main[start];
            main[start] = main[end];
            main[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("Let's take LeetCode contest"));
    }

}
