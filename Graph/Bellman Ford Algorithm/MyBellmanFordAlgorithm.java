import java.util.ArrayList;

public class MyBellmanFordAlgorithm {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // 그래프의 모든 간선을 저장하는 ArrayList
    static ArrayList<Edge> edges;

    // 간선을 그래프에 추가
    public static void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    // 벨만-포드 수행 알고리즘
    // v : 정점의 개수, e : 간선의 개수, s : 소스
    static boolean bellmanFord(int v, int e, int s) {
        
        // 1. 가중치 배열 초기화
        int[] dist = new int[v];
        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        // 2. V-1 번 반복하여 모든 간선에 대해 거리를 갱신 -> 간선 완화
        for (int i = 0; i < v - 1; i++) {
            for (int j = 0; j < e; j++) {
                Edge edge = edges.get(j);
                int src = edge.src;
                int dest = edge.dest;
                int weight = edge.weight;
                if (dist[src] != Integer.MAX_VALUE && dist[dest] > dist[src] + weight) {
                    dist[dest] = dist[src] + weight;
                }
            }
        }

        // 3. 1번 더 진행하여 음의 사이클이 있는지 확인
        for (int j = 0; j < e; j++) {
            Edge edge = edges.get(j);
            int src = edge.src;
            int dest = edge.dest;
            int weight = edge.weight;
            
            // 음의 사이클이 존재한다면 최소값 갱신이 되기에 검출할 수 있다.
            if (dist[src] != Integer.MAX_VALUE && dist[dest] > dist[src] + weight) {
                return true;
            }
        }
        printArr(dist, v);
        // 음의 사이클이 존재하지 않음
        return false;
    }

    static void printArr(int dist[], int v) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < v; i++) {
            System.out.println(i + " " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int v = 5;
        int e = 8;
        edges = new ArrayList<>();
        addEdge(0, 1, -1);
        addEdge(0, 2, 4);
        addEdge(1, 2, 3);
        addEdge(1, 3, 2);
        addEdge(1, 4, 2);
        addEdge(3, 2, 5);
        addEdge(3, 1, 1);
        addEdge(4, 3, -3);

        boolean isCycle = bellmanFord(v, e, 0);
        if (isCycle) {
            System.out.println("음수 사이클 발생");
        } else {
            System.out.println("음수 사이클 없음");
        }
    }
}

// 벨만-포드 알고리즘

// - 설명
// 벨만-포드 알고리즘은 다익스트라 알고리즘이 음의 가중치를 처리하지 못하는 문제를 해결하기 위해서 등장
// 그래프에서 한 정점에서 다른 모든 정점으로 가는 최단 경로를 찾는 알고리즘
// 음의 가중치를 갖는 간선이 포함된 그래프에서 사용할 수 있음.
// DP를 사용해서 점진적으로 최단 경로를 찾음

// - 특징
// 1. 음의 가중치를 허용하고, 음의 사이클을 검출할 수 있음.
// 2. 시간 복잡도는 O(VE) : 정점 * 간선
// 3. 다이나믹 프로그래밍(DP)을 사용하여 여러 번 간선 완화

// - 흐름
// 1. 초기화 : 시작 정점에서 모든 정점까지의 거리를 무한대, 자기 자신은 0
// 2. 간선 완화 : 모든 간선에 대해 여러 번 반복하여 거리를 갱신
// 3. 음의 사이클 검출 : N-1번의 반복 후에도 거리가 갱신되는 간선이 있다면 음의 사이클이 있음을 검출
