class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}


class BinarySearchTree {
    Node root;

    // 생성자
    public BinarySearchTree() {
        this.root = null;
    }

    // 이진 탐색 트리에 새로운 노드를 삽입
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // 새로운 노드를 삽입하는 재귀 호출
    public Node insertRec(Node root, int data) {

        // 트리가 비어있을 경우 새로운 노드 생성하여 반환
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // 노드를 삽입할 위치를 찾아서 왼쪽 또는 오른쪽 자식으로 이동
        // 값이 노드보다 작을 경우 -> 왼쪽
        // 값이 노드보다 클 경우 -> 오른쪽
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        // 변경된 루트 노드 반환
        return root;
    }

    // 이진 탐색 트리에 특정 값을 가지는 노드를 삭제
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // 지울 노드를 찾아 삭제하기 위한 재귀 호출
    public Node deleteRec(Node root, int data) {

        // 빈 트리인 경우 루트 반환
        if (root == null) {
            return root;
        }

        // 삭제할 노드를 찾기 위해 왼쪽 또는 오른쪽 자식으로 이동
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // 삭제할 노드를 발견한 경우

            // 자식이 없거나 하나만 있는 경우 -> Leaf Node
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // 두 개의 자식이 모두 있는 경우
            root.data = minValue(root.right);

            // 오른쪽 서브트리에서 중위 후속자를 찾아서 삭제
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    // 주어진 서브트리의 가장 작은 값을 반환
    private int minValue(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    public void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }
}


public class MyBinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // 이진 탐색 트리에 노드 삽입
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.insert(10);

/*
 *        50
 *       /  \
 *      30   70
 *     / \   / \
 *    20 40 60 80
 *   /
 *  10
 * 
 */

        System.out.println("이진 탐색 트리 중위 순회 결과 :");
        tree.inOrderTraversal(tree.root); // 10 20 30 40 50 60 70 80

        // 이진 탐색 트리에서 노드 삭제
        tree.delete(20);

/*
 *      50
 *     /  \
 *    30   70
 *   / \   / \
 *  10 40 60 80
 * 
 */
        tree.delete(30);
/*
 *      50
 *     /  \
 *    40   70
 *   /    / \
 *  10   60 80
 * 
 */
        tree.delete(50);
/*
 *      60
 *     /  \
 *    40   70
 *   /      \
 *  10       80
 * 
 */
        System.out.println("\n삭제 후 중위 순회 결과 :");
        tree.inOrderTraversal(tree.root); // 10 40 60 70 80
    }
}