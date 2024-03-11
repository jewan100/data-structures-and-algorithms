public class MyPermutationDFS {
  // 순열 출력을 위한 DFS
  public static void main(String[] args) {
      int N = 4; // 순열의 길이
      boolean[] visited = new boolean[N]; // 노드 방문 처리를 위한 변수
      int[] result = new int[N]; // 깊이에 따라 값을 저장하기 위한 변수
      dfs(visited, result, 0, N); // DFS 호출
  }

  public static void dfs(boolean[] visited, int[] result, int depth, int N) {
      if (depth == N) {
          // 순열이 완성되면 결과를 출력
          for (int num : result) {
              System.out.print(num + " ");
          }
          System.out.println();
          return;
      }

      for (int i = 0; i < N; i++) {
          // 방문하지 않은 노드라면
          if (!visited[i]) {
              visited[i] = true; // 방문 처리
              result[depth] = i + 1; // 순열의 각 위치에 1부터 N까지의 숫자 저장
              // DFS 호출 (재귀)
              dfs(visited, result, depth + 1, N);
              
              // 백트래킹 : 다음 경우를 위해 현재 상태를 원복
              visited[i] = false;
          }
      }
  }
}
/*
  1 2 3 4 
  1 2 4 3 
  1 3 2 4 
  1 3 4 2 
  1 4 2 3 
  1 4 3 2 
  2 1 3 4 
  2 1 4 3 
  2 3 1 4 
  2 3 4 1 
  2 4 1 3 
  2 4 3 1 
  3 1 2 4 
  3 1 4 2 
  3 2 1 4 
  3 2 4 1 
  3 4 1 2 
  3 4 2 1 
  4 1 2 3 
  4 1 3 2 
  4 2 1 3 
  4 2 3 1 
  4 3 1 2 
  4 3 2 1 
*/