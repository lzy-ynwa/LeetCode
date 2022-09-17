package Solution;

public class Solution174 {
    public static void main(String[] args){
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(calculateMinimumHP(dungeon));
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for(int i = dp.length - 1; i >= 0; i--){
            for(int j = dp[i].length - 1; j >= 0; j--){
                if(i == dp.length - 1 && j == dp[i].length - 1){
                    dp[i][j] = getMinimumHP(1 - dungeon[i][j]);
                }else if(i == dp.length - 1){
                    dp[i][j] = getMinimumHP(dp[i][j + 1] - dungeon[i][j]);
                }else if(j == dp[i].length - 1){
                    dp[i][j] = getMinimumHP(dp[i + 1][j] - dungeon[i][j]);
                }else{
                    dp[i][j] = getMinimumHP(Math.min(dp[i][j + 1] - dungeon[i][j], dp[i + 1][j] - dungeon[i][j]));
                }
            }
        }
        return dp[0][0];
    }

    private static int getMinimumHP(int rawHp){
        return rawHp > 0 ? rawHp: 1;
    }
}
