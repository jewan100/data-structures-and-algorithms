public class MyPalindromeCheck {

    // 팰린드롬 확인
    // 팰린드롬 : 앞으로 읽으나 뒤로 읽으나 동일한 문자열
    public static boolean isPalindrome(String str) {
        
        // 전달 받은 문자열의 알파벳이나 숫자가 아닌 문자 제거
        str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

        // 투 포인터를 사용하여 팰린드롬 여부를 확인
        int l = 0 ;
        int r = str.length();

        while (l < r) {
            
            // 전달 받은 문자열이 팰린드롬이 아닐 경우
            if (str.charAt(l) != str.charAt(right)) {
                return false;
            }

            // 포인터들의 위치의 문자들이 동일하다면 포인터 이동
            l++; // 왼쪽 포인터를 오른쪽으로 이동
            r--; // 오른쪽 포인터를 왼쪽으로 이동
        }

        // 모든 문자가 일치한다면 팰린드롬
        return true;
    }

    public static void main(String[] args) {
        String testStr = "A man, a plan, a canal, Panama!";
        System.out.println("Is \"" + testString + "\" a palindrome? " + isPalindrome(testString));
        // TRUE
    }
}