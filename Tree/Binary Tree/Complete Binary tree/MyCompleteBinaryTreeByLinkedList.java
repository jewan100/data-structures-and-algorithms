import java.util.Queue;
import java.util.LinkedList;

class Node {
    int data;
    Node left;
    Node right;

    // 생성자
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class CompleteBinaryTree {
    Node root;

    // 트리 생성자
    public CompleteBinaryTree() {
        root = null;
    }

    // 새로운 노드를 트리에 삽입
    // Leaf의 깊이 이전까지는 Perfect Binary Tree를 유지해야함
    public void insert(int data) {
        // 트리가 비어있는 경우, 새로운 노드를 루트로 설정
        if (root == null) {
            root = new Node(data);
            return;
        }

        // BFS를 위한 큐 생성
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // BFS를 이용하여 Perfect Binary Tree를 유지하며
        // 값을 넣을 자리를 찾아 노드를 삽입
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 왼쪽 자식이 없는 경우 왼쪽 자식으로 삽입
            if (node.left == null) {
                node.left = new Node(data);
                break;
            } 
            // 오른쪽 자식이 없는 경우 오른쪽 자식으로 삽입
            else if (node.right == null) {
                node.right = new Node(data);
                break;
            } 
            // 자식이 모두 있는 경우, 다음 레벨로 이동
            else {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
    }

    // 중위 순회 (Left -> Root -> Right)
    public void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.print(node.data + " ");
        inOrderTraversal(node.right);
    }
}

public class MyCompleteBinaryTreeByLinkedList {

    public static void main(String[] args) {
        CompleteBinaryTree tree = new CompleteBinaryTree();
        tree.insert(3);
        tree.insert(5);
        tree.insert(1);
        tree.insert(4);
        tree.insert(2);

        tree.inOrderTraversal(tree.root); // 4 5 2 3 1 
    }
}
