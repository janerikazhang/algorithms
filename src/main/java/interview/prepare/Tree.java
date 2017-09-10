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
    }

    Node makeTree(Node root, int height) {
        if (height >0) {
            root.left = new Node(root.key + 1);
            root.right = new Node(root.key + 2);
            height--;
            makeTree(root.left, height);
            makeTree(root.right, height);
        }
        return root;
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
        Node left;
        Node right;
        int key;
        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }
}
