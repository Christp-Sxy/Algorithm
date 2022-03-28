package FirstWeek;

/**
 * @author Christp
 * @version 1.0
 * @ClassName Test0007
 * @Description TODO
 * @date 2022/3/28 16:16
 */
public class Test0007 {
    public int reverse(int x) {
        if (x < (-Math.pow(2, 31)) || x > (Math.pow(2, 31) - 1)){
            return 0;
        }
        String s = String.valueOf(x);
        int state = 1;
        if (x > 0){
            int[] p = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                p[i] = x % 10;
                x = x / 10;
            }
            if (p.length > 10 || ((p.length == 10) && ((x % 10) > 2) )){
                return 0;
            }
            int result = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                result += p[i] * state;
                state *= 10;
            }
            return result;
        }else {
            int[] p = new int[s.length() - 1];
            for (int i = 0; i < s.length() - 1; i++) {
                p[i] = x % 10;
                x = x / 10;
            }
            if (p.length > 10 || ((p.length == 10) && ((x % 10) < -2) )){
                return 0;
            }
            int result = 0;
            for (int i = s.length() - 2; i >= 0; i--) {
                result += p[i] * state;
                state *= 10;
            }
            return result;
        }
    }

    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            int tmp = res * 10 + x % 10;
            if (tmp / 10 != res) { // 溢出!!!
                return 0;
            }
            res = tmp;
            x /= 10;
        }
        return res;
    }

    /*
    * 极限
    * */
    public int reverse3(int x) {
        int ans = 0;
        while (x != 0) {
            if (x > 0 && ans > (Integer.MAX_VALUE - x % 10) / 10) return 0;
            if (x < 0 && ans < (Integer.MIN_VALUE - x % 10) / 10) return 0;
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans;
    }
}
