public class MyUnionFind {
    private int[] parent;

    // 생성자 선언
    public MyUnionFind(int size) {
        parent = new int[size];

        // 초기화 : 각 원소가 자신이 속한 집합의 대표를 자신으로 설정
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    // Find연산 : 원소가 속한 집합의 대표를 찾는 연산
    public int find(int x) {
        // 자기 자신이 대표인지 확인
        if (parent[x] != x) {
            // 대표가 아니라면 해당 부모로 재귀호출하여 대표를 찾음.

            // 이 과정에서 경로 압축(Path Comperssion)하지 않고 대표를 찾는다면
            // ・Time Complexity(worst case) : O(n) 
            // 경로 압축(Path Comperssion)을 하여 트리의 높이를 압축하며 대표를 찾는다면
            // ・Time Complexity(worst case) : O(log n)

            // 경로 압축(Path Comperssion)
            parent[x] = find(parent[x]);
        }
        // 자신이 대표였다면 자신을 반환
        return parent[x];
    }

    // Union연산 : 두 원소가 속한 집합을 합치는 연산
    public void union(int x, int y) {
        int rootX = find(x); // 원소 x의 대표
        int rootY = find(y); // 원소 y의 대표

        // 원소 x의 대표를 원소 y의 대표로 바꾸어 두 서로소 집합을 하나의 집합으로 합침
        parent[rootX] = rootY;
    }

    public static void main(String[] args) {
        // 초기화
        MyUnionFind myUnionFind = new MyUnionFind(11); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // Find -> Union
        myUnionFind.union(0, 1); // [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        myUnionFind.union(1, 2); // [1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        myUnionFind.union(2, 3); // [1, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10]
        myUnionFind.union(3, 4); // [1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10]
        myUnionFind.union(4, 5); // [1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10]
        myUnionFind.union(6, 7); // [1, 2, 3, 4, 5, 5, 7, 7, 8, 9, 10]
        myUnionFind.union(7, 8); // [1, 2, 3, 4, 5, 5, 7, 8, 8, 9, 10]
        myUnionFind.union(8, 9); // [1, 2, 3, 4, 5, 5, 7, 8, 9, 9, 10]
        myUnionFind.union(9, 10); // [[1, 2, 3, 4, 5, 5, 7, 8, 9, 10, 10]
        
        // 1과 5가 같은 집합인지 확인
        System.out.println(myUnionFind.find(1) == myUnionFind.find(5)); // true
        
        // 5와 6이 같은 집합인지 확인
        System.out.println(myUnionFind.find(5) == myUnionFind.find(6)); // false
    }
}
