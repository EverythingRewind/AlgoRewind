package datastructures.tree;

import com.sun.source.tree.BinaryTree;

import java.util.Comparator;
import java.util.TreeMap;

public class BinarySearchTree<E> {

    private Node<E> root;

    private Comparator<? super E> cmp;

    private int size;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    private boolean contains(E e, Node<E> t) {
        Node<E> node = t;

        if (e == null || node == null) {
            return false;
        }

        int cmp = compare(e, node.value);

        if (cmp < 0) {
            return contains(e, node.left);
        } else if (cmp > 0) {
            return contains(e, node.right);
        } else {
            return true;
        }
    }

    public boolean contains(E e) {

        Node<E> node = root;

        if (e == null || node == null) {
            return false;
        }

        do {
            int cmp = compare(e, node.value);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return true;
            }
        } while (node != null);

        return false;
    }

    private int compare(E e1, E e2) {
        if (cmp != null) {
            return cmp.compare(e1, e2);
        } else {
            return ((Comparable<E>) e1).compareTo(e2);
        }
    }

    public void insert(E e) {
        if (e == null) {
            throw new IllegalArgumentException("BST does not accept null node");
        }

        Node<E> t = root;

        if (t == null) {
            root = new Node<>(e);
            return;
        }

        Node<E> parent;
        int cmp;

        do {
            cmp = compare(e, t.value);
            parent = t;
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                // already in the tree, just return;
                return;
            }
        } while (t != null);

        // not in the tree,
        if (cmp < 0) {
            parent.left = new Node<>(e);
        } else {
            parent.right = new Node<>(e);
        }
        size++;

        // TODO balance the tree
    }

    public void print() {
        print(root);
    }

    private void print(Node<E> t) {
        if (t == null) return;
        if (t.left != null) {
            print(t.left);
        }

        if (t != null) {
            System.out.print(t.value + " ");
        }

        if (t.right != null) {
            print(t.right);
        }

    }

    private Node<E> insert(E e, Node<E> t) {

        if (t == null) {
            return new Node<>(e);
        }

        Node<E> node = t;

        int cmp = compare(e, node.value);

        if (cmp < 0) {
            node.left = insert(e, node.left);
        } else if (cmp > 0) {
            node.right = insert(e, node.right);
        } else {
            // already in the tree

        }
        return node;
    }

    public void remove(E e) {
        remove(e, root);
    }

    public E findMax() {
        Node<E> t = root;

        if (t == null) {
            return null;
        }

        while (t.right != null) {
            t = t.right;
        }
        return t.value;
    }

    public E findMin() {
        return findMin(root).value;
    }

    public Node<E> findMin(Node<E> root) {
        Node<E> t = root;

        if (t == null) {
            return null;
        }

        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    public int getHeight() {
        Node<E> t = root;

        if (t == null) {
            return 0;
        }

        int leftTreeLength = 0;
        int rightTreeLength = 0;

        while (t.left != null) {
            leftTreeLength++;
            t = t.left;
        }
        t = root;
        while (t.right != null) {
            rightTreeLength++;
            t = t.right;
        }

        return rightTreeLength > leftTreeLength ? rightTreeLength : leftTreeLength;
    }

    public Node<E> remove(E e, Node<E> t) {

        if (t == null) {
            return t;
        }

        int compareResult = compare(e, t.value);

        if (compareResult < 0)
            t.left = remove(e, t.left);
        else if (compareResult > 0)
            t.right = remove(e, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.value = findMin(t.right).value;
            t.right = remove(t.value, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void removeNonRecursive(E e) {

        Node<E> current = root;

        if (current == null || e == null) {
            return;
        }

        int cmp = -1;
        Node<E> parent = root;
        boolean isleftChild = false;

        while((cmp = compare(e, current.value)) != 0) {
            parent = current;
            if (cmp < 0) {
                current = current.left;
                isleftChild = true;
            } else if (cmp > 0) {
                current = current.right;
                isleftChild = false;
            }

            if (current == null) {
                // not found
                break;
            }

        }

        if (current != null) {
            // found

            // 1. t has no child.
            // parent link to null
            if (current.left == null && current.right == null) {
                if (isleftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if ((current.left != null && current.right == null) || (current.right != null && current.left == null)) {

                Node<E> child = current.left != null ? current.left : current.right;

                if (isleftChild) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }

            } else {
                // deleting node has two children
                // find the smallest node of right subtree
                // replace the value of deleting node with the smallest node
                // remove the smallest node of right subtree

                // find the smallest node of right subtree
                Node<E> cur = current.right;
                Node<E> p = current;

                while (cur.left != null) {
                    p = cur;
                    cur = cur.left;
                }

                current.value = cur.value;

                // remove it the smallest node of the right subtree
                p.right = null;

            }
        }

    }

    static class Node<E> {

        E value;

        Node<E> left;

        Node<E> right;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(4);
        tree.insert(2);
        tree.insert(0);
        tree.insert(6);

        tree.print();
//        System.out.println(tree.findMax());
//        System.out.println(tree.findMin());
//        System.out.println(tree.getHeight());
        System.out.println();
        //tree.print();
        tree.removeNonRecursive(2);
        tree.print();
    }

}
