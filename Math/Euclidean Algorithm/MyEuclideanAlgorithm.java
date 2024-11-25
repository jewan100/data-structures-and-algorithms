public class MyEuclideanAlgorithm {

    public static void main(String[] args) {
        int a = 1024;
        int b = 48;
        int GCD = gcd(a, b);
        System.out.println(GCD); // 16
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
