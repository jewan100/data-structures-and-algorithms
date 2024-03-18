class MaxHeap{
    private int[] heapArray;
    private maxSize;
    private currentSize;

    public MaxHeap(int maxSize) {
        this.heapArray = new int[maxSize];
        this.maxSize = maxSize;
        this.currentSize = 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2; // 완전 이진 트리에서 부모 노드의 인덱스
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1; // 
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    public void insert(int data) {
        // 트리가 꽉 찼을 경우
        if(currentSize == maxSize) {
            System.out.println("Heap is Full");
            return;
        }
        
        heapArray[currentSize] = data; // 힙의 마지막에 새로운 요소를 임시로 추가
        heapifyUp(currentSize); // 새로운 요소를 올바른 위치로 이동
        currentSize++; // 노드가 추가되었으므로 현재 사이즈 증가
    }

    // 큰 값이 위로 올라가도록 하는 연산
    private void heapifyUp(int index) {
        int parentIndex = getParentIndex(index);
        int newData = heapArray[index];

        // 새로운 요소가 부모 노드보다 크다면
        while (index > 0 && heapArray[parentIndex] < newData) {
            heapArray[index] = heapArray[parentIndex]; // 부모를 아래로 이동
            index = parentIndex;
            parentIndex = getParentIndex(index); // 다음 부모를 확인
        }

        // 부모가 자신보다 작다면 그 자리에 추가
        heapArray[index] = newData;
    }

    // 반환 처리에서 루트로 올린 값을 아래로 내려가도록 하기 위한 연산 (힙 속성을 복원)
    private void heapifyDown(int index) {
        int largerChildIndex;
        int top = heapArray[index];

        // Leaf가 아닐 경우
        while (index < currentSize / 2) { 
            int leftChildIndex = getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);

            // 오른쪽 자식이 현재 힙의 크기를 벗어나지 않고, 오른쪽 자식의 값이 왼쪽 자식의 값보다 큰 경우
            if (rightChildIndex < currentSize && heapArray[leftChildIndex] < heapArray[rightChildIndex]) {
                largerChildIndex = rightChildIndex;
            } else {
                largerChildIndex = leftChildIndex;
            }

            // 현재 노드의 값이 선택한 자식의 값보다 크거나 같으면 반복문 종료
            if (top >= heapArray[largerChildIndex]) {
                break;
            }

            // 현재 노드의 값이 선택한 자식의 값보다 크다면 선택한 자식의 값을 현재 위치로 이동
            heapArray[index] = heapArray[largerChildIndex];
            index = largerChildIndex; // 현재 인덱스를 선택한 자식의 인덱스로 설정
        }
        heapArray[index] = top; // 최종적으로 현재 위치에 저장되어야 할 값을 설정
    }

    // 힙에서 최대값을 추출하여 반환
    public int extractMin() {
        if (currentSize == 0) {
            System.out.println("Heap is Empty");
            return -1;
        }

        int max = heapArray[0]; // 최대값은 루트
        heapArray[0] = heapArray[currentSize - 1]; // 힙의 마지막 요소를 루트로 이동
        currentSize--;
        heapifyDown(0); // 이동 시킨 요소를 올바른 위치로 이동
        return max;
    }

    // 힙에서 최대값을 반환
    public int peek() {
        if (currentSize == 0) {
            System.out.println("Heap is Empty");
            return -1;
        }
        return heapArray[0];
    }
}

public class MyMaxHeap {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(12);
        maxHeap.insert(23);
        maxHeap.insert(64);
        maxHeap.insert(85);
        maxHeap.insert(34);
        maxHeap.insert(9);
        maxHeap.insert(54);
        maxHeap.insert(92);
    }
}