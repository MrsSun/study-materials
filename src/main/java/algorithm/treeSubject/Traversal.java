package algorithm.treeSubject;

import com.sun.tools.javac.util.ListBuffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description
 * @Author lichao11@xiaomi.com
 * @Date 2024/12/26
 */
public class Traversal {
    /**
     * 前序遍历
     */
    public static void leftTraversal(TreeNode root) {
        if (root == null){
            return;
        }
        System.out.print(root.getVal());
        leftTraversal(root.getLeft());
        leftTraversal(root.getRight());
    }

    /**
     * 层序遍历
     */
    public static void levelTraversal(TreeNode root){
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0){
            int size = queue.size();
            while (size-- > 0){
                TreeNode node = queue.poll();
                if (node.getLeft() != null){
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null){
                    queue.add(node.getRight());
                }
                System.out.print(node.getVal()+",");
            }
            System.out.print("\n");
        }
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        levelTraversal(node1);
    }

}
