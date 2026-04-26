// 定義樹節點結構，資料型態改為 int
class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class Assignment3 {
    Node root;

    // 1. 前序走訪 (Preorder): Root -> Left -> Right
    void printPreorder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    // 2. 中序走訪 (Inorder): Left -> Root -> Right
    // 觀察重點：在二元搜尋樹中，這會印出由小到大的序列 
    void printInorder(Node node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    // 3. 後序走訪 (Postorder): Left -> Right -> Root [cite: 327-328]
    void printPostorder(Node node) {
        if (node == null) return;
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        Assignment3 tree = new Assignment3();

        /* 建構具備數值邏輯的二元樹：
                  40
                /    \
               20     60
              /  \   /  \
             10  30 50  70  [cite: 263-283]
        */
        tree.root = new Node(40);
        tree.root.left = new Node(20);
        tree.root.right = new Node(60);
        tree.root.left.left = new Node(10);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(50);
        tree.root.right.right = new Node(70);

        System.out.println("=== Assignment 3: Tree Traversal (Numeric) ===");
        
        System.out.print("Preorder Traversal (根左右):  ");
        tree.printPreorder(tree.root);
        System.out.println();

        System.out.print("Inorder Traversal (左根右):   ");
        tree.printInorder(tree.root);
        System.out.println(" <-- 觀察重點：呈現遞增排序");

        System.out.print("Postorder Traversal (左右根): ");
        tree.printPostorder(tree.root);
        System.out.println();

        System.out.println("\n--- Complexity Analysis ---");
        System.out.println("Time Complexity: O(V)");
        System.out.println("V = Number of nodes (7 in this case)");
        System.out.println("=============================================");
    }
}