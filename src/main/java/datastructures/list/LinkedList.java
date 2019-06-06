package datastructures.list;

import java.util.HashSet;

public class LinkedList {

    static class Node<E> {
        E e;
        Node<E> next;
    }

    static class SinglyLinkedList<E> {
        Node<E> first;
        Node<E> last;

        int size;

        public void addFirst(E e) {

            Node<E> node = new Node<>();
            node.e = e;

            if (first == null) {
                first = node;
                last = node;
            } else {
                node.next = first;
                first = node;
            }
            size++;
        }
        // 0 1 2 3
        public void add(E e, int pos) {
            if (pos < 0 || pos > size) {
                throw new IndexOutOfBoundsException("pos must between 0 and size -1");
            }

            Node<E> node = new Node<>();
            node.e = e;

            Node<E> before = first;
            Node<E> after = null;
            for (int i = 0; i < pos - 1; i++) {
                before = before.next;
            }
            after = before.next;
            before.next = node;
            node.next = after;

            size++;
        }

        public void print() {
            if (first == null) {
                System.out.println("empty list");
            }

            String toString = "";
            Node<E> pointer = first;
            toString += pointer.e;
            while((pointer = pointer.next) != null) {
                toString += " | " +  pointer.e;
            }
            System.out.println(toString);
        }

    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addFirst(7);
        list.addFirst(6);
        list.addFirst(5);
        list.addFirst(5);
        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(4);
        list.addFirst(2);
        list.addFirst(1);

        list.print();

        //removeGivenElement(list.first.next.next.next);
        removeDuplicates(list);
        list.print();

    }

    // Remove Dups: Write code to remove duplicates from an unsorted linked list
    // no buffer allowed
    public static <E> SinglyLinkedList<E> removeDuplicates(SinglyLinkedList<E> list) {

        // make use of hash table or set

        var set = new HashSet<E>();

        var current = list.first;

        current = list.first;
        var prev = current;
        while (current.next != null) {
            if (set.contains(current.e)) {
                // remove current
                prev.next = current.next;
                current.next = null;
                current = prev;

            }
            set.add(current.e);
            prev = current;
            current = current.next;
        }

        return list;
    }

    public static<E> void removeGivenElement(Node<E> removing) {
        var current = removing;
        var prev = current;

        while(current.next != null) {
            E tmp = current.e;
            current.e = current.next.e;
            current.next.e = tmp;
            prev = current;
            current = current.next;
        }

        // remove given element
        prev.next = null;

    }

    public static <E> Node<E> delectLoop(SinglyLinkedList<E> list) {

        var current1 = list.first;

        var current2 = list.first;

        while (current1 != null ) {
            return null;
        }

        return null;

    }

}
