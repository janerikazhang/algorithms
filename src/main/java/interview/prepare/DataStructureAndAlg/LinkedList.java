package interview.prepare.DataStructureAndAlg;

/**
 * Created by Rika on 2017/11/5.
 */
public class LinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // creating first linked list
        list.head1 = new Node(3);
        list.head1.next = new Node(6);
        list.head1.next.next = new Node(15);
        list.head1.next.next.next = new Node(15);
        list.head1.next.next.next.next = new Node(30);

        // creating second linked list
        list.head2 = new Node(10);
        list.head2.next = new Node(15);
        list.head2.next.next = new Node(30);

        System.out.println("The node of intersection is " + list.intersectPoint(list.head1, list.head2));

    }
    Node head1;
    Node head2;
    static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }
    int intersectPoint(Node headA, Node headB)
    {
        //get count
        Node tA = headA;
        Node tB = headB;

        int sizeA = 0,sizeB = 0;
        while (tA != null) {
            tA = tA.next;
            sizeA ++;
        }

        while (tB != null) {
            tB = tB.next;
            sizeB++;
        }

        tA = headA;
        tB = headB;
        if (sizeA > sizeB) {
            int diff = sizeA - sizeB;
            while (diff > 0) {
                tA = tA.next;
                diff --;
            }
        } else if (sizeA < sizeB) {
            int diff = sizeB - sizeA;
            while (diff > 0) {
                tB = tB.next;
                diff --;
            }
        }

        int toggle = 0;
        while (tA != null && tB != null) {
            if (tA.key == tB.key) {
                return tA.key;
            }
            if (toggle == 0) {
                //move A
                tA = tA.next;
                toggle = 1;
            } else {
                //move B
                tB = tB.next;
                toggle = 0;
            }
        }

        return -1;
    }
}
