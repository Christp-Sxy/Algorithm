package SecondWeek;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Christp
 * @version 1.0
 * @ClassName Test0020
 * @Description 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * @date 2022/4/1 10:41
 */
public class Test0020 {

    // 错解
    public boolean checkIsTrue(String s, int index, int end, HashMap<Character, Character> map){
        if ((end - index) == 1){
            if (map.get(s.charAt(index)) == s.charAt(end)){
                return true;
            }else {
                return false;
            }
        }else {
            if ((end - index) % 2 == 1){
                return false;
            }else {
                for (int i = end; i >= index; i--) {
                    if (map.get(s.charAt(index)) == s.charAt(i) && index != (i)) {
                        if ((index - i) % 2 == 1){
                            return false;
                        }else if (i == (end - 1)){
                            return true;
                        }else {
                            return checkIsTrue(s, index + 1, i, map);
                        }
                    }
                }
                return false;
            }
        }
    }

    public boolean isValid1(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        return checkIsTrue(s, 0, s.length() - 1, map);
    }



}

// 最优解
class Solution {
    static char[] stack = new char[10001]; // 使用char数组减小内存开销
    static int top;
    public boolean isValid(String s) {
        top = 0;
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            int c = getNumber(s.charAt(i));
            if (c <= 2) {
                stack[top++] = (char)c; // 左括号进栈
            } else if (top == 0 || c - stack[--top] != 3) {
                return false; // 检测栈是否为空或括号是否匹配
            }
        }
        return top == 0;
    }
    static private int getNumber(char c) {
        switch (c) {
            case '(' : return 0;
            case '[' : return 1;
            case '{' : return 2;
            case ')' : return 3;
            case ']' : return 4;
            case '}' : return 5;
            default: return 0;
        }
    }
}