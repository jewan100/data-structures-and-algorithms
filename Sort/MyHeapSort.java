public class MyHeapSort {

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 최대 힙 구성
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 힙에서 하나씩 루트 노드를 제거하고 정렬
        for (int i = n - 1; i > 0; i--) {
            // 현재 루트 노드를 배열의 끝으로 이동
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 힙 재구성
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 왼쪽 자식이 루트보다 크면 largest 갱신
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // 오른쪽 자식이 루트보다 크면 largest 갱신
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // largest가 루트가 아니면 힙을 재구성
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // 재귀적으로 하위 트리에 대해 heapify 호출
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {11, 23, 564, 4, 67, 9, 34};
        heapSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
