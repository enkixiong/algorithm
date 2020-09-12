package week_06.offer.offer36;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

class Solution {

    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        Node[] nodes = dfs(root);
        if(nodes[0] != nodes[1]){
            nodes[0].left = nodes[1];
            nodes[1].right = nodes[0];
        }
        return nodes[0];
    }

    private Node[] dfs(Node node){
        if(node == null){
            return new Node[]{null,null};
        }
        Node[] left = dfs(node.left);
        Node[] right = dfs(node.right);
        if(left[1] != null){
            left[1].right = node;
            node.left = left[1];
        }
        if(right[0] != null){
            node.right = right[0];
            right[0].left = node;
        }
        return new Node[]{left[0] == null ? node : left[0], right[1] == null ? node : right[1]};
    }

    public static void main(String[] args) {
        Node node4 = new Node(4);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node5 = new Node(5);
        Node node1 = new Node(1);
        node4.left = node2;
        node2.left = node1;
        node2.right = node3;
        node4.right = node5;

        System.out.println(new Solution().treeToDoublyList(node4));
    }

}
