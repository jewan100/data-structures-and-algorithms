import java.util.List;
import java.util.ArrayList;

public class MyMancherAlgorithm {

    public static List<String> findAllPalindrome(String str) {
        
        // 매내처 알고리즘을 사용하기 위해 특수문자를 삽입한 문자열
        String modifiedString = addSpecialChars(str);
        int len = modifiedString.length();

        // 해당 인덱스를 중심으로 팰린드롬의 반지름을 저장할 배열
        int[] palLength = new int[len];
        
        // 마지막으로 검출한 팰린드롬의 중심 인덱스
        int center = 0;
        // 마지막으로 검출한 팰린드롬의 오른쪽 끝 인덱스 => 팰린드롬의 반지름
        int rightEdge = 0;

        // 팰린드롬을 저장할 List배열
        List<String> palindromes = new ArrayList<>();

        for (int i = 1; i < len - 1; i++) {

            // 검출하고자 하는 인덱스가 마지막으로 검출한 팰린드롬 안에 속해있을 경우
            if (i < rightEdge) {
                // 팰린드롬의 중심 인덱스를 기준으로 반대로 대챙인 점의 인덱스
                // 예를들어, 2--6--10
                // 6이 중심이고 i = 10인 경우, 반대 인덱스 -> 2
                int mirroredIndex = 2 * center - i;
                // 이미 검출된 팰린드롬이 중심을 기준으로 좌우 대칭이기 때문에
                // 기존에 계산이 완료된 palLength[mirroredIndex]의 값을 다음 계산에 참고할 수 있음.
                // 최소값을 가져오는 이유는, 현재 i 기준 오른쪽으로의 팰린드롬이 얼마나 확장될지 알 수 없기에
                // 최소값을 가져와서 갱신 -> 검출되어있는 팰린드롬 반지름 안의 값을 가지게 됨
                palLength[i] = Math.min(rightEdge - i, palLength[mirroredIndex]);
            }
            // i = 확인하려고 하는 팰린드롬의 중심 인덱스
            // palLength[i] = 검사한 팰린드롬의 반지름
            // left = 왼쪽 끝 문자 인덱스
            // right = 오른쪽 끝 문자 인덱스
            int left = i - palLength[i] - 1;
            int right = i + palLength[i] + 1;
            while (0 <= left && right < len &&
                modifiedString.charAt(left) == modifiedString.charAt(right)){
                    // 팰린드롬을 확장
                    // left와 right의 문자가 같다면 팰린드롬 반지름 증가
                    palLength[i]++;
                    left--;
                    right++;
            }

            // 기존의 팰린드롬에 포함되지 않는 새로운 팰린드롬이 검출되었을 경우 
            if (i + palLength[i] > rightEdge) {
                // 새로 검출된 중심, 팰린드롬 반지름을 갱신
                center = i;
                rightEdge = i + palLength[i];
            }
        }

        // 찾은 팰린드롬들을 문자열로 반환하여 리스트에 추가
        for (int i = 1; i < len - 1; i++) {
            // 검출된 팰린드롬
            if (palLength[i] > 0) {
                // 특수문자를 포함한 문자열에서 특수문자를 제거
                String palindrome = modifiedString.substring(i - palLength[i], i + palLength[i] + 1).replaceAll("#", "");
                // 팰린드롬을 리스트에 추가
                palindromes.add(palindrome);
            }
        }

        return palindromes;
    }

    // 짝수 문자열도 팰린드롬 검사를 하기 위해 문자열 사이사이에 #을 삽입
    public static String addSpecialChars(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (char c : str.toCharArray()) {
            sb.append(c);
            sb.append("#");
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        String testStr = "abcbabcbabcba";
        System.out.println(findAllPalindrome(testStr));
        // [a, b, abcba, b, abcbabcba, b, abcbabcbabcba, b, abcbabcba, b, abcba, b, a]
    }
    
}