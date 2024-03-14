// 트리의 각 노드를 나타내기 위한 클래스
class Node<T> {
    private T data; // 노드의 데이터
    private List<Node<T>> children; // 자식 노드들의 리스트 -> 차수
    private Node<T> parent; // 부모 노드의 참조

    // 생성자
    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    // 데이터 반환
    public T getData() {
        return data;
    }

    // 부모 노드 반환
    public Node<T> getParent() {
        return parent;
    }

    // 부모 노드 추가
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    // 자식 노드 추가
    public void addChild(Node<T> child) {
        
        // 추가할 자식 노드의 부모를 현재 노드의 참조 값으로 설정
        child.setParent(child);

        // 부모 노드의 자식 노드 리스트에 추가
        children.add(child);
    }

    // 자식 노드들 반환
    public List<Node<T>> getChildren() {
        return children;
    }
}

// 일반적인 트리 클래스
class GeneralTree<T> {
    private Node<T> root; // 루트 노드

    // 루트 노드 설정
    public void setRoot(Node<T> root) {
        this.root = root;
    }

    // 루트 노드 반환
    public Node<T> getRoot() {
        return root;
    }
}

public class MyGeneralTree {

    public static void main(String[] args) {
        
        // 트리 생성
        GeneralTree<String> tree = new GeneralTree<>();

        // 노드 생성
        Node<String> rootNode = new Node<>("Root");
        Node<String> child1 = new Node<>("Child 1");
        Node<String> child2 = new Node<>("Child 2");
        Node<String> child3 = new Node<>("Child 3");
        Node<String> child4 = new Node<>("Child 4");
        Node<String> child5 = new Node<>("Child 5");

        // 노드 연결 -> 엣지
        rootNode.addChild(child1);
        rootNode.addChild(child2);
        child1.addChild(child3);
        child1.addChild(child4);
        child2.addChild(child5);
    }
}

/*
 *      root
 *     /    \
 *    1      2
 *   / \    /
 *  3   4  5
 * 
 */