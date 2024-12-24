import java.util.ArrayList;

public class MyImprovedLCA {

    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static int n, k = 0;
    static boolean[] visited;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) {
        n = 15; // 노드의 수
        int[][] nodeInfo = { { 1, 2 }, { 1, 3 }, { 2, 4 }, { 3, 7 }, { 6, 2 }, { 3, 8 }, { 4, 9 }, { 2, 5 }, { 5, 11 },
                { 7, 13 }, { 10, 4 }, { 11, 15 }, { 12, 5 }, { 14, 7 } }; // 노드 연결 정보

        // 노드가 가질 수 있는 최대 깊이 차이
        // 2^k만큼씩 증가
        k = (int) (Math.log(n) / Math.log(2)) + 1;
        System.out.println();
        // 인접 리스트 작성
        for (int i = 0; i < n + 1; i++)
            adjList.add(new ArrayList<>());
        for (int i = 0; i < nodeInfo.length; i++) {
            int v = nodeInfo[i][0], u = nodeInfo[i][1];
            adjList.get(v).add(u);
            adjList.get(u).add(v);
        }

        visited = new boolean[n + 1]; // 방문 처리
        depth = new int[n + 1]; // 각 노드의 깊이를 저장
        parent = new int[n + 1][k]; // 각 노드의 부모를 저장

        int root = 1; // 루트
        dfs(root, 0); // 루트를 기준으로 모든 노드의 깊이를 계산하기 위해 DFS 진행

        fillParent(); // 제곱에 해당하는 부모

        int m = 6; // 쿼리의 수
        int[][] queries = { { 6, 11 }, { 10, 9 }, { 2, 6 }, { 7, 6 }, { 8, 13 }, { 8, 15 } }; // 쿼리 정보

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int v = query[0], u = query[1];
            int lca = getLCA(v, u);
            sb.append(v).append("와 ").append(u).append("의 공통 조상: ").append(lca).append("\n");
        }
        System.out.println(sb);
    }

    private static void fillParent() {
        for (int i = 1; i < k; i++)
            for (int j = 1; j < n + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
    }

    // DFS로 각 노드의 깊이를 계산
    private static void dfs(int v, int d) {
        visited[v] = true; // 방문 처리
        depth[v] = d; // 깊이 저장
        for (int u : adjList.get(v))
            if (!visited[u]) {
                parent[u][0] = v; // 부모 저장
                dfs(u, d + 1); // 다음 DFS
            }
    }

    private static int getLCA(int v, int u) {
        // v의 깊이가 항상 작도록 조정
        if (depth[v] > depth[u]) {
            int temp = v;
            v = u;
            u = temp;
        }
        // 깊이가 같아질 때까지 제곱씩 증가
        for (int i = k - 1; i >= 0; i--)
            if (depth[u] - depth[v] >= 1 << i)
                u = parent[u][i];

        // 같다면 반환
        if (v == u)
            return v;

        // 노드가 같아질 때까지 증가
        for (int i = k - 1; i >= 0; i--)
            if (parent[v][i] != parent[u][i]) {
                v = parent[v][i];
                u = parent[u][i];
            }

        return parent[v][0]; // LCA
    }
}