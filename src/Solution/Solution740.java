package Solution;

import java.util.HashMap;
import java.util.Map;

public class Solution740 {
    public static void main(String[] args){
        int[] nums = new int[]{3, 4, 2};
        System.out.println(deleteAndEarn(nums));
    }
    public static int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            max = Math.max(max, nums[i]);
        }
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = map.getOrDefault(1, 0);
        for(int i = 2; i <= max; i++){
            if(map.containsKey(i)){
                dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + map.get(i) * i);
            }else{
                dp[i % 2] = dp[(i - 1) % 2];
            }
        }
        return dp[max % 2];
    }
}
