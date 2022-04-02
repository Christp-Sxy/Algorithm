package SecondWeek;

/**
 * @author Christp
 * @version 1.0
 * @ClassName Test0014
 * @Description 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * @date 2022/3/29 10:50
 */
public class Test0014 {

    /*
     * 自己写的，没有考虑执行时的重复问题
     * */
    public String longestCommonPrefix1(String[] strs) {
        String same = "";
        int w = 0;
        while (w < strs[0].length()) {
            int x = 0;
            for (int i = 0; i < strs.length; i++) {
                if ((w + 1) > strs[i].length()) {
                    break;
                } else {
                    if (strs[i].charAt(w) == strs[0].charAt(w)) {
                        x++;
                    } else {
                        break;
                    }
                }
            }
            if (x == strs.length) {
                same += strs[0].charAt(w);
                w++;
            } else {
                break;
            }
        }
        return same;
    }

    /*
     *
     * 这下面的2和3是看别人提问问为什么感觉差不多却2是9ms而3是1ms
     * 问题出在了第一个每次结束时都会王数组中再添加，等到完全遍历完或者
     * 等到不一样时数组才停止添加
     * 而第二个是当等到遍历有不一样时会直接返回指定数组的值，而遍历结束时直接返回
     * 数组中的第一个元素，并没有再调用一个变量进行存储再提取，因此速度和空间都会快
     * */
    public String longestCommonPrefix2(String[] strs) {
        String res = "";
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            char cur = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != cur) {
                    return res;
                }
            }
            res = res + cur;
        }
        return res;
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /*
     * 找到的暂时的最佳解
     * */
    public String longestCommonPrefix4(String[] strs) {
        int min = strs[0].length();
        int index = 0;
        // 先找出所有给定字符串中长度最短的
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < min) {
                min = strs[i].length();
                index = i;
            }
        }
        String str = strs[index];
        for (int i = 0; i < strs.length; i++) {
            /*
            * 从最短字符串的末尾开始查找是否为所有元素的公共子串
            * 如果不是的话就将长度减小，直到相等并且长度不为零为止
            * */
            String demo = strs[i].substring(0, min);
            while (!demo.equals(str) && min != 0) {
                str = str.substring(0, --min);
                demo = demo.substring(0, min);
            }
        }
        return str;
    }
}
