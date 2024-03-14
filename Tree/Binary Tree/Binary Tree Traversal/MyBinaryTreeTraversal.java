class Node {
    int data;
    Node left;
    Node right;
}

class Tree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public Node makeNode(Node left, int data, Node right) {
        Node node = new Node();
        node.data = data;
        node.left = left;
        node.right = right;
    }

    // 전위(Pre-Order) 순회
    // Root -> Left -> Right
    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " "); // 현재 노드 출력
            preOrderTraversal(node.left); // 왼쪽 서브트리 순회
            preOrderTraversal(node.right); // 오른쪽 서브트리 순회
        }
    }

    // 중위(In-Order) 순회
    // Left -> Root -> Right
    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left); // 왼쪽 서브트리 순회
            System.out.print(node.data + " "); // 현재 노드 출력 
            inOrderTraversal(node.right); // 오른쪽 서브트리 순회
        }
    }

    // 후위(Post-Order) 순회
    // Left -> Right -> Root
    public void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left); // 왼쪽 서브트리 순회
            postOrderTraversal(node.right); // 오른쪽 서브트리 순회
            System.out.print(node.data + " "); // 현재 노드 출력
        }
    }
}

public class MyBinaryTreeTraversal {

    public static void main(String[] args) {
        Tree tree = new Tree();
        Node n5 = tree.makeNode(null, 5, null);
        Node n4 = tree.makeNode(null, 4, null);
        Node n3 = tree.makeNode(null, 3, null);
        Node n2 = tree.makeNode(n4, 2, n5);
        Node n1 = tree.makeNode(n2, 1, n3);

        tree.setRoot(n1);
        
        // 전위 순회
        tree.preOrderTraversal(tree.getRoot()); // 1 2 4 5 3
        System.out.println();

        // 중위 순회
        tree.inOrderTraversal(tree.getRoot()); // 4 2 5 1 3
        System.out.println();

        // 후위 순회
        tree.postOrderTraversal(tree.getRoot()); // 4 5 2 3 1
    }
}

/*
 *       1      <- Root 
 *      / \         전위 순회 : 1 2 4 5 3
 *     2   3        중위 순회 : 4 2 5 1 3
 *    / \           후위 순회 : 4 5 2 3 1
 *   4   5
 * 
 */