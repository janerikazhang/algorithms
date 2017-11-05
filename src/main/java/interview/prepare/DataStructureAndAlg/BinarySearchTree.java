package interview.prepare.DataStructureAndAlg;

/**
 * Created by Rika on 2017/11/5.
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        /**
         * BST
         * */
        System.out.println("\n=====BST=========");
        Tree.Node rootB = tree.makeTreeB();
        Tree.Node n = tree.treeSearch(rootB, 7);
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

        Tree.Node randomBST = new Tree.Node(0);
        for (int i = 1; i < 10; i++) {
            tree.treeInsert(randomBST, new Tree.Node(i));
        }
        System.out.println("Random built BST and inorder tree walk to sort: ");
        new Tree().inOrderTreeWalk(randomBST);
    }

    Tree.Node makeTreeB() {
        Tree.Node root = new Tree.Node(15);
        root.left = new Tree.Node(6);
        root.left.parent = root;
        root.right = new Tree.Node(18);
        root.right.parent = root;

        //next level
        Tree.Node nextL = root.left;
        nextL.left = new Tree.Node(3);
        nextL.left.parent = nextL;
        nextL.right = new Tree.Node(7);
        nextL.right.parent = nextL;

        Tree.Node nextR = root.right;
        nextR.left = new Tree.Node(17);
        nextR.left.parent = nextR;
        nextR.right = new Tree.Node(20);
        nextR.right.parent = nextR;

        //next level
        Tree.Node nextLL = nextL.left;
        nextLL.left = new Tree.Node(2);
        nextLL.left.parent = nextLL;
        nextLL.right = new Tree.Node(4);
        nextLL.right.parent = nextLL;

        Tree.Node nextLR = nextL.right;
        nextLR.right = new Tree.Node(13);
        nextLR.right.parent = nextLR;
        nextLR.right.left = new Tree.Node(9);
        nextLR.right.parent = nextLR;

        return root;
    }

    Tree.Node treeMinimun(Tree.Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    Tree.Node treeMaximun(Tree.Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    private static void printResult(Tree.Node n, String x) {
        if (n != null)
            System.out.println(x + " " + n.key);
        else
            System.out.println(x + " null");
    }

    Tree.Node treeSearch(Tree.Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return treeSearch(root.left, key);
        } else {
            return treeSearch(root.right, key);
        }
    }

    Tree.Node iterativeTreeSearch(Tree.Node root, int key) {
        while (root != null && root.key != key) {
            if (key < root.key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    /**
     * Successor is the smallest key larger than n.key
     *
     * @param n
     * @return
     */
    Tree.Node treeSuccessor(Tree.Node n) {
        if (n.right != null) {
            return treeMinimun(n.right);
        }
        Tree.Node y = n.parent;
        while (y != null && n == y.right) {
            n = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * Predecessor is the largest key smaller than n.key
     *
     * @param n
     * @return
     */
    Tree.Node treePredecessor(Tree.Node n) {
        if (n.left != null) {
            return treeMaximun(n.left);
        }

        Tree.Node y = n.parent;
        while (y != null && n == y.left) {
            n = y;
            y = y.parent;
        }
        return y;
    }

    Tree.Node treeInsert(Tree.Node root, Tree.Node newNode) {
        Tree.Node x = root;
        Tree.Node y = null;
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
}
