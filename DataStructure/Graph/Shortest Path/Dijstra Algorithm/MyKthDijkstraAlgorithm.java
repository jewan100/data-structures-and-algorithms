import java.util.*;

public class MyKthDijkstraAlgorithm {

    static class Node implements Comparable<Node> {
        int to;
        long cost;
        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) {
        int n = 5, m = 6, k = 3;

        // 그래프 초기화
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        // 예시 그래프 구성 (1번에서 시작)
        graph.get(1).add(new Node(2, 1));
        graph.get(1).add(new Node(3, 2));
        graph.get(2).add(new Node(4, 3));
        graph.get(3).add(new Node(4, 2));
        graph.get(2).add(new Node(5, 2));
        graph.get(4).add(new Node(5, 1));

        long result = kthDijkstra(graph, n, 1, 5, k);
        System.out.println(k + "번째 최단거리 = " + (result == -1 ? "없음" : result));
    }

    static long kthDijkstra(List<List<Node>> graph, int n, int start, int end, int k) {
        // 각 노드별로 K개의 최단 거리만 저장
        PriorityQueue<Long>[] dist = new PriorityQueue[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = new PriorityQueue<>(Collections.reverseOrder());

        // (거리, 노드) 형태의 탐색 큐 (오름차순)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start].offer(0L);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : graph.get(cur.to)) {
                long newCost = cur.cost + next.cost;
                PriorityQueue<Long> q = dist[next.to];

                // 아직 K개 미만이면 추가
                if (q.size() < k) {
                    q.offer(newCost);
                    pq.offer(new Node(next.to, newCost));
                }
                // 이미 K개지만 가장 큰 값보다 작으면 교체
                else if (q.peek() > newCost) {
                    q.poll();
                    q.offer(newCost);
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }

        // end 노드의 K번째 최단거리 반환
        return dist[end].size() == k ? dist[end].peek() : -1;
    }
}
