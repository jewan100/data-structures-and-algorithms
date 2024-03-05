class Edge implements Comparable<Edge> {
    int src; // 출발 노드
    int dest; // 도착 노드
    int weight; // 가중치

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(weight, e.weight);
    }
}


public class MyKruskalAlgorithm {

    private int vertices;
    private List<Edge> edges;

    public MyKruskalAlgorithm(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    public List<Edge> kruskalMST() {
        List<Edge> result = new ArrayList<>();

        // 우선순위 큐를 이용해서 간선을 가중치 기준으로 정렬
        PriorityQueue<Edge> pq = new PriorityQueue<>(edges);

        // 각 노드의 부모를 초기화
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        // 간선을 하나씩 선택하여 사이클을 형성하지 않으면 추가
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int rootSrc = find(parent, edge.src); // 출발 지점의 대표 노드
            int rootDest = find(parent, edge.dest); // 도착 지점의 대표 노드

            // 간선의 양 끝 노드가 같은 부모를 가지지 않으면(서로소 집합) 사이클이 형성되지 않음
            if (rootSrc != rootDest) {
                result.add(edge);
                union(parent, rootSrc, rootDest); // 서로소 집합을 Union
            }
        }
        return result;
    }

    // Find 연산
    private int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]); // 재귀 호출로 경로 압축
        }
        return parent[node];
    }

    // Union 연산
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x); // 노드 x의 대표 노드
        int rootY = find(parent, y); // 노드 y의 대표 노드
        parent[rootX] = rootY;
    }

    public static void main(String[] args) {
        MyKruskalAlgorithm myKruskalAlgorithm = new MyKruskalAlgorithm(7);
        myKruskalAlgorithm.addEdge(0, 6, 12);
        myKruskalAlgorithm.addEdge(0, 3, 28);
        myKruskalAlgorithm.addEdge(0, 1, 67);
        myKruskalAlgorithm.addEdge(0, 4, 17);
        myKruskalAlgorithm.addEdge(1, 3, 24);
        myKruskalAlgorithm.addEdge(1, 4, 62);
        myKruskalAlgorithm.addEdge(2, 4, 20);
        myKruskalAlgorithm.addEdge(2, 5, 37);
        myKruskalAlgorithm.addEdge(3, 6, 13);
        myKruskalAlgorithm.addEdge(4, 5, 45);
        myKruskalAlgorithm.addEdge(5, 6, 73);

        List<Edge> mst = myKruskalAlgorithm.kruskalMST();
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
        // 0 - 6 : 12
        // 3 - 6 : 13
        // 0 - 4 : 17
        // 2 - 4 : 20
        // 1 - 3 : 24
        // 2 - 5 : 37
        // Sum(weight) = 123
    }
}
