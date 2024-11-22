public class MyPrimeFactorization {

    public static void main(String[] args) {
        // 소인수 분해하려는 수
        int n = 100;
        boolean[] isPrime = new boolean[n + 1];
        isPrime[0] = isPrime[1] = true;
        // 에라토스테네스의 체
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!isPrime[i]) {
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int num = 0; // 소수
        // 소인수 분해 = 소수로 나누기
        while (n > 1) {
            // 현재 숫자가 소수가 아니라면
            if (isPrime[num]) {
                num++; // 다음 숫자 확인
                continue;
            }
            // 소수로 나누어 떨어진다면
            if (n % num == 0) {
                sb.append(num).append(" ");
                n /= num; // 소수로 나누기
            // 그렇지 않다면
            } else {
                num++; // 다음 숫자 확인
            }
        }
        System.out.println(sb);
        // 2 2 5 5
    }
}
