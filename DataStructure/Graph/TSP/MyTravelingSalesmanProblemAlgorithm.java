package DataStructure.Graph.TSP;

import java.util.Arrays;
public class MyTravelingSalesmanProblemAlgorithm {
    private static final int N = 4;     // 도시의 수
    private static final int INF = Integer.MAX_VALUE;   // 도시 간 경로가 없음을 나타낼 무한대 값

    // 각 도시 간의 거리 배열
    private static int[][] distance = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    // 메모이제이션을 위한 DP 배열
    private static int[][] dp = new int[N][1 << N];

    // 현재 도시와 방문 상태를 바탕으로 최소 비용을 계산
    private static int tsp(int city, int visited) {

        // 모든 도시를 방문한 경우, 시작지점으로 돌아감
        if (visited == (1 << N) - 1)
            return distance[city][0];

        // 이미 계산된 경로가 있을 경우 그 값을 반환 -> 메모이제이션을 이용
        if (dp[city][visited] != -1)
            return dp[city][visited];
    
        int result = INF;
        // 다음에 방문할 도시 선택
        for (int nextCity = 0; nextCity < N; nextCity++) {
            // 해당 도시의 비트가 0이 아니면 방문한 것임
            if ((visited & (1 << nextCity)) != 0)
                continue;
            
            // 다음 도시로 이동하고 + 그 도시 방문 처리후 tsp 재귀
            int temp = distance[city][nextCity] + tsp(nextCity, visited | (1 << nextCity));
            result = Math.min(result, temp); // 최소 경로
        }

        // 결과를 DP 배열에 저장
        dp[city][visited] = result;
        return result;
    }

    public static void main(String[] args) {
        // DP 배열을 -1로 초기화
        for (int[] row : dp)
            Arrays.fill(row, -1);
        
        // 시작점은 0번 도시
        // 첫 번째 방문 상태는 1 (0번 도시만 방문한 상태)
        int result = tsp(0, 1);

        System.out.println("최소 비용: " + result);
    }
}