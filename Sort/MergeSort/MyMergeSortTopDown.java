public class MyMergeSortTopDown {

    // 합병 정렬 메인 함수
    public static void mergeSort(int[] arr) {

        // 정렬할 수 없을 경우 반환
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 임시 배열 생성
        int[] temp = new int[arr.length];
        // Top-Down 방식은 재귀적으로 정렬함
        mergeSort(arr, temp, 0, arr.length - 1);

    }

    // 합병 정렬 재귀 함수
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {

        // 주어진 배열을 반으로 나누는 과정을 반복할 때
        // 정렬해야할 배열의 크기가 1일 때 까지만 계속되어야하므로
        // 왼쪽 인덱스 left가 오른쪽 인덱스 right보다 작을 때에만 반복
        if (left < right) {

            // 중간 지점 계산
            int mid = left + (right - left) / 2;

            // 왼쪽 부분 배열에 대해 재귀 호출
            mergeSort(arr, temp, left, mid);
            // 오른쪽 부분 배열에 대해 재귀 호출
            mergeSort(arr, temp, mid + 1, right);

            // 정렬된 부분 배열들을 합병
            merge(arr, temp, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {

        // 임시 배열에 정렬할 부분 배열을 복사
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        // 왼쪽 부분 배열의 시작 인덱스
        int i = left;
        // 오른쪽 부분 배열의 시작 인덱스
        int j = mid + 1;
        // 합병할 배열의 시작 인덱스
        int k = left;

        // 왼쪽과 오른쪽 부분 배열 비교하여 합병
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                // 왼쪽의 요소가 오른쪽의 요소보다 작거나 같으면
                // 왼쪽의 요소를 합병할 배열에 추가 -> arr의 기존 값을 덮어쓰기
                arr[k] = temp[i];
                // 다음 비교를 위해 왼쪽 부분 배열의 인덱스를 한 칸 증가
                i++;
            } else {
                // 오른쪽 요소가 왼쪽 요소보다 작거나 같으면
                // 오른쪽 요소를 합병할 배열에 추가 -> arr의 기존 값을 덮어쓰기
                arr[k] = temp[j];
                // 다음 비교를 위해 왼쪽 부분 배열의 인덱스를 한 칸 증가
                j++;
            }
            // 왼쪽이든 오른쪽이든 작은 값으로 합병하였으므로
            // 합병할 배열의 다음 위치로 인덱스 증가
            k++;
        }

        // 남은 요소들을 합병된 배열에 추가
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }

        // 남은 요소들을 합병된 배열에 추가하는 과정에서는 왼쪽 부분 배열만 고려하면 됨
        // -> 커버리지 0
        // 오른쪽 부분 배열의 요소들은 이미 합병 과정에서 왼쪽 부분 배열의 모든 요소들과 비교되고 합병되었기 때문
        // while (j <= mid) {
        //     arr[k] = temp[j];
        //     j++;
        //     k++;
        // }
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 3, 8, 10, 1, 2, 5, 7, 9};
        mergeSort(arr);
        for (int num : arr) {
            System.out.print(num + " "); // 1 2 3 3 5 6 7 8 9 10 
        }
    }
}