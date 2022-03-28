package FirstWeek;

import java.util.Scanner;

/**
 * @author Christp
 * @version 1.0
 * @ClassName Test0005
 * @Description TODO
 * @date 2022/3/26 18:35
 */
public class Test0005 {
    /*
     * 用于判断当前给定字符串是否是回文串
     * 如果在遍历时，当前位置对应的字符串另一端的位置不一样的话，就不是
     * */
    public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public String longestPalindrome1(String s) {
        int len = s.length();
        int max = 0;
        String ans = "";
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = test;
                    max = test.length();
                }
            }
        }
        return ans;
    }


    /*
     * 时间复杂度 O(n²)O(n²)。
     * 空间复杂度降为 O(n)O(n)。
     * */
    public String longestPalindrome2(String s) {
        if (s.equals("")) {
            return "";
        }
        int len = s.length();
        int maxLen = 0;
        int maxEnd = 0;
        String reverse = new StringBuffer(s).reverse().toString();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j >= 0; j--) {
                // 如果字符串对应位置相同的话，数组在当前所遍历到的位置长度加1并计入数组，否则长度就为0
                if (s.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                } else {
                    arr[j] = 0;
                }
                // 是否当前相等字符统计长度大于最长回文串长度
                if (arr[j] > maxLen) {
                    // 判断当前长度是否构成回文串，是的话就记录当前长度为最长长度，i的位置就为回文串结束位置
                    int beforeRev = len - 1 - j;
                    if (beforeRev + arr[j] - 1 == i) {
                        maxLen = arr[j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /*
     * 时间复杂度：O(n²）O(n²）。
     * 空间复杂度：O(1）O(1）。
     * */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 从当前位置向两边扩散，查找当前位置扩散出去最长回文子串
            int len1 = expandAroundCenter(s, i, i);// 查找偶数个数最长回文子串
            int len2 = expandAroundCenter(s, i, i + 1);// 查找单数个数最长回文子串
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        // 查找最长回文子串，向两边扩散，如果左右位置出去的相等的话，边向外扩散一个位置
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}