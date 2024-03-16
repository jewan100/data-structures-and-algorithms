class CompleteBinaryTree {
    private int[] treeArray;
    private int size;

    // 생성자
    public CompleteBinaryTree(int capacity) {
        this.treeArray = new int[capacity];
        this.size = 0;
    }

    // 노드 추가
    public void add(int value) {

        // 사이즈가 인덱스의 역할을 함
        if (size < treeArray.length) {
            treeArray[size++] = value;
        } else {
            System.out.println("트리가 가득 찼습니다.");
        }
    }

    // 트리의 크기를 반환
    public int getSize() {
        return this.size;
    }

    // 특정 인덱스에 해당하는 노드의 레벨(깊이)를 반환
    public int getLevel(int index) {
        int level = 0;
        int nodesInLevel = 1;

        // 한 레벨마다 2배씩 늘어남
        while (index >= nodesInLevel) {
            level++;
            nodesInLevel *= 2;
        }
        return level;
    }

    // 특정 인덱스에 해당하는 노드의 레벨과 값 반환
    public String getNodeInfo(int index) {
        if (index >= 0 && index < size) {
            int level = getLevel(index);
            int value = treeArray[index];
            return "인덱스 " + index + "의 레벨: " + level + ", 값: " + value;
        } else {
            return "유효하지 않은 인덱스입니다.";
        }
    }
}


public class MyCompleteBinaryTreeByArray {

    public static void main(String[] args) {
        CompleteBinaryTree tree = new CompleteBinaryTree(10);
        tree.add(10);
        tree.add(34);
        tree.add(21);
        tree.add(64);
        tree.add(12);
        tree.add(45);
        tree.add(95);
        tree.add(6);

        for (int i = 0; i < tree.getSize(); i++) {
            System.out.println(tree.getNodeInfo(i));
        }
    }
}
/*
 * 인덱스 0의 레벨: 0, 값: 10
 * 인덱스 1의 레벨: 1, 값: 34
 * 인덱스 2의 레벨: 2, 값: 21
 * 인덱스 3의 레벨: 2, 값: 64
 * 인덱스 4의 레벨: 3, 값: 12
 * 인덱스 5의 레벨: 3, 값: 45
 * 인덱스 6의 레벨: 3, 값: 95
 * 인덱스 7의 레벨: 3, 값: 6
 */
