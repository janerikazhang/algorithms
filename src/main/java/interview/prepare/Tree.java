package interview.prepare;


/**
 * Created by Rika on 2017/9/10.
 */
public class Tree {
    /**
     * Make a tree
     *
     *  A:                               B:
     *        1                              15
     *      /   \                           /    \
     *     2     3                        6        18
     *    / \   / \                      / \      /  \
     *   4  5  6   7                    3   7    17   20
     *  / \     \                      / \   \
     * 8  9     10                    2   4   13
     *                                        /
     *                                       9
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


        /**
         * BST
         * */
        System.out.println("\n=====BST=========");
        Node rootB = tree.makeTreeB();
        Node n = tree.treeSearch(rootB, 7);
        printResult(n, "Tree search:");

        n = tree.iterativeTreeSearch(rootB, 3);
        printResult(n, "Iterative tree search:");

        n = tree.treeMinimun(rootB);
        printResult(n, "Tree mini:");

        n = tree.treeMaximun(rootB);
        printResult(n, "Tree Maximum:");

        n = tree.treeSuccessor(rootB.left);
        printResult(n, "Tree successor:");

        System.out.println("node is " + rootB.right.left.key);
        n = tree.treePredecessor(rootB.right.left);
        printResult(n, "Tree predecessor:");

        Node randomBST = new Node(0);
        for (int i=1; i<10; i++) {
            tree.treeInsert(randomBST, new Node(i));
        }
        System.out.println("Random built BST and inorder tree walk to sort: ");
        tree.inOrderTreeWalk(randomBST);
    }

    private static void printResult(Node n, String x) {
        if (n != null)
            System.out.println(x + " " + n.key);
        else
            System.out.println(x + " null");
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

    Node makeTreeB() {
        Node root = new Node(15);
        root.left = new Node(6);
        root.left.parent = root;
        root.right = new Node(18);
        root.right.parent = root;

        //next level
        Node nextL = root.left;
        nextL.left = new Node(3);
        nextL.left.parent = nextL;
        nextL.right = new Node(7);
        nextL.right.parent = nextL;

        Node nextR = root.right;
        nextR.left = new Node(17);
        nextR.left.parent = nextR;
        nextR.right = new Node(20);
        nextR.right.parent = nextR;

        //next level
        Node nextLL = nextL.left;
        nextLL.left = new Node(2);
        nextLL.left.parent = nextLL;
        nextLL.right = new Node(4);
        nextLL.right.parent = nextLL;

        Node nextLR = nextL.right;
        nextLR.right = new Node(13);
        nextLR.right.parent = nextLR;
        nextLR.right.left = new Node(9);
        nextLR.right.parent = nextLR;

        return root;
    }

    Node treeMinimun(Node n) {
        while(n.left != null) {
            n = n.left;
        }
        return n;
    }

    Node treeMaximun(Node n) {
        while(n.right!=null) {
            n = n.right;
        }
        return n;
    }

    Node treeSearch(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return treeSearch(root.left, key);
        } else {
            return treeSearch(root.right, key);
        }
    }

    Node iterativeTreeSearch(Node root, int key) {
        while (root != null && root.key != key) {
            if (key < root.key){
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    /**
     * Successor is the smallest key larger than n.key
     * @param n
     * @return
     */
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

    /**
     * Predecessor is the largest key smaller than n.key
     * @param n
     * @return
     */
    Node treePredecessor(Node n) {
        if (n.left != null) {
            return treeMaximun(n.left);
        }

        Node y = n.parent;
        while (y != null && n == y.left) {
            n = y;
            y = y.parent;
        }
        return y;
    }

    Node treeInsert(Node root, Node newNode) {
        Node x = root;
        Node y = null;
        while (x != null) {
            y = x;
            if (newNode.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        newNode.parent = y;
        if (y == null) {
            root = newNode;//tree was empty;
        } else if (newNode.key < y.key) {
            y.left = newNode;
        } else {
            y.right = newNode;
        }
        return root;
    }


    void inOrderTreeWalk(Node root) {
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
