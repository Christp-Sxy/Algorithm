package FirstWeek;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Christp
 * @version 1.0
 * @ClassName Test0001
 * @Description 两数之和算法
 * @date 2022/3/21 18:46
 */


public class Test0001 {
    /**
     * 自己写的，暴力枚举法，效率较低，代码过于冗余，效率比官方给的低
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSum(int[] nums, int target) {
        boolean flag = false;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i + 1; j--) {
                if ((nums[i] + nums[j]) == target) {
                    flag = true;
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        if (flag = false){
            return new int[0];
        }else {
            return result;
        }
    }

    /**
     * 官方给出的暴力枚举法
     * 时间复杂度：O(N^2)
     * 其中 N是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * 空间复杂度：O(1)。
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 使用哈希表大致的思路是先建立一个哈希表，遍历数组，先检查表中是否含有”target - nums[i]“的数，
     * 如果有的话，那么结果就是当前查询到的位置，加上”target - nums[i]“所在的位置
     *
     *时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，我们可以 O(1) 地寻找 target - x。
     *
     * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。

     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])){
                return new int[] {hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
