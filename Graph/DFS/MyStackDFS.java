import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

class Graph {
    int vertices; // 정점의 개수
    List<List<Integer>> adjancecyList; // 인접 행렬 리스트

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjancecyList = new ArrayList<>();
        
        // 초기화
        for(int i = 0; i < vertices; i++) {
            adjancecyList.add(new ArrayList<>());
        }
    }

    public void add(int x, int y) {
        adjancecyList.get(x).add(y);
        adjancecyList.get(y).add(x); // 무방향 그래프의 경우 양쪽에 추가
    }
}

public class MyStackDFS {

    public static void main(String[] args) {
        int vertices = 7;
        Graph graph = new Graph(vertices);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(1, 3);
        graph.add(2, 4);
        graph.add(2, 6);
        graph.add(3, 5);
        graph.add(4, 6);

        // 작은 순서대로 Stack에 넣기 위해 내림차순 정렬
        for(List<Integer> edge : graph.adjancecyList) {
            edge.sort((o1, o2) -> o2 - o1);
        }

        // 모든 정점을 출발 정점으로 하는 DFS
        for(int i = 0; i < vertices; i++){
            boolean[] visited = new boolean[vertices]; // 방문 체크
            Stack<Integer> stk = new Stack<>(); // DFS를 위한 Stack

            int[] result = new int[vertices]; // 값을 저장하기 위한 배열
            int depth = 0; // 현재 깊이를 나타내는 변수

            stk.push(i); // 시작 노드

            while(!stk.isEmpty()) {
                int cur = stk.pop();

                if(visited[cur]) continue;

                visited[cur] = true; // 방문 처리
                result[depth] = cur; // 현재 깊이에 현재 노드 저장

                // 배열이 완성되었으면 출력
                if(depth == vertices - 1) {
                    for(int vertex : result) {
                        System.out.print(vertex + " ");
                    }
                    System.out.println();
                    break; // 다음 DFS 진행
                }

                // 현재 노드의 간선 정보
                for(int vertex : graph.adjancecyList.get(cur)) {
                    // 간선 정보에서 방문하지 않은 정점이 있다면 스택에 추가
                    if(!visited[vertex]) {
                        stk.push(vertex);
                    }
                }
                // 깊이 증가
                depth++;
            }
        }
    }
}
/*
 * 0 1 3 5 2 4 6
 * 1 0 2 4 6 3 5
 * 2 0 1 3 5 4 6
 * 3 1 0 2 4 6 5
 * 4 2 0 1 3 5 6
 * 5 3 1 0 2 4 6
 * 6 2 0 1 3 5 4
 */