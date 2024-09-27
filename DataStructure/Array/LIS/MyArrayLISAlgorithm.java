public class MyArrayLISAlgorithm {
    public static void main(String[] args) {
        int[] arr = {1, 23, 2, 14, 687, 5, 46, 345, 89, 34, 8, 32, 989};
        int lisLen = findLIS(arr);
        System.out.println(lisLen); // 6
    }

    // LIS : 최장 증가 부분 수열(LIS, Longest Increasing Subsequence)
    // 원소가 n개인 배열의 일부 원소를 골라내서 만든 부분 수열 중
    // 각 원소가 이전 원소보다 크다는 조건을 만족하고 그 길이가 최대인 부분 수열
    private static int findLIS(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        int maxLIS = 1;
        for (int i = 0; i < n; i++) // LIS배열 초기화
            lis[i] = 1;
        
        for (int i = 1; i < n; i++) {
            // i번째 인덱스의 값보다 작은 값이 있는지,
            // j번째 인덱스의 LIS값이 크다면 i번째 인덱스 LIS값 증가 
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1; // LIS 길이 증가
                maxLIS = Math.max(maxLIS, lis[i]);
            }
        }

        return maxLIS;
    }
}