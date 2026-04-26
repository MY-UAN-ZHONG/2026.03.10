public class Assignment4 {

    // ==========================================
    // 1. Merge Sort (合併排序)
    // ==========================================
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // 找到中間點，將陣列切成兩半
            int mid = left + (right - left) / 2;

            // 分別排序兩半
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // 合併兩半
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) { arr[k] = L[i]; i++; }
            else { arr[k] = R[j]; j++; }
            k++;
        }
        while (i < n1) { arr[k] = L[i]; i++; k++; }
        while (j < n2) { arr[k] = R[j]; j++; k++; }
    }

    // ==========================================
    // 2. Binary Search (二元搜尋) 
    // ==========================================
    public static int binarySearch(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) return mid;
            
            // 如果目標比中間值小，往左半邊找
            if (arr[mid] > x) return binarySearch(arr, left, mid - 1, x);
            
            // 如果目標比中間值大，往右半邊找
            return binarySearch(arr, mid + 1, right, x);
        }
        return -1;
    }

    // ==========================================
    // 主程式
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Assignment 4: Divide and Conquer ===");

        // 測試 1: Merge Sort (使用投影片 P.32 數據) 
        int[] arrMerge = {8, 3, 5, 2, 9, 1};
        System.out.print("Original Array (Merge Sort): ");
        printArray(arrMerge);
        mergeSort(arrMerge, 0, arrMerge.length - 1);
        System.out.print("Sorted Array (Merge Sort):   ");
        printArray(arrMerge);

        // 測試 2: Binary Search (使用投影片 P.30 數據) 
        int[] arrBinary = {2, 3, 6, 8, 10, 11, 16, 18, 22, 27, 30};
        int target = 11;
        System.out.println("\nArray for Binary Search: 2 3 6 8 10 11 16 18 22 27 30");
        int result = binarySearch(arrBinary, 0, arrBinary.length - 1, target);
        System.out.println("Target " + target + " found at index: " + result);

        // 輸出遞迴關係式 (Recurrence) [cite: 485]
        System.out.println("\n--- Recurrence Relations ---");
        System.out.println("Merge Sort:    T(n) = 2T(n/2) + n");
        System.out.println("Binary Search: T(n) = T(n/2) + 1");
        System.out.println("========================================");
    }

    public static void printArray(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }
}