package leetcode.NewYear2022.MyString;

import java.util.HashMap;

/**
 * 字典树结构定义
 * Created by shaobin on 2022/1/7.
 */
public class DictTree {
    Character val;
    HashMap<Character, DictTree> children = new HashMap<>();

    public DictTree(Character c) {
        this.val = c;
    }

    public static DictTree updateTree(DictTree root, String s) {
        DictTree tempTree = root;
        for (Character c : s.toCharArray()) {
            if (tempTree.children.containsKey(c)) {
                tempTree = tempTree.children.get(c);
            }
            else {
                DictTree temp = new DictTree(c);
                tempTree.children.put(c, temp);
                tempTree = temp;
            }
        }
        // 添加结束符
        if (!tempTree.children.containsKey('0')) {
            DictTree temp = new DictTree('0');
            tempTree.children.put('0', temp);
        }
        return root;
    }
}
