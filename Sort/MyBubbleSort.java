public class MyBubbleSort {

    // 버블 정렬
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < n - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {57, 90, 12, 33, 72, 56, 4, 25, 97, 39, 48, 65, 80, 7, 79, 68, 3, 16, 1, 85};
        // 정렬 전
        printArray(arr); // 57 90 12 33 72 56 4 25 97 39 48 65 80 7 79 68 3 16 1 85 
        
        // 버블 정렬
        bubbleSort(arr);

        // 정렬 후
        printArray(arr); // 1 3 4 7 12 16 25 33 39 48 56 57 65 68 72 79 80 85 90 97 
    }

}
