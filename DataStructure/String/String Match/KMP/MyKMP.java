public class MyKMP {

    static int[] table; // 접두사와 접미사가 일치하는 길이를 저장하는 배열

    public static void main(String[] args) {
        String str = "ABC ABCDAB ABCDABCDABDE";
        String target = "ABCDABD";
        KMP(str, target);
    }

    public static void KMP(String str, String target) {
        table = makeTable(target); // 매칭에 사용될 테이블 생성
        int strLen = str.length(), targetLen = target.length();
        int i = 0; // 대상 문자열에서 현재 비교할 위치 인덱스
        
        // 매칭 시작
        for (int j = 0; j < strLen; j++) {
         // 일치하지 않을 경우, 테이블 값을 이용해 건너뛸 위치를 계산
            while (i > 0 && str.charAt(j) != target.charAt(i))
                i = table[i - 1]; // 건너뛰기
            // 일치할 경우
            if (str.charAt(j) == target.charAt(i)) {
                // 전부 일치
                if (i == targetLen - 1) {
                    System.out.println(j - targetLen + 2 + "에서 발견!");
                    i = table[i];
                } else
                    i++; // 다음 인덱스 확인
            }
        }
    }

    public static int[] makeTable(String target) {
        int n = target.length();
        int[] table = new int[n];
        int i = 0;
        // 접두사와 접미사가 일치하는 길이를 테이블에 기록
        for (int j = 1; j < n; j++) {
            // 일치하지 않을 경우, 이전 접두사 길이로 이동
            while (i > 0 && target.charAt(i) != target.charAt(j))
                i = table[i - 1];
            // 접두사와 접미사가 일치하는 경우
            if (target.charAt(i) == target.charAt(j)) {
                table[j] = ++i; // 테이블에 일치 길이 저장
            }
        }
        return table;
    }
}
