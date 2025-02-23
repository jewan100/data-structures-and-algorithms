class SegmentTree {
    private int[] tree;
    private int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[n << 2];
        build(arr, 1, 0, n - 1);
    }

    private void build(int[] arr, int idx, int s, int e) {
        if (s == e)
            tree[idx] = arr[s];
        else {
            int m = (s + e) >> 1;
            build(arr, idx << 1, s, m); // 왼쪽
            build(arr, (idx << 1) + 1, m + 1, e); // 오른쪽
            tree[idx] = tree[idx << 1] + tree[(idx << 1) + 1];// 합
        }
    }

    public int query(int l, int r) {
        return query(1, 0, n - 1, l, r);
    }

    /**
     * 재귀적으로 구간 합 쿼리를 수행하는 메소드
     * 
     * @param idx 현재 트리 노드 인덱스
     * @param s   현재 노드가 담당하는 구간의 시작 인덱스
     * @param e   현재 노드가 담당하는 구간의 끝 인덱스
     * @param l   찾고자 하는 구간의 시작 인덱스
     * @param r   찾고자 하는 구간의 끝 인덱스
     * @return 구간 [l, r]의 합
     */
    private int query(int idx, int s, int e, int l, int r) {
        if (r < s || e < l)
            return 0;
        if (l <= s && e <= r)
            return tree[idx];
        int m = (s + e) >> 1;
        int sumL = query(idx << 1, s, m, l, r);
        int sumR = query((idx << 1) + 1, m + 1, e, l, r);
        return sumL + sumR;
    }

    public void update(int target, int newValue) {
        update(1, 0, n - 1, target, newValue);
    }

    /**
     * 재귀적으로 세그먼트 트리의 값을 업데이트하는 메소드
     * 
     * @param idx      현재 트리 노드 인덱스
     * @param s        현재 노드가 담당하는 구간의 시작 인덱스
     * @param e        현재 노드가 담당하는 구간의 끝 인덱스
     * @param target   업데이트할 원본 배열의 인덱스
     * @param newValue 새로운 값
     */
    private void update(int idx, int s, int e, int target, int newValue) {
        if (s == e)
            tree[idx] = newValue;
        else {
            int m = (s + e) / 2;
            if (target <= m)
                update(idx << 1, s, m, target, newValue);
            else
                update((idx << 1) + 1, m + 1, e, target, newValue);
            tree[idx] = tree[idx << 1] + tree[(idx << 1) + 1];
        }
    }
}

public class MySegmentTree {
    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11 };
        SegmentTree segTree = new SegmentTree(arr);
        // 구간 합 쿼리 예제: 인덱스 1부터 3까지의 합 (3 + 5 + 7 = 15)
        System.out.println("구간 [1, 3]의 합: " + segTree.query(1, 3));

        // 인덱스 1의 값을 10으로 업데이트 (기존 값 3 -> 10)
        segTree.update(1, 10);

        // 업데이트 후 구간 합 쿼리 예제: 인덱스 1부터 3까지의 합 (10 + 5 + 7 = 22)
        System.out.println("업데이트 후 구간 [1, 3]의 합: " + segTree.query(1, 3));

    }
}