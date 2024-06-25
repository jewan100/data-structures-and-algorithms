public class MyQuickSelect {

    // 퀵 선택 진입
    public static int quickSelect(int[] arr, int k) {

        // k번째로 작은 수를 구하기 위해서 k - 1의 인덱스를 전달
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    // 퀵 정렬 알고리즘 구현 -> 재귀
    private static int quickSelect(int[] arr, int l, int r, int k) {
        
        // 더 이상 분할할 수 없을 경우 해당 인덱스가 k번째로 작은 수
        if (l == r)
            return arr[l];

        // 피벗을 기준으로 분할 -> 분할 정복
        int pivot = partition(arr, l, r);

        // k의 인덱스가 피벗과 같다면 해당 값 반환
        if (k == pivot) 
            return arr[k];
        
        // k의 인덱스가 피벗의 인덱스보다 작을 때,
        else if (k < pivot) 
            return quickSelect(arr, l, pivot - 1, k); // 피벗 기준 왼쪽 부분에서 다시 탐색
        // k의 인덱스가 피벗의 인덱스보다 클 때,
        else
            return quickSelect(arr, pivot + 1, r, k); // 피벗 기준 오른쪽 부분에서 다시 탐색
    }

    // 피벗을 기준으로 왼쪽(작은 수), 오른쪽(큰 수)로 분할
    // 피벗을 어떻게 선정하느냐에 따라 시간 복잡도가 달라짐
    // 보통 O(n)이지만 최악의 경우 O(n^2)까지 증가
    // 해당 소스는 오른쪽 인덱스로 설정
    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];

        // 피벗보다 작은 값들을 스왑하기 위한 인덱스
        int idx = l;

        for (int i = l; i < r; i++)
            // 피벗 기준으로 값이 작다면 인덱스에 따라 값 스왑
            if (arr[i] < pivot)
                swap(arr, i, idx++);
        
        // 마지막에 피벗의 위치(오른쪽 마지막)을 인덱스에 삽입해서 피벗의 최종 위치를 결정
        swap(arr, idx, r);
        // 피벗의 최종 위치 반환
        return idx;
    }

    // 배열 내 두 요소의 위치를 교환하는 함수
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {10, 4, 5, 8, 6, 11, 26};
        int k = 3;
        System.out.println(quickSelect(arr, k)); // k(3)번째로 작은 수는 6
    }
}