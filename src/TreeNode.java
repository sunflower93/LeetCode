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

      public void insert(TreeNode root,int data) {     //向二叉树中插入子节点
          if (data > root.val)                               //二叉树的左节点都比根节点小
          {
              if (root.right == null) {
                  root.right = new TreeNode(data);
              } else {
                  this.insert(root.right, data);
              }
          } else {                                          //二叉树的右节点都比根节点大
              if (root.left == null) {
                  root.left = new TreeNode(data);
              } else {
                  this.insert(root.left, data);
              }
          }
      }
  }
