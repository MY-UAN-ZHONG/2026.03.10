import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class GraphNode {
    char key;
    GraphNode left, right;

    public GraphNode(char item) {
        key = item;
        left = right = null;
    }
}

public class Assignment5 {
    GraphNode root;

    // ==========================================
    // 1. Depth First Search (DFS) - 使用 Stack 
    // ==========================================
    void DFS(GraphNode node) {
        if (node == null) return;
        
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            GraphNode current = stack.pop();
            System.out.print(current.key + " ");

            // ⚠️ 關鍵：先推入右節點，再推入左節點
            // 這樣 Stack 彈出 (pop) 時，左節點才會先出來
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    // ==========================================
    // 2. Breadth First Search (BFS) - 使用 Queue 
    // ==========================================
    void BFS(GraphNode node) {
        if (node == null) return;

        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            System.out.print(current.key + " ");

            // Queue 是先進先出 (FIFO)，所以按順序加入左右節點
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public static void main(String[] args) {
        Assignment5 tree = new Assignment5();

        // 建立投影片 P.21 的標準測試樹 
        tree.root = new GraphNode('A');
        tree.root.left = new GraphNode('B');
        tree.root.right = new GraphNode('C');
        tree.root.left.left = new GraphNode('D');
        tree.root.left.right = new GraphNode('E');
        tree.root.right.left = new GraphNode('F');
        tree.root.right.right = new GraphNode('G');

        System.out.println("=== Assignment 5: DFS vs BFS ===");
        
        System.out.print("DFS Sequence (Stack based): ");
        tree.DFS(tree.root);

        System.out.print("\nBFS Sequence (Queue based): ");
        tree.BFS(tree.root);

        System.out.println("\n\n--- Time Complexity Analysis ---");
        System.out.println("DFS: O(V + E) [cite: 428-429]");
        System.out.println("BFS: O(V + E) [cite: 430]");
        System.out.println("V = vertices (nodes), E = edges");
        System.out.println("===============================================");
    }
}