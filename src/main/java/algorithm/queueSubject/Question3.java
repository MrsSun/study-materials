package algorithm.queueSubject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * 例如：
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * @Author lichao11@xiaomi.com
 * @Date 2025/1/9
 */
public class Question3 {

    public static int lengthOfLongestSubstring(String s) {
        //特殊情况判断
        if (s == null || s.length() == 0){
            return 0;
        }
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            int start = i, end = i;
            //使用Set用来判断表示每次遍历的字符串中的元素是否重复
            set.clear();
            //如果end没有重复，则end++，注意边界判断
            while (end < s.length() && !set.contains(s.charAt(end))){
                set.add(s.charAt(end));
                end ++;
            }
            max = Math.max(max, end - start);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "bbtablud";
        System.out.println(lengthOfLongestSubstring(s));

        String s1 = "aabaab!bb";
        System.out.println(lengthOfLongestSubstring(s1));
    }

}
