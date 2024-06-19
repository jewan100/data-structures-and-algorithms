public class MyArrayList {
    private Object[] elementData = new Object[100]; // 초기 배열의 크기 할당
    private int size = 0;
    public boolean addLast(Object element) {
        elementData[size] = element;
        size++;
        return true;
    }
    public boolean add(int index, Object element) {
        for (int i = size - 1; i >= index; i--) { // 데이터를 중간에 삽입하는 경우, 그 뒤의 데이터들의 인덱스를 전부 변경
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = element;
        size++;
        return true;
    }
    public boolean addFirst(Object element) {
        return (add(0, element));
    }
    public Object remove(int index) {
        Object removed = elementData[index];
        for (int i = index + 1; i < size; i++) {
            elementData[i - 1] = elementData[i];
        }
        size--;
        elementData[size] = null;
        return removed;
    }
    public Object removeFirst() {
        return remove(0);
    }
    public Object removeLast() {
        return remove(size - 1);
    }
    public Object get(int index) {
        return elementData[index];
    }
    public int size() {
        return size;
    }
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
  
    public static void main(String[] args) {
        MyArrayList numbers = new MyArrayList();
        numbers.addLast(10);                     // [10]
        numbers.addLast(20);                     // [10,20]
        numbers.addLast(30);                     // [10,20,30]
        numbers.addLast(40);                     // [10,20,30,40]
        numbers.addLast(50);                     // [10,20,30,40,50]
        numbers.add(3, 35);                      // [10,20,30,35,40,50]
        numbers.add(5, 45);                      // [10,20,30,35,40,45,50]
        numbers.addFirst(5);                     // [5,10,20,30,35,40,45,50]
        System.out.println(numbers.indexOf(45)); // 6
        numbers.remove(6);                       // [5,10,20,30,35,40,50]
        System.out.println(numbers.toString());  // [5,10,20,30,35,40,50]
    }
}
