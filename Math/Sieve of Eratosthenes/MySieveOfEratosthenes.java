public class MySieveOfEratosthenes {

    public static void main(String[] args) {
        int n = 100;
        boolean[] isPrime = new boolean[n + 1]; // false: 소수
        isPrime[0] = isPrime[1] = true;         // 0과 1은 제거
        for (int i = 2; i <= Math.sqrt(n); i++) {
            // 제거되지 않은 수 = 소수
            if (!isPrime[i]) {
                // 해당 수의 배수들은 합성수이므로 제거
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            // 소수만 출력
            if (!isPrime[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
        // 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 
    }
}
