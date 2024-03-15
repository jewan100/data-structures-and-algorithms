class Node {

    int data;
    Node left;
    Node right;

    // 노드 생성자
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {

    Node root;

    // 이진 트리 생성자
    public BinaryTree() {
        root = null;
    }

    // 이진 트리에 값을 삽입
    public void insert(int data) {
        
        // 삽입할 위치를 찾기 위해 재귀 호출
        root = insertRec(root, data);
    }

    // 이진 트리에 값을 삽입할 위치를 찾기 위한 재귀 함수
    private Node insertRec(Node root, int data) {

        // 현재 노드가 비어있으면 새로운 노드를 생성 후 반환 -> 값 삽입
        if(root == null) {
            root = new Node(data);
            return root;
        }

        // 이진 트리를 유지하기 위해 삽입 위치를 찾아 감
        if (root.left == null) {
            // 왼쪽이 비어있으면 왼쪽에 값을 삽입
            root.left = insertRec(root.left, data);
        } else if (root.right == null) {
            // 오른쪽이 비어있으면 오른쪽에 값을 삽입
            root.right = insertRec(root.right, data);
        } else {
            // 왼쪽과 오른쪽 둘 다 차 있으면 무작위로 왼쪽이나 오른쪽에 값을 삽입
            if (Math.random() < 0.5) { // 50% 확률로 왼쪽으로 값 삽입
                root.left = insertRec(root.left, data);
            } else { // 나머지 50% 확률로 오른쪽으로 값 삽입
                root.right = insertRec(root.right, data);
            }
        }
        return root;
    }

    // 중위 순회를 통해 결과 출력
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    // 중위 순회를 재귀적으로 수행
    private void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }
}

public class MyBinaryTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        tree.inOrderTraversal();
    }
}

/*
 *      50
 *     /  \
 *    30   20
 *         / \
 *        40 70
 *           / \
 *          60 80
 */

/*
 *      50
 *     /  \
 *    30   40
 *   / \   /
 *  20 60 70
 *          \
 *           80
 */

/*
 *      50
 *     /  \
 *    30   70
 *   / \   / \
 *  20 40 60 80
 * 
 */

