import java.util.Arrays;

public class MyFloydWarshallTracking {

    // 적절한 최대 가중치 설정
    static final int INF = 10_001;

    // 플로이드 와샬
    public static void floydWarshallTracking(int[][] graph) {
        int n = graph.length;
        
        int[][] dist = new int[n][n];
        int[][] tracking = new int[n][n]; // 트래킹을 위한 배열
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] != 0) {
                    dist[i][j] = graph[i][j];
                    tracking[i][j] = j; // i에서 j를 가기 위해선 j를 거쳐야함
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        tracking[i][j] = tracking[i][k];
                        // i에서 j를 가기 위해서 원래는 j를 들려야 했지만
                        // 그 거리보다 더 적은 거리가 k를 거쳤을 경우이므로
                        // i -> j를 가기 위해서는 k를 거쳐야한다는 것을 기록
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        // i 에서 j로 가기 위한 경로 추적
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || dist[i][j] == INF) {
                    sb.append(i +"에서 " + j +"까지 가는 경로 없음\n");
                    continue;
                }
                StringBuilder path = new StringBuilder(); // 트래킹하는 경로를 저장할 StringBuilder
                int now = i;
                path.append(now).append(" ");
                // now에서 출발해서 j까지 도착할 떄까지 거치는 경로 추적
                while (now != j) {
                    path.append(tracking[now][j]).append(" ");
                    now = tracking[now][j]; // now를 다음 경로로 바꿔 줌
                }
                sb.append(i).append("에서 ").append(j).append("까지 가는 경로: ");
                sb.append(path.toString()).append("\n");
            }
        }
        System.out.println(sb);
        /*
            0에서 0까지 가는 경로 없음
            0에서 1까지 가는 경로: 0 1 
            0에서 2까지 가는 경로: 0 2 
            0에서 3까지 가는 경로: 0 3 
            0에서 4까지 가는 경로: 0 2 4 

            1에서 0까지 가는 경로: 1 3 4 0 
            1에서 1까지 가는 경로 없음
            1에서 2까지 가는 경로: 1 3 4 0 2 
            1에서 3까지 가는 경로: 1 3 
            1에서 4까지 가는 경로: 1 3 4 

            2에서 0까지 가는 경로: 2 0 
            2에서 1까지 가는 경로: 2 4 1 
            2에서 2까지 가는 경로 없음
            2에서 3까지 가는 경로: 2 3 
            2에서 4까지 가는 경로: 2 4 

            3에서 0까지 가는 경로: 3 4 0 
            3에서 1까지 가는 경로: 3 4 1 
            3에서 2까지 가는 경로: 3 4 0 2 
            3에서 3까지 가는 경로 없음
            3에서 4까지 가는 경로: 3 4 
            
            4에서 0까지 가는 경로: 4 0 
            4에서 1까지 가는 경로: 4 1 
            4에서 2까지 가는 경로: 4 0 2 
            4에서 3까지 가는 경로: 4 1 3 
            4에서 4까지 가는 경로 없음
         */
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, INF, INF, INF, INF, INF},
            {INF, 0, 2, 3, 1, 10},
            {INF, INF, 0, INF, 2, INF},
            {INF, 8, INF, 0, 1, 1},
            {INF, INF, INF, INF, 0, 3},
            {INF, 7, 4, INF, INF, 0},
        };
        floydWarshallTracking(graph);
    }
}