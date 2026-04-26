// 霍夫曼樹的節點定義
class HuffmanNode {
    char data;
    int freq;
    HuffmanNode left, right;

    public HuffmanNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }
}

// 手動實作 Min-Heap (最小堆積) 來取代內建的 PriorityQueue
class MinHeap {
    private HuffmanNode[] heap;
    private int size;

    public MinHeap(int capacity) {
        heap = new HuffmanNode[capacity];
        size = 0;
    }

    public int getSize() { return size; }

    // 插入節點
    public void insert(HuffmanNode node) {
        int current = size;
        heap[size++] = node;
        while (current != 0 && heap[parent(current)].freq > heap[current].freq) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // 取出最小頻率的節點
    public HuffmanNode extractMin() {
        if (size == 0) return null;
        if (size == 1) { size--; return heap[0]; }
        
        HuffmanNode root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        minHeapify(0);
        return root;
    }

    private void minHeapify(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && heap[left].freq < heap[smallest].freq) smallest = left;
        if (right < size && heap[right].freq < heap[smallest].freq) smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private int parent(int i) { return (i - 1) / 2; }

    private void swap(int i, int j) {
        HuffmanNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

public class Assignment2 {

    // 遞迴印出霍夫曼編碼 (向左為 0，向右為 1)
    public static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) return;

        // 如果是葉節點 (沒有左右子節點)，則印出字元與其編碼
        if (root.left == null && root.right == null) {
            System.out.println(root.data + " | 頻率: " + root.freq + " | 編碼: " + code);
            return;
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        System.out.println("=== Assignment 2: Huffman Coding Algorithm ===");

        // 投影片第 20 頁的測試資料
        char[] charArray = { 'A', 'B', 'C', 'D', 'E', 'F' };
        int[] charFreq = { 5, 9, 12, 13, 16, 45 };
        int n = charArray.length;

        // 步驟 1: 建立最小堆積並將所有字元加入
        MinHeap minHeap = new MinHeap(n);
        for (int i = 0; i < n; i++) {
            minHeap.insert(new HuffmanNode(charArray[i], charFreq[i]));
        }

        // 步驟 2: Greedy 核心邏輯 - 每次挑選最小的兩個節點合併
        while (minHeap.getSize() > 1) {
            HuffmanNode left = minHeap.extractMin();
            HuffmanNode right = minHeap.extractMin();

            // 建立新的內部節點，頻率為左右子節點之和，字元用 '-' 代表非葉節點
            HuffmanNode newNode = new HuffmanNode('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;

            minHeap.insert(newNode);
        }

        // 最後剩下的節點即為霍夫曼樹的根節點
        HuffmanNode root = minHeap.extractMin();

        System.out.println("--- Huffman Codes ---");
        printHuffmanCodes(root, "");

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("1. Extract min frequency: O(log n)");
        System.out.println("2. Insert combined node: O(log n)");
        System.out.println("3. Performed (n-1) times");
        System.out.println("=> Total Time Complexity: O(n log n)");
        System.out.println("==============================================");
    }
}