public class MySinglyLinkedList {
    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        private Object data;
        private Node next;
        public Node(Object data) {
            this.data = data;
            this.next = null;
        }
        public String toString() {
            return String.valueOf(this.data);
        }
    }

    Node getNode(int index) {
        Node node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public void addFirst(Object data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        size++;
        if (head.next == null) {
            tail = head;
        }
    }
    public void addLast(Object data) {
        Node node = new Node(data);
        if (size == 0) {
            addFirst(data);
        } else {
            tail.next = node;
            tail = node;
            size++;
        }
    }
    public void add(int i, Object data) {
        if (i == 0) {
            addFirst(data);
        } else {
            Node prevNode = getNode(i - 1);
            Node nextNode = prevNode.next;
            Node node = new Node(data);
            prevNode.next = node;
            node.next = nextNode;
            size++;
            if (node.next == null) {
                tail = node;
            }
        }
    }
    public Object removeFirst() {
        Node node = head;
        head = head.next;
        Object removed = node.data;
        node = null;
        size--;
        return removed;
    }
    public Object remove(int i) {
        if (i == 0) {
            return removeFirst();
        }
        Node node = getNode(i - 1);
        Node deleted = node.next;
        node.next = deleted.next;
        Object deleteData = deleted.data;
        if (deleted == tail) {
            tail = node;
        }
        deleted = null;
        size--;
        return deleteData;
    }
    public Object removeLast() {
        return remove(size - 1);
    }
    public Object get(int i) {
        Node node = getNode(i);
        return node.data;
    }
    public int indexOf(Object data) {
        Node node = head;
        int index = 0;
        while (node.data != data) {
            node = node.next;
            index++;
            if (node == null) {
                return -1;
            }
        }
        return index;
    }
    public int size() {
        return size;
    }
    public String toString() {
        if (head == null) {
            return "[]";
        }
        Node node = head;
        StringBuilder sb = new StringBuilder("[");
        while (node.next != null) {
            sb.append(node.data);
            sb.append(",");
            node = node.next;
        }
        sb.append(node.data);
        sb.append("]");
        return sb.toString();
    }
    public static void main(String[] args) {
        MySinglyLinkedList numbers = new MySinglyLinkedList();
        numbers.addLast(10);                    // [10]
        numbers.addLast(20);                    // [10,20]
        numbers.addLast(30);                    // [10,20,30]
        numbers.removeFirst();                  // [20,30]
        numbers.addFirst(5);                    // [5,20,30]
        numbers.add(1, 15);                     // [5,15,20,30]
        numbers.indexOf(30);                    // 3
        numbers.remove(3);                      // [5,15,20]
        numbers.get(1);                         // 15
        System.out.println(numbers.toString()); // [5,15,20]
    }
}
