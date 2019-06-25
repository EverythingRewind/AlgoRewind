package datastructures.tree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNode<E> {

    E value;

    TreeNode<E> firstChild;

    TreeNode<E> nextSibling;

    public TreeNode(E value) {
        this.value = value;
    }

    public boolean isDirectory() {
        return firstChild != null;
    }

    public String toString() { return value.toString();}

    public void listAll(String parentPath) {
        if (isDirectory()) {
            String path = parentPath.isEmpty() ? value.toString() : parentPath + "/" + value;
            firstChild.listAll(path);
        }
        TreeNode<E> node = nextSibling;
        if (node != null) {
            node.listAll(parentPath);
        }

        printNode(parentPath);

    }

    public void listAllPostOrder(String parentPath) {

        if (isDirectory()) {
            String path = parentPath.isEmpty() ? value.toString() : parentPath + "/" + value;
            firstChild.listAllPostOrder(path);
        }

        printNode(parentPath);

        TreeNode<E> node = nextSibling;
        if (node != null) {
            node.listAllPostOrder(parentPath);
        }


    }

    public TreeNode<E> getNode(String fullPath, String parentPath) {

        if (fullPath == null || fullPath.isBlank()) {
            return null;
        }

        String path = parentPath.isBlank() ? value.toString() : parentPath + "/" + value;

        if (fullPath.trim().equals(path)) {
            return this;
        }

        if (isDirectory()) {
            var node = firstChild.getNode(fullPath, path);
            if (node != null) {
                return node;
            }
        }

        if (nextSibling != null) {
            var node = nextSibling.getNode(fullPath, parentPath);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public TreeNode<E> findNode(String fullPath) {
        if (fullPath == null || fullPath.isBlank()) {
            return null;
        }

        return getNode(fullPath, "");

    }


    public void listAll() {
        listAll("");
    }

    public void listAllPostOrder() {
        listAllPostOrder("");
    }


    public void printNode(String parentPath) {
        String path = parentPath.isEmpty() ? value.toString() : parentPath + "/" + value;
        System.out.println(path);
    }

    public void printNodePost(int depth, String childPath) {
        String path =  childPath +  "/" + value;
        System.out.println(path);
    }

    public static void main(String[] args) {
        TreeNode<String> usr = new TreeNode<>("/usr");

        TreeNode<String> mark = new TreeNode<>("mark");
        TreeNode<String> mark1 = new TreeNode<>("mark");


        TreeNode<String> book = new TreeNode<>("book");
        TreeNode<String> chap1 = new TreeNode<>("chap1");
        TreeNode<String> chap2 = new TreeNode<>("chap2");
        TreeNode<String> chap3 = new TreeNode<>("chap3");
        book.firstChild = chap1;
        chap1.nextSibling = chap2;
        chap2.nextSibling = chap3;

        mark.firstChild = book;

        TreeNode<String> course = new TreeNode<>("course");
        TreeNode<String> cop3530 = new TreeNode<>("cop3530");
        TreeNode<String> fall = new TreeNode<>("fall");
        TreeNode<String> spr = new TreeNode<>("spr");
        TreeNode<String> sum = new TreeNode<>("sum");

        TreeNode<String> fallSy1r = new TreeNode<>("sy1.r");
        TreeNode<String> sprSy1r = new TreeNode<>("sy1.r");
        TreeNode<String> sumSy1r = new TreeNode<>("sy1.r");

        fall.firstChild = fallSy1r;
        spr.firstChild = sprSy1r;
        sum.firstChild = sumSy1r;

        course.firstChild = cop3530;
        cop3530.firstChild = fall;
        fall.nextSibling = spr;
        spr.nextSibling = sum;
        book.nextSibling = course;

        TreeNode<String> junk = new TreeNode<>("junk");
        course.nextSibling = junk;

        TreeNode<String> alex = new TreeNode<>("alex");
        TreeNode<String> alex1 = new TreeNode<>("alex");

        TreeNode<String> alexJunk = new TreeNode<>("junk");
        alex.firstChild = alexJunk;

        TreeNode<String> bill = new TreeNode<>("bill");
        TreeNode<String> bill1 = new TreeNode<>("bill");


        TreeNode<String> billWork = new TreeNode<>("work");
        TreeNode<String> billCourse = new TreeNode<>("course");

        bill.firstChild = billWork;
        billWork.nextSibling = billCourse;

        TreeNode<String> cop3212 = new TreeNode<>("cop3212");
        TreeNode<String> billFall = new TreeNode<>("fall");
        TreeNode<String> billFall1 = new TreeNode<>("fall");

        billCourse.firstChild = cop3212;
        cop3212.firstChild = billFall;
        billFall.nextSibling = billFall1;

        TreeNode<String> fallGrades = new TreeNode<>("grades");
        TreeNode<String> fallProg1 = new TreeNode<>("prog1.r");
        TreeNode<String> fallProg2 = new TreeNode<>("prog2.r");

        billFall.firstChild = fallGrades;
        fallGrades.nextSibling = fallProg1;
        fallProg1.nextSibling = fallProg2;

        TreeNode<String> fallGrades2 = new TreeNode<>("grades");
        TreeNode<String> fallProg11 = new TreeNode<>("prog1.r");
        TreeNode<String> fallProg22 = new TreeNode<>("prog2.r");

        billFall1.firstChild = fallGrades2;
        fallGrades2.nextSibling = fallProg11;
        fallProg11.nextSibling = fallProg22;

        usr.firstChild = mark;
        mark.nextSibling = alex;
        alex.nextSibling = bill;

        usr.listAll();
       // usr.listAllPostOrder();

        var node = usr.findNode("/usr/bill/course/cop3212/fall/prog1.r");
        System.out.println(node);
    }



}
