class LazySegmentTree {

	private final int[] arr;
	private final int[] tree;
	private final int[] lazy;
	private final int n;

	public LazySegmentTree(int[] arr) {
		this.arr = arr;
		n = arr.length;
		tree = new int[n << 2];
		lazy = new int[n << 2];
		build(1, 0, n - 1);
	}

	private void build(int idx, int s, int e) {
		if (s == e) {
			tree[idx] = arr[s];
			return;
		}
		int m = (s + e) >> 1;
		build(idx << 1, s, m);
		build((idx << 1) + 1, m + 1, e);
		tree[idx] = tree[idx << 1] + tree[(idx << 1) + 1];
	}

	public void update(int l, int r, int val) {
		update(1, 0, n - 1, l, r, val);
	}

	private void update(int idx, int s, int e, int l, int r, int val) {

		// 이전에 밀린 lazy값을 처리
		if (lazy[idx] != 0) {
			tree[idx] += (e - s + 1) * lazy[idx]; // 현재 구간에 누적 반영
			if (s != e) { // 리프 노드가 아닐 경우 자식에게 lazy 전달
				lazy[idx << 1] += lazy[idx];
				lazy[(idx << 1) + 1] += lazy[idx];
			}
			lazy[idx] = 0; // 처리 완료
		}

		// 범위 밖
		if (r < s || e < l)
			return;

		// 완전히 포함된 구간
		// [s, e] 노드 구간이 질의/업데이트 구간 [l, r]에 완전히 포함되면
		if (l <= s && e <= r) {
			// 1) 현재 노드가 담당하는 합계를 "즉시" 갱신
			tree[idx] += (e - s + 1) * val;

			// 2) 자식에게는 "지연(lazy) 표식"만 남기고 실제 값 갱신은 미룸
			if (s != e) { // 리프가 아니면
				lazy[idx << 1] += val; // 왼쪽 자식에게 '이만큼 더해야 함'을 표시
				lazy[(idx << 1) + 1] += val; // 오른쪽 자식도 동일
			}

			// 3) 더 내려가지 않고 반환
			return;
		}

		// 걸친 구간
		int m = (s + e) >> 1;
		update(idx << 1, s, m, l, r, val);
		update((idx << 1) + 1, m + 1, e, l, r, val);
		tree[idx] = tree[idx << 1] + tree[(idx << 1) + 1];
	}

	public int query(int l, int r) {
		return query(1, 0, n - 1, l, r);
	}

	private int query(int idx, int s, int e, int l, int r) {

		// 밀린 lazy 값 처리
		if (lazy[idx] != 0) {
			tree[idx] += (e - s + 1) * lazy[idx];
			if (s != e) {
				lazy[idx << 1] += lazy[idx];
				lazy[(idx << 1) + 1] += lazy[idx];
			}
			lazy[idx] = 0;
		}

		// 범위 밖
		if (r < s || e < l)
			return 0;

		// 완전히 포함
		if (l <= s && e <= r)
			return tree[idx];

		int m = (s + e) >> 1;
		int sumL = query(idx << 1, s, m, l, r);
		int sumR = query((idx << 1) + 1, m + 1, e, l, r);
		return sumL + sumR;
	}
}

public class MyLazySegmentTree {

	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 7, 9, 11};
		LazySegmentTree segTree = new LazySegmentTree(arr);

		// 초기 구간 합: [1,3] → 3 + 5 + 7 = 15
		System.out.println("초기 구간 [1,3] 합: " + segTree.query(1, 3));

		// 구간 [1,3]에 +2씩 더하기
		segTree.update(1, 3, 2);
		System.out.println("구간 [1,3] +2 후 합: " + segTree.query(1, 3));

		// 구간 [0,5] 전체 합 확인
		System.out.println("전체 구간 [0,5] 합: " + segTree.query(0, 5));
	}
}
