package interview.prepare;

public class AmazonLinkedlistTest {

    public static void main(String[] args) {
        Node LL = new Node(0);
        Node t = LL;
        for (int i = 0; i < 9; i++) {
            Node n = new Node(i + 1);
            t.next = n;
            t = t.next;
        }
        Node reversedLL = reverseLinkedList(LL);

        Node rt = reversedLL;
        while (rt != null) {
            System.out.println(rt.key);
            rt = rt.next;
        }
    }

    static class Node {
        Node(int k) {
            key = k;
            next = null;
        }
        int key;
        Node next;
    }

    public static Node reverseLinkedList(Node head) {
        Stack stack = new Stack(100);
        Node t = head;
        while (t != null) {
            stack.push(new Node(t.key));
            t = t.next;
        }
        Node p = null;
        Node resultHead = null;
        while (stack.size() > 0) {
            Node current = stack.pop();
            if (resultHead == null) {
                resultHead = p = current;
            } else {
                p.next = current;
                p = p.next;
            }
        }
        return resultHead;
    }

    static class Stack {
        Node pointer = null;
        int size = 0;
        int maxSize = 1000;

        Stack(int maxSize) {
            this.maxSize = maxSize;
        }

        int getMaxSize() {
            return maxSize;
        }

        void push(Node n) {
            if (size >= maxSize) {
                throw new RuntimeException("Max size exceed.");
            }
            if (pointer == null) {
                pointer = n;
            } else {
                n.next = pointer;
                pointer = n;
            }
            size++;

        }

        Node pop() {
            Node n = null;
            if (pointer != null) {
                n = pointer;
                pointer = pointer.next;
                size--;
            }
            return n;
        }

        int size() {
            return this.size;
        }
    }
}
