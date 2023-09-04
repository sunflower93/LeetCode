package leetcode;

import leetcode.NewYear2022.MyTree.TreeNode;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by ShaoBin on 2016/3/8.
 */

public class BinaryTreePreorder {

    public static void preOrder(TreeNode root) {  //�ȸ�����
        if (root != null) {
            System.out.print(root.val + "-");
            preOrder(root.left);
            preOrder(root.right);
        }
        else System.out.println("null");
    }

    public static void inOrder(TreeNode root) {     //�и�����
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + "-");
            inOrder(root.right);
        }
        else System.out.println("null");
    }

    public static void postOrder(TreeNode root) {    //�������
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + "-");
        }
        else System.out.println("null");
    }

    public  static void preOrder2(TreeNode root) {    //��������ǵݹ�
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                System.out.print(root.val + "-");
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    public static void inOrder2(TreeNode root) {    //��������ǵݹ�
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                root = stack.pop();
                System.out.print(root.val + "-");
                root = root.right;
            }
        }
    }

    public static void postOrder2(TreeNode root) {   //��������ǵݹ�
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<Integer> integer = new Stack<Integer>();
        Integer i = new Integer(1);
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                integer.push(new Integer(0));
                root = root.left;
            }
            while (!stack.empty() && integer.peek().equals(i)) {
                integer.pop();
                System.out.print(stack.pop().val + "-");
            }
            if (!stack.empty()) {
                integer.pop();
                integer.push(new Integer(1));
                root = stack.peek();
                root = root.right;
            }
        }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {       //�Ƚ������������Ƿ����
        if (p != null && q != null) {
            if (p.val != q.val) return false;
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
        else if (p == null && q == null) {
            return true;
        }
        return false;
    }

    public static TreeNode find(TreeNode root, int x) {         //������
        while (root != null) {
            if (root.val > x) root = root.left;
            else if (root.val < x) root = root.right;
            else return root;
        }
        return null;
    }

    public static int min(TreeNode root) {                    //������СԪ��
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    public static int max(TreeNode root) {                    //�������Ԫ��
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    private static TreeNode getSuccessor(TreeNode delNode) {                         //Ѱ��ɾ���ڵ�ĺ�̽ڵ�
        TreeNode successorParent = delNode;
        TreeNode successor = delNode;
        TreeNode current = delNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != delNode.right) {
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

    public static void delete(TreeNode root, int key) {                        //ɾ���ڵ�
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeft = true;

        while (current.val != key) {
            parent = current;
            if (key < current.val) {
                isLeft = true;
                current = current.left;
            }
            else {
                isLeft = false;
                current = current.right;
            }
            if (current == null)
                return ;
        }
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeft)
                parent.left = null;
            else
                parent.right = null;
        }
        else if (current.right == null) {
            if (current == root)
                root = current.left;
            else if (isLeft)
                parent.left = current.left;
            else
                parent.right = current.left;
        }
        else if (current.left == null) {
            if (current == root)
                root = current.right;
            else if (isLeft)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeft)
                parent.left = successor;
            else
                parent.right = successor;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = {12, 76, 35, 22, 16, 48, 90, 46, 9, 40};
        int[] array1 = {12, 76, 3, 22, 16, 48, 90, 46, 9, 40};
        TreeNode root = new TreeNode(array[0]);   //����������
        TreeNode root1 = new TreeNode(array1[0]);
        for (int i = 1; i < array.length; i++) {
            root.insert(root, array[i]);       //��������в�������
        }
        for (int i = 1; i < array1.length; i++) {
            root.insert(root1, array1[i]);       //��������в�������
        }
//        System.out.println("�ȸ�������");
//        preOrder(root);
//        System.out.println();
//        preOrder2(root);
//        System.out.println();
//        System.out.println("�и�������");
//        inOrder(root);
//        System.out.println();
//        inOrder2(root);
//        System.out.println();
//        System.out.println("���������");
//        postOrder(root);
//        System.out.println();
//        postOrder2(root);
//        System.out.println();
        Solution solution = new Solution();
//        root = solution.invertTree(root);
//        postOrder(root);
//        System.out.println();
//        preOrder(root);
//        System.out.println();
//        inOrder(root);
//        System.out.println();
//        System.out.println(isSameTree(root,root1));
//        int x = in.nextInt();
//        TreeNode ans = find(root, x);
//        System.out.println("�ȸ�������");
//        preOrder(ans);
//        System.out.println();
//        preOrder2(ans);
//        System.out.println();
        System.out.println(max(root));
        System.out.println(min(root));
    }
}
