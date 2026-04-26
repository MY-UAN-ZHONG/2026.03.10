class Job {
    String name;
    int start;
    int finish;
    int value;

    public Job(String name, int start, int finish, int value) {
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.value = value;
    }
}

public class Assignment1 {

    // ==========================================
    // 1. 手動實作 Quick Sort (依結束時間排序)
    // ==========================================
    public static void sortJobsByFinishTime(Job[] jobs, int low, int high) {
        if (low < high) {
            int pi = partition(jobs, low, high);
            sortJobsByFinishTime(jobs, low, pi - 1);
            sortJobsByFinishTime(jobs, pi + 1, high);
        }
    }

    private static int partition(Job[] jobs, int low, int high) {
        int pivot = jobs[high].finish;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (jobs[j].finish <= pivot) {
                i++;
                Job temp = jobs[i];
                jobs[i] = jobs[j];
                jobs[j] = temp;
            }
        }
        Job temp = jobs[i + 1];
        jobs[i + 1] = jobs[high];
        jobs[high] = temp;
        return i + 1;
    }

    // ==========================================
    // 2. 二元搜尋 p(j): 找出在活動 j 開始前，最後一個結束的活動 index
    // ==========================================
    public static int binarySearch(Job[] jobs, int index) {
        int low = 0, high = index - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (jobs[mid].finish <= jobs[index].start) {
                if (jobs[mid + 1].finish <= jobs[index].start) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // ==========================================
    // 3. Dynamic Programming 主邏輯
    // ==========================================
    public static void solveWeightedIntervalScheduling(Job[] jobs) {
        int n = jobs.length;
        // 步驟 1: 依 finish time 排序 (時間複雜度 O(n log n))
        sortJobsByFinishTime(jobs, 0, n - 1);

        // dp[i] 儲存前 i 個活動的最大價值
        int[] dp = new int[n];
        dp[0] = jobs[0].value;

        // 步驟 2: 建立 DP 陣列 (時間複雜度 O(n))
        for (int i = 1; i < n; i++) {
            int inclProf = jobs[i].value;
            int l = binarySearch(jobs, i);
            if (l != -1) {
                inclProf += dp[l];
            }
            // OPT(j) = max(value_j + OPT(p(j)), OPT(j-1))
            dp[i] = Math.max(inclProf, dp[i - 1]);
        }

        System.out.println("Maximum total value (最大總價值): " + dp[n - 1]);
    }

    // ==========================================
    // 主程式
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Assignment 1: Weighted Interval Scheduling ===");
        
        // 投影片第 8 頁的活動數據
        Job[] jobs = {
            new Job("A", 1, 4, 5),
            new Job("B", 3, 5, 1),
            new Job("C", 0, 6, 8),
            new Job("D", 4, 7, 4),
            new Job("E", 3, 8, 6),
            new Job("F", 5, 9, 3),
            new Job("G", 6, 10, 2),
            new Job("H", 8, 11, 4)
        };

        solveWeightedIntervalScheduling(jobs);
        
        System.out.println("--------------------------------------------------");
        System.out.println("Time Complexity Analysis:");
        System.out.println("1. Sorting by finish time: O(n log n)");
        System.out.println("2. DP formulation with Binary Search: O(n)");
        System.out.println("=> Total Time Complexity: O(n log n)");
    }
}