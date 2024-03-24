import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MyDijkstraAlgorithm {

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 다익스트라 수행
    public static void dijkstra(List<List<Edge>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n]; // 시작 노드로부터의 최단 거리를 저장하는 배열
        Arrays.fill(dist, Integer.MAX_VALUE); // 모든 거리를 무한대로 초기화
        dist[start] = 0; // 시작 노드의 거리는 0으로 설정

        // 선형 탐색으로 구현 시 O(N^2)의 시간 복잡도를 가지지만 힙 구조를 이용하여 시간복잡도를 줄일 수 있음.
        // 우선순위 큐는 이진 힙(Binary Heap)을 기반으로 구현됨
        // 가장 짧은 거리를 가진 정점을 빠르게 찾아낼 수 있고, 삽입과 삭제 연산이 O(logN)의 시간 복잡도를 가짐
        PriorityQueue<Integer> pq = new PriorityQueue<>((v1, v2) -> dist[v1] - dist[v2]);
        pq.offer(start); // 시작 노드를 우선순위 큐에 추가

        while (!pq.isEmpty()) {
            int u = pq.poll(); // 우선순위 큐에서 최단 거리를 갖는 정점을 꺼냄

            // 현재 정점에 연결된 모든 간선에 대해 최단 거리 갱신
            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                // 시작 노드를 통해 u까지의 거리(dist[u])가 최소값이며,
                // u를 통해 v까지의 거리(dist[v])보다 더 짧은 경로가 있다면
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight; // 최단 거리 갱신
                    pq.offer(v); // 우선순위 큐에 v를 추가하여 다음에 처리
                }
            }
        }

        // 각 정점까지의 최단 거리 출력
        for (int i = 0; i < n; i++) {
            System.out.println(start + " -> " + i + " : " + dist[i]);
        }
    }

    public static void main(String[] args) {

        int n = 5; // 노드의 수

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 단방향 간선
        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(2, 5));
        graph.get(1).add(new Edge(2, 2));
        graph.get(1).add(new Edge(3, 1));
        graph.get(2).add(new Edge(1, 3));
        graph.get(2).add(new Edge(3, 9));
        graph.get(2).add(new Edge(4, 2));
        graph.get(3).add(new Edge(4, 4));

        dijkstra(graph, 0); // 그래프와 시작노드 전달
        // start -> to : minWeight
        //   0   -> 0  :     0
        //   0   -> 1  :     8
        //   0   -> 2  :     5
        //   0   -> 3  :     9
        //   0   -> 4  :     7
    }

}
