import java.util.Arrays;

public class MyArrayBinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 2, 7, 8, 4, 9, 3};
        Arrays.sort(arr) // 이분탐색은 정렬 된 배열에서 사용할 수 있으므로 정렬
        int target = 5; // 정렬된 배열에서 인덱스를 알고 싶은 값
        int result = binarySearch(arr, target);
        if (result == -1) // 인덱스를 찾지 못했을 경우
            System.out.println("The target value is not in the array.");
        else
            System.out.println("The index of the target value " + target + " is: " + result);
    }

    private static int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1; // 절반씩 나눠 탐색하기 위한 왼, 오른쪽 인덱스
        while (l <= r) {
            int m = l + (r - l) / 2; // 중간 인덱스
            if (arr[m] == target)
                return m;
            if (arr[m] > target) // 중간 값보다 크다 = 오른쪽 인덱스 감소
                r = m - 1;
            else                 // 중간 값보다 작다 = 왼쪽 인덱스 증가
                l = m + 1;
        }
        return -1; // 타겟이 배열 안에 없을 경우 -1 반환 -> 음수가 포함된 경우 다른 값으로
    }
}