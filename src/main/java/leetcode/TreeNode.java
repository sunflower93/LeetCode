package leetcode;

/**
 * Created by ShaoBin on 2016/3/4.
 */

//Definition for a binary tree node.
  public class TreeNode {
      int val;
      char ch;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }

      public void setCh(char c) {
          this.ch = c;
      }

      public void insert(TreeNode root,int data) {     //��������в����ӽڵ�
          if (data > root.val)                               //����������ڵ㶼�ȸ��ڵ�С
          {
              if (root.right == null) {
                  root.right = new TreeNode(data);
              } else {
                  this.insert(root.right, data);
              }
          } else {                                          //���������ҽڵ㶼�ȸ��ڵ��
              if (root.left == null) {
                  root.left = new TreeNode(data);
              } else {
                  this.insert(root.left, data);
              }
          }
      }
  }
