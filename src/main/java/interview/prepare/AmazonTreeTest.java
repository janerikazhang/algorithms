package interview.prepare;

/**
 * Created by Rika on 2017/10/24.
 */
public class AmazonTreeTest {
    class Node {
        int key;
        Node left;
        Node right;
    }

    int getSize(Node root) {
        int size = 0;
        if (root != null) {
            size++;
            if (root.left != null) {
                size = size + getSize(root.left);
            }
            if (root.right != null) {
                size = size + getSize(root.right);
            }
        }
        return size;
    }

    int getLeafCount(Node root) {
        int size = 0;
        if (root != null) {
            if (root.left != null) {
                size = size + getLeafCount(root.left);
            }
            if (root.right != null) {
                size = size + getLeafCount(root.right);
            }
            if (root.left == null && root.right == null) {
                size++;
            }
        }
        return size;
    }
}
