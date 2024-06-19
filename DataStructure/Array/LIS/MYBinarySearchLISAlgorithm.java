import java.util.Arrays;

public class MyBinarySearchLISAlgorithm {
    
    public static void main(String[] args) {
        int[] arr = {1, 23, 2, 2, 14, 687, 5, 46, 345, 89, 34, 8, 32, 989, 2};
        int lisLen = findLISWithBinarySearch(arr);
        System.out.println(lisLen); // 6
    }

    private static int findLISWithBinarySearch(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n]; // 각 길이의 최솟값을 기억
        int len = 0; // LIS의 최대 길이
        
        // 매 탐색마다 길이가 len인 LIS들의 마지막 값중 최솟값
        for (int num : arr) {
            // Arrays.binarySearch(배열, 시작 인덱스, 끝 인덱스, 찾고자 하는 값);
            int idx = Arrays.binarySearch(lis, 0, len, num);
            // 해당 값이 들어갈 수 있는 인덱스를 찾음
            lis[idx] = num; // 해당 인덱스의 최솟값 갱신 -> lis의 길이별 최솟값만 기억
            if (idx == len) // lis의 길이가 늘어났으면 최대 길이 갱신
                len++;
        }
        // lis = {1, 2, 5, 8, 32, 989, 0, 0 ...};
        // 각 길이 별 최솟값들 -> LIS
        return len;
    }
}