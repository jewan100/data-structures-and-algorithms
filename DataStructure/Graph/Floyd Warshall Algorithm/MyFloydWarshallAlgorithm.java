import java.util.Arrays;

public class MyFloydWarshallAlgorithm {

    // 플로이드 와샬
    public static void floydWarshall(int[][] graph) {
        int n = graph.length;

        // 최소 가중치를 저장할 배열
        int[][] dist = new int[n][n];
        
        // 전달 받은 데이터가 인접행렬이 아닐 시 인접행렬로 변환
        // 해당 소스에서 인자로 전달받는 graph는 인접행렬이므로 생략해도 무관

        // 최대치로 초기화
        for (int i = 0; i < n; i++) {
            // 최대치로 초기화할 시, 값을 계산할 때 오버플로우가 발생할 수도 있음
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 자기 자신으로 향하는 가중치 0으로 초기화
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        // 전달 받은 graph의 값으로 설정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 자기 자신 이외
                if (dist[i][j] != 0) {
                    dist[i][j] = graph[i][j];
                }
            }
        }

        // 플로이드 와샬 알고리즘
        // i = 출발 노드
        // j = 도착 노드
        // k = 거쳐가는 노드 즉, 경유하는 노드 i -> k -> j를 확인하기 위함
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 오버플로우를 방지하기 위한 조건식
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        // i -> k -> j (k를 경유해서 가는 경우): dist[i][k] + dist[k][j] 
                        // 경유해서 가는 경우가 현재 최소값보다 빠르다면 갱신
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        
        // 모든 노드 쌍 간의 최소 가중치 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 5, Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        floydWarshall(graph);
        // 0   5   8   9
        // INF 0   3   4
        // INF INF 0   1
        // INF INF INF 0
    }
}