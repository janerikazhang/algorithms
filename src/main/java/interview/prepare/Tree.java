package interview.prepare;

/**
 * Created by Rika on 2017/9/10.
 */
public class Tree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = tree.makeTree(new Node(0), 2);
        System.out.println("In order tree walk: ");
        tree.inOrderTreeWalk(root);

        System.out.println("\nPre order tree walk: ");
        tree.preOrderWalk(root);

        System.out.println("\nPost order tree walk: ");
        tree.postOrderTreeWalk(root);

        Node n = tree.treeMinimun(root.right.right);
        if (n != null)
            System.out.println("\nTree mini: " + n.key);

        n = tree.treeSuccessor(root.left.right);
        if (n != null)
            System.out.println("\nTree successor: " + n.key);
    }

    Node makeTree(Node root, int height) {
        if (height >0) {
            root.left = new Node(root.key + 1);
            root.left.parent = root;
            root.right = new Node(root.key + 2);
            root.right.parent = root;
            height--;
            makeTree(root.left, height);
            makeTree(root.right, height);
        }
        return root;
    }
    Node treeMinimun(Node n) {
        while(n.left != null) {
            n = n.left;
        }
        return n;
    }

    Node treeSuccessor(Node n) {
        if(n.right != null) {
            return treeMinimun(n.right);
        }

        Node y = n.parent;
        while (y != null && n == y.right) {
            n = y;
            y = y.parent;
        }
        return y;
    }


    void inOrderTreeWalk(Node root) {
        if (root != null) {
            inOrderTreeWalk(root.left);
            System.out.print(root.key);
            inOrderTreeWalk(root.right);
        }
    }

    void postOrderTreeWalk(Node root) {
        if (root != null) {
            postOrderTreeWalk(root.left);
            postOrderTreeWalk(root.right);
            System.out.print(root.key);
        }
    }

    void preOrderWalk(Node root) {
        if (root != null) {
            System.out.print(root.key);
            preOrderWalk(root.left);
            preOrderWalk(root.right);
        }
    }

    static class Node{
        Node parent;
        Node left;
        Node right;
        int key;
        Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }
}
