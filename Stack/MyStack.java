public class MyStack<E> {
    private Object[] elementData;
    private int capacity = 10;
    private int top;
  
    public MyStack() {
        this.elementData = new Object[capacity];
        this.top = -1;
    }

    public E push(E value) {
        if (isFull()) {
            resize();
        }
        top++;
        elementData[top] = value;
        return value;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E value = (E) elementData[top];
        elementData[top] = null;
        top--;
        resize();
        return value;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) elementData[top];
    }

    public int search(E value) {
        for (int i = top; i >= 0; i--) {
            if (elementData[i].equals(value)) {
                return top - i + 1;
            }
        }
        return -1;
    }

    public boolean isFull() {
        return top == elementData.length - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void resize() {
        int elementCount = elementData.length - 1;
        if (top == elementCount) {
            int newCapacity = elementData.length * 2;
            elementData = Arrays.copyOf(elementData, newCapacity);
            return;
        }
        if (top < (elementCount / 2)) {
            int newCapacity = elementData.length / 2;
            elementData = Arrays.copyOf(elementData, Math.max(newCapacity, capacity));
            return;
        }
    }

    public String toString() {
        return Arrays.toString(elementData);
    }

    public static void main(String[] args) {
        MyStack<String> stk = new MyStack<String>();
        stk.push("10"); // [10, null, null, null, null, null, null, null, null, null]
        stk.push("20"); // [10, 20, null, null, null, null, null, null, null, null]
        stk.push("30"); // [10, 20, 30, null, null, null, null, null, null, null]
        stk.push("40"); // [10, 20, 30, 40, null, null, null, null, null, null]
        stk.push("50"); // [10, 20, 30, 40, 50, null, null, null, null, null]

        stk.peek(); // 50
        stk.pop(); // [10, 20, 30, 40, null, null, null, null, null, null]
        stk.pop(); // [10, 20, 30, null, null, null, null, null, null, null]
        stk.pop(); // [10, 20, null, null, null, null, null, null, null, null]

        stk.search("20"); // 1
    }

}
