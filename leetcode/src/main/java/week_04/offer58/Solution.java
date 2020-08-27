package week_04.offer58;

public class Solution {

    public String reverseLeftWords(String s, int n) {
        // 1. 每次都转移一位,转移N次
        // 2. 跳跃; 每次跳跃n位, 如果扫到了重复节点,则+1,进行下一次跳跃
        // 3. 反转两次

        char[] array = s.toCharArray();
        int i = 0;
        int count = 0;
        while(count < array.length){
            char val = array[i];
            int current = i;
            int next = (i + n) % array.length;
            do{
                array[current] = array[next];
                current = next;
                next = (next + n) % array.length;
                count++;
            }while(next != i && count < array.length);
            array[current] = val;
            count++;
            i++;
        }
        return new String(array);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseLeftWords("abcdefg",2));
    }

}
