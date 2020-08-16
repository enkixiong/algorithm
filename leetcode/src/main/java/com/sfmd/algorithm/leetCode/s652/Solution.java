package com.sfmd.algorithm.leetCode.s652;

import com.alibaba.fastjson.JSON;
import com.sfmd.algorithm.leetCode.s449.LeetcodeCodec;
import com.sfmd.algorithm.leetCode.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap<>();
        count = new HashMap<>();
        ans = new ArrayList<>();
        lookup(root);
        System.out.println(JSON.toJSONString(trees));
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = trees.computeIfAbsent(serial, x-> t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2)
            ans.add(node);
        return uid;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().findDuplicateSubtrees(new LeetcodeCodec().deserialize("1,2,3,4,null,2,4,null,null,4"))));
        System.out.println(JSON.toJSONString(new Solution().findDuplicateSubtrees(new LeetcodeCodec().deserialize("1,2,3,4,null,4"))));
    }


}
