package SecondWeek;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;

/**
 * @author Christp
 * @version 1.0
 * @ClassName Test0015
 * @Description TODO
 * @date 2022/3/30 9:52
 */
public class Test0015 {
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3){
            return ans;
        }
        // 先对数组进行排序
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            // 因为排过序了，如果当前位置数字大于0的话，那么之后的都会大于0，因此不可能和等于0
            if (nums[i] > 0){
                break;
            }
            // 去重，如果与上一个位置相同的话，就到下一个位置
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // L从当前位置的下一个位置开始找，R从尾部开始找
            int L = i + 1;
            int R = len - 1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0){
                    // 如果满足条件，那么就将当前数字添加进结果集合中
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 如果下一个位置重复的话，就向前移一位
                    while (L < R && nums[L] == nums[L + 1]){
                        L++;
                    }
                    // 同理
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    L++;
                    R--;
                }else if (sum > 0){
                    // 如果和大于0的话，那就是最大位置的数太大了。就将R向前移一位
                    R--;
                }else if (sum < 0){
                    // 如果和小于0的话，那就是最小位置的数太小了，就将L向后移一位
                    L++;
                }
            }
        }
        return ans;
    }
}
