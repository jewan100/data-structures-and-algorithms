import java.util.Arrays;

public class MyUnionBySize {
    private int[] parent;
    private int[] size;

    public MyUnionBySize(int n) {
        // Union By Size and Path Compression
        parent = new int[n];
        size = new int[n];

        // 초기화 : 각 원소가 자신이 속한 집합의 대표를 자신으로 설정
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // 초기화 : 사이즈를 1로 설정
        Arrays.fill(size, 1);
    }

    // Find연산 : 원소가 속한 집합의 대표를 찾는 연산
    public int find(int x) {
        if (parent[x] != x) {
            // 재귀호출과 경로 압축(Path Compression)을 통한 최적화
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union연산 : 두 원소가 속한 집합을 합치는 연산
    public void unionBySize(int x, int y) {
        int rootX = find(x); // 원소 x의 대표
        int rootY = find(y); // 원소 y의 대표

        // 두 집합의 대표가 같으면 이미 같은 집합
        if (rootX != rootY) {
            // 사이즈가 큰 쪽으로 합침
            int sizeX = size[rootX];
            int sizeY = size[rootY];

            if(sizeX < sizeY) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }
}
