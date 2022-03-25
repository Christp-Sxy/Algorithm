package FirstWeek;

import javax.swing.text.MaskFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Christp
 * @version 1.0
 * @ClassName Test0003
 * @Description 求给定字符串中最长无重复子字符串
 * @date 2022/3/22 15:11
 */
public class Test0003 {
    /*
    * 时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
    * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），
    * ∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII码在
    * [0, 128)[0,128) 内的字符，即∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，
    * 而字符最多有 ∣Σ∣ 个，因此空间复杂度为 O(∣Σ∣)。
    * */

    /**
     * * 时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
     * * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），
     * * ∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII码在
     * * [0, 128)[0,128) 内的字符，即∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，
     * * 而字符最多有 ∣Σ∣ 个，因此空间复杂度为 O(∣Σ∣)。
     * @param s
     * @return int
     */
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        // 创建数组occ作为储存最长无重复字符串的集合
        Set<Character> occ = new HashSet<Character>();
        // rk作为临时保存当前循环中最长字符串的长度
        // ans储存最长无重复字符串的长度
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0){
                // 若当前循环不是第一次的话，就向后移动一位
                occ.remove(s.charAt(i - 1));
            }
            // 循环条件为当前最长的位置没有到倒数第一位，并且没有有相同的值
            while (rk + 1 < n && occ.contains(s.charAt(rk + 1))){
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 如果当前while循环结束后字符串的长度比最长的长，那么就替换值
            ans = Math.max(ans, rk - i +1);
        }
        return ans;
    }


    /**
     * 每次左指针右移一位，移除set的一个字符，这一步会导致很多无用的循环。
     * while循环发现的重复字符不一定就是Set最早添加那个，还要好多次循环才能到达，
     * 这些都是无效循环，不如直接用map记下每个字符的索引，直接进行跳转
     * @param s
     * @return int
     */
    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        int max = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            // 如果先前有存放过相同的值时，那就采取比较大的那个位置，则第一个相等的位置减去最后一次遇到的就是无重复字符串长度
            if (hashMap.containsKey(ch)){
                start = Math.max(hashMap.get(ch) + 1, start);
            }
            // 判断当前最大无重复字符串是否是最长的
            max = Math.max(max, end - start + 1);
            // 向数组中添加当前数值和位置
            hashMap.put(ch, end);
        }
        return max;
    }

    /**
     * 字符串是一个有限集合，题目说了是ASCII，最多就是128个字符，
     * 并且字符是无符号short，可以作为数组下标。无重复子串，
     * 也就是说子串中每个字符最多出现1次，且出现2次时就需要去重。
     * 由此，完全 没有必要用哈希表，因为哈希表只是理论上是O(1)的，且有自动装箱/拆箱，
     * 实际运行效率较差。
     *
     * ASCII字符最多128个，数量最多不超2,所以这里完全可以用一个长度为128的byte数组作代替哈希表。
     * @param s
     * @return int
     */
    public int lengthOfLongestSubstring3(String s) {
        int result = 0;
        if (s == null || s == "") {
            return result;
        }
        byte[] substring = new byte[128];
        int i = 0;
        /*
         从第一位开始，每次在数组中添加一位作为储存字符的位置
         每次出现一次，便在字符所在位置+1，如果出现相同的字符，
         那么就在当前字符位置+1，那么当前字符位置的值就不为一，
         于是起始位置i就向前进一位，当然，如果都是一样的话，i就增加到
         j为止，也就是当前循环的字符串长度为1，到最后知道遍历完
         字符串为止
         */
        for (int j = 0; j < s.length(); j++) {
            substring[s.charAt(j)] += 1;
            while (i < j && substring[s.charAt(j)] != 1) {
                substring[s.charAt(i)] -= 1;
                i++;
            }
            result = Math.max(result, j - i + 1);
        }
        return result;
    }

    //最佳解
    public int lengthOfLongestSubstring(String s) {
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        while (i < s.length()) {
            int pos = s.indexOf(s.charAt(i),flag);
            if (pos < i) {
                if (length > result) {
                    result = length;
                }
                // 如果当前所剩下的字符串长度已经小于最大值时，直接返回
                if (result >= s.length() - pos - 1) {
                    return result;
                }
                length = i - pos - 1;
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return length;
    }
}
