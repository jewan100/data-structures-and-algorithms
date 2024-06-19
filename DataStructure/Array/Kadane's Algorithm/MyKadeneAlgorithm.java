public class MyKadeneAlgorithm {

    // 카데인 알고리즘

    // 연속 부분 수열의 최대합
    public static void maxSubarraySum(int[] arr) {

        // 최대 연속 부분 수열의 합을 저장할 변수
        int maxSum = Integer.MIN_VALUE;
        // 현재까지의 부분 수열의 합을 저장할 변수
        int currentSum = 0;

        // 배열의 각 요소에 대해 반복
        for (int n : arr) {
            // 현재 요소를 포함하는 새로운 부분 수열의 합을 계산
            // 현재 요소인 n과 current + n의 값을 비교하는 이유는
            // current가 음수일 경우 초기화를 해주기 위함
            currentSum = Math.max(n, current + n);

            // 현재까지의 부분 수열의 최대 합을 갱신
            maxSum = Math.max(maxSum, currentSum);
        }

        // 최대 연속 부분 수열의 합 출력
        System.out.println("최대 연속 부분 수열의 합 : " + maxSum);
    }

    // 연속 부분 수열의 최대합과 범위
    public static void maxSubarraySumWithIndex(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        // 부분 수열의 시작과 종료 인덱스
        int start = 0, end = 0;
        // 현재 부분 수열의 시작 인덱스를 저장할 변수
        int s = 0;

        for (int i = 0; i < arr.length; i++) {
            // 현재 요소를 부분 수열에 추가하여 합을 계산
            currentSum += arr[i];

            // 최대 연속 부분 수열의 합이 갱신될 경우
            if (maxSum < currentSum) {
                maxSum = currentSum;
                // 최대 부분 수열의 시작과 종료 인덱스 갱신
                start = s;
                end = i;
            }

            // 부분 수열의 합이 0 이하인 경우
            if(currentSum <= 0) {
                // 부분 수열의 합을 0으로 초기화
                currentSum = 0;
                // 다음 부분 수열의 시작 인덱스를 현재 인덱스의 다음으로 설정
                s = i + 1;
            }
        }
        System.out.println("최대 연속 부분 수열의 합 : " + maxSum);
        System.out.println("연속 부분 수열의 시작 인덱스 : " + start);
        System.out.println("연속 부분 수열의 종료 인덱스 : " + end);
    }

    public static void main(String[] args) {
        int[] arr = {2, -1, 3, -2, 4, -3, 5, -4, 6, -5};
        maxSubarraySum(arr); // 최대 연속 부분 수열의 합 : 10
        maxSubarraySumWithIndex(arr); 
        // 최대 연속 부분 수열의 합 : 10 
        // 연속 부분 수열의 시작 인덱스 : 0
        // 연속 부분 수열의 종료 인덱스 : 8
    }

}