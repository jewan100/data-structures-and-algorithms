import java.util.List;
import java.util.ArrayList;

public class MyMancherAlgorithm {

    public static List<String> findAllPalindrome(String str) {
        
        Stirng modifiedString = addSpecialChars(str);

        int len = modifiedString.length();
        int[] palLength = new int[len];
        int center = 0;
        int rightEdge = 0;
        List<String> palindromes = new ArrayList<>();

        for (int i = 1; i < len - 1; i++) {
            if (i < rightEdge) {
                int mirroredIndex = 2 * center - i;
                palLength[i] = Math.min(rightEdge - i, palLength[mirroredIndex]);
            }

            int left = i - palLength[i] - 1;
            int right = i + palLength[i] + 1;
            while (0 <= left && right < len &&
                modifiedString.charAt(left) == modifiedString.charAt(right)){
                    palLength[i]++;
                    left--;
                    right++;
            }

            if (i + palLength[i] > rightEdge) {
                center = i;
                rightEdge = i + palLength[i];
            }
        }

        for (int i = 1; i < len - 1; i++) {
            if (palLength[i] > 0) {
                String palindrome = modifiedString.substring(i - palLength[i], i + palLength[i] + 1).replaceAll("#", "");
                palindromes.add(palindrome);
            }
        }

        return palindromes;
    }

    public static String addSpecialChars(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (char c : str.toCharArray) {
            sb.append(c);
            sb.append("#");
        }
    }


    public static void main(String[] args) {
        String testStr = "abcbabcbabcba";
        System.out.println(findAllPalindrome(testStr));
        // [a, b, abcba, b, abcbabcba, b, abcbabcbabcba, b, abcbabcba, b, abcba, b, a]
    }
}