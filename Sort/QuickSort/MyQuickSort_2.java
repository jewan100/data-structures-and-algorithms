public class MyQuickSort_2 {

    // 퀵 정렬 진입
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // 퀵 정렬 알고리즘 구현
    private static void quickSort(int[] arr, int start, int end) {
        // 종료 기준
        if (start >= end) {
            return;
        }
        // 피벗을 기분으로 분할하여 재귀적으로 정렬 -> 분할 정복
        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot - 1); // 피벗 기준 왼쪽 부분 배열 정렬
        quickSort(arr, pivot + 1, end); // 피벗 기준 오른쪽 부분 배열 정렬
    }

    // 분할 과정을 수행하여 피벗의 최종 위치를 찾는 함수
    private static int partition(int[] arr, int start, int end) {
        // 피벗을 부분 배열의 첫 번째 요소로 설정
        int pivot = arr[start];
        int i = start;

        // 배열을 피벗을 기준으로 분할하는 과정 수행
        for (int j = start + 1; j <= end; j++) {
            // 피벗보다 작은 값이면 i를 증가시키고 현재 요소와 i 위치의 요소를 교환
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // 피벗과 right가 가리키는 값을 교환하여 피벗의 최종 위치를 결정
        swap(arr, i, start);
        // 피벗의 최종 위치 반환
        return i;
    }

    // 배열 내 두 요소의 위치를 교환하는 함수
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 76, 2, 345, 97, 345, 324, 12345, 125, 3, 4, 123, 46, 6, 58, 789};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " "); // 1 2 3 4 6 46 58 76 97 123 125 324 345 345 789 12345
        }
    }
}
