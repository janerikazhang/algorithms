package interview.prepare.DataStructureAndAlg;

/**
 * Created by Rika on 2017/10/31.
 */
public class TreeToDLL {
    /**
     * 10
     * /    \
     * 12     15
     * / \    /
     * 25 30 36
     */

    public static void main(String[] args) {

    }
    class Node {
        Node left, right;
        int data;

        Node(int d) {
            data = d;
            left = right = null;
        }

    }
// This function should convert a given Binary tree to Doubly
// Linked List
// root --> Root of Binary Tree
// head --> head of created doubly linked list

    Node head;
    Node tail;

    Node BToDLL(Node root) {
        head = null;
        if (root != null) {
            head = BToDLL(root.left);
            // add to tail
            if (head == null) {
                head = tail = new Node(root.data);
            } else {
                Node t = new Node(root.data);
                tail.right = t;
                t.left = tail;
            }
            head = BToDLL(root.right);
        }
        return head;
    }
}
