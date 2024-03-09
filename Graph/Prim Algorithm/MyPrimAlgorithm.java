class Edge implements Comparable<Edge> {

    int to; // 간선이 연결되는 대상 노드
    int weight; // 가중치

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}


public class MyPrimAlgorithm {

    // 인자로 인접리스트를 전달
    static int primAlgorithm(List<List<Edge>> graph) {
        int vertices = graph.size();

        // 해당 노드가 MST에 속하는지 여부를 나타내는 배열
        boolean[] inMST = new boolean[vertices];

        // 우선순위 큐를 이용해서 Prim Algorithm 구현
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(0, 0));

        int minWeight = 0; // 최소 신장 트리의 전체 가중치 합

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().to;

            // 간선이 연결되는 대상 노드를 MST에 추가
            inMST[u] = true; // 방문처리
            minWeight += key[u];

            // 현재 노드에 연결된 미방분 노드들에 대해 최소 가중치 업데이트
            for (Edge neighbor : graph.get(u)) {
                int v = neighbor.to;
                int weight = neighbor.weight;

                // 방문, 최소 가중치 체크
                if (!inMST[v] && weight < key[v]) {
                    priorityQueue.add(new Edge(v, key[v]));
                }
            }
        }
        // 최소 가중치 반환
        return minWeight;
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Edge>> graph = new ArrayList<>(vertices);
        // 그래프 노드 초기화
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        // 간선 추가 (노드1, 노드2, 가중치)
        graph.get(0).add(new Edge(1, 2));
        graph.get(1).add(new Edge(0, 2));
        graph.get(1).add(new Edge(2, 3));
        graph.get(2).add(new Edge(1, 3));
        graph.get(2).add(new Edge(3, 4));
        graph.get(3).add(new Edge(2, 4));

        int result = primAlgorithm(graph);
        System.out.println("Minimum Spanning Tree Weight: " + result); // 9
    }
}
