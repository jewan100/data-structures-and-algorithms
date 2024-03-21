public class MyInsertionSort {

    // 삽입 정렬
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int value = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = value;
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

        // 삽입 정렬
        insertionSort(arr);

        // 정렬 후
        printArray(arr); // 1 3 4 7 12 16 25 33 39 48 56 57 65 68 72 79 80 85 90 97
    }
}