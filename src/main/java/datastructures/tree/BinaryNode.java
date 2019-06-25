package datastructures.tree;

import datastructures.stack.Stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryNode {

    Object value;

    BinaryNode left;

    BinaryNode right;

    public BinaryNode(Object value) {
        this.value = value;
    }

    public BinaryNode(Object value, BinaryNode left, BinaryNode right) {
        this(value);
        this.left = left;
        this.right = right;
    }

    public static BinaryNode fromPostfix(List<String> postFix) {
        var stack = new ArrayDeque<BinaryNode>();
        var infixStack = new ArrayDeque<String>();


        String infix = "";

        for (var e : postFix) {
            if (!Stacks.isOperator(e)) {
                stack.push(new BinaryNode(e));
                infixStack.push(e);
            } else {
                var subTree1 = stack.pop();
                var subTree2 = stack.pop();

                var right = infixStack.pop();
                var left  = infixStack.pop();

                // build a tree with root is the operator whose left child is subTree2 and right child is subTree1

                var root = new BinaryNode(e, subTree2, subTree1);

                infix = "(" + left + e + right + ")";
                infixStack.push(infix);
                stack.push(root);

            }
        }
        System.out.println(infixStack.pop());
        return stack.pop();

    }

    public String listAll(String acc) {

        if (left != null) {
            acc =  left.listAll(acc);
        }

        if (right != null) {
            acc =  right.listAll(acc);
        }

        if (value != null) {
            acc =  acc + value;
        }

        return acc;
    }

    public static void main(String[] args) {
        var postFix = Arrays.stream(("a b + c d e + * *").split(" "))
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());

        BinaryNode root = fromPostfix(postFix);
        String s = root.listAll("");
        System.out.println(s);
    }


}
