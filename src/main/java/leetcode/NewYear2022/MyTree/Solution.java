package leetcode.NewYear2022.MyTree;


import java.util.*;

/**
 * Created by shaobin on 2022/1/19.
 */
public class Solution {
    //二叉搜索树中第K小的元素
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int index = 1;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (k == index) break;
            index++;
            root = root.right;
        }
        return root.val;
    }

    //二叉搜索树构建平衡二叉树
    List<Integer> inorderSeq = new ArrayList<Integer>();

    public TreeNode balanceBST(TreeNode root) {
        getInorder(root);
        return build(0, inorderSeq.size() - 1);
    }

    public void getInorder(TreeNode o) {
        if (o.left != null) {
            getInorder(o.left);
        }
        inorderSeq.add(o.val);
        if (o.right != null) {
            getInorder(o.right);
        }
    }

    public TreeNode build(int l, int r) {
        int mid = (l + r) >> 1;
        TreeNode o = new TreeNode(inorderSeq.get(mid));
        if (l <= mid - 1) {
            o.left = build(l, mid - 1);
        }
        if (mid + 1 <= r) {
            o.right = build(mid + 1, r);
        }
        return o;
    }


    //二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        if (left == null) return right;
        return left;
    }

    //天际线问题，输入为建筑物三元组，分别代表水平起始点、终止点、垂直高度，输出为关键点，建筑物默认矩形
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ans = new ArrayList<>();
        int n = buildings.length;
        int idx = 0;
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (ans.size() == 0 || maxn != ans.get(ans.size() - 1).get(1)) {
                ans.add(Arrays.asList(boundary, maxn));
            }
        }
        return ans;
    }
}
