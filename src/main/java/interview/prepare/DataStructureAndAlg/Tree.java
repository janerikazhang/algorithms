package interview.prepare.DataStructureAndAlg;


/**
 * Created by Rika on 2017/9/10.
 */
public class Tree {
    /**
     * Make a tree
     * <p>
     * A:                               B:
     * 1                              15
     * /   \                           /    \
     * 2     3                        6        18
     * / \   / \                      / \      /  \
     * 4  5  6   7                    3   7    17   20
     * / \     \                      / \   \
     * 8  9     10                    2   4   13
     * /
     * 9
     */
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = tree.makeTree(new Node(0), 2);

        /**
         * General Tree walks
         */
        Node rootA = tree.makeTreeA();
        System.out.println("In order tree walk: ");
        tree.inOrderTreeWalk(rootA);

        System.out.println("\nPre order tree walk: ");
        tree.preOrderWalk(rootA);

        System.out.println("\nPost order tree walk: ");
        tree.postOrderTreeWalk(rootA);


        /* Driver program to test above functions */
        tree.root = new Node(-15);
        tree.root.left = new Node(5);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(-8);
        tree.root.left.right = new Node(1);
        tree.root.left.left.left = new Node(2);
        tree.root.left.left.right = new Node(6);
        tree.root.right.left = new Node(3);
        tree.root.right.right = new Node(9);
        tree.root.right.right.right = new Node(0);
        tree.root.right.right.right.left = new Node(4);
        tree.root.right.right.right.right = new Node(-1);
        tree.root.right.right.right.right.left = new Node(10);
        System.out.println("Max pathSum of the given binary tree is "
                + tree.maxSumFromLeafToLeaf(tree.root));

        tree.root = new Node(10);
        tree.root.left = new Node(2);
        tree.root.right = new Node(10);
        tree.root.left.left = new Node(20);
        tree.root.left.right = new Node(1);
        tree.root.right.right = new Node(-25);
        tree.root.right.right.left = new Node(3);
        tree.root.right.right.right = new Node(4);
        System.out.println("maximum path sum is : " +
                tree.maxSumFromNodeToNode(tree.root));

    }

    Node root;


    Node makeTree(Node root, int height) {
        if (height > 0) {
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

    Node makeTreeA() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        Node next = root.left;
        next.left = new Node(4);
        next.right = new Node(5);

        Node next1 = root.right;
        next1.left = new Node(6);
        next1.right = new Node(7);

        next = next.left;
        next.left = new Node(8);
        next.right = new Node(9);

        next1 = next1.left;
        next1.right = new Node(10);
        return root;
    }

    public void inOrderTreeWalk(Node root) {
        if (root != null) {
            inOrderTreeWalk(root.left);
            System.out.print(root.key + "->");
            inOrderTreeWalk(root.right);
        }
    }

    void postOrderTreeWalk(Node root) {
        if (root != null) {
            postOrderTreeWalk(root.left);
            postOrderTreeWalk(root.right);
            System.out.print(root.key + "->");
        }
    }

    void preOrderWalk(Node root) {
        if (root != null) {
            System.out.print(root.key + "->");
            preOrderWalk(root.left);
            preOrderWalk(root.right);
        }
    }

    int maxSumLeafToLeaf = Integer.MIN_VALUE;

    int maxSumNodeToNode = Integer.MIN_VALUE;

    int maxSumFromLeafToLeafFunction(Node n) {
        if (n == null) {
            return 0;
        }
        if (n.left == null && n.right == null) {
            return n.key;
        }
        int lresult = maxSumFromLeafToLeafFunction(n.left);
        int rresult = maxSumFromLeafToLeafFunction(n.right);
        if (n.left != null && n.right != null) {
            maxSumLeafToLeaf = Math.max(maxSumLeafToLeaf, lresult + n.key + rresult);
            return Math.max(lresult + n.key, rresult + n.key);
        }
        return n.left != null ? lresult + n.key : rresult + n.key;
    }

    int maxSumFromLeafToLeaf(Node n) {
        maxSumFromLeafToLeafFunction(n);
        return maxSumLeafToLeaf;
    }

    int maxSumFromNodeToNodeFunction(Node n) {
        if (n == null) {
            return 0;
        }
        int lresult = maxSumFromNodeToNodeFunction(n.left);
        int rresult = maxSumFromNodeToNodeFunction(n.right);

        int max_single = Math.max(Math.max(lresult, rresult) + n.key,
                n.key);
        int max_top = Math.max(max_single, lresult + rresult + n.key);

        maxSumNodeToNode = Math.max(maxSumNodeToNode, max_top);
        return max_single;
    }

    int maxSumFromNodeToNode(Node n) {
        maxSumFromNodeToNodeFunction(n);
        return maxSumNodeToNode;
    }

    static class Node {
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
