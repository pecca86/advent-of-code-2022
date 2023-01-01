package year2022.day7.trees;

public class BinaryTree {
    TreeNode root;

    // Recursevly traverse using a helper function
    public void  preOrderTraversal() {
        preOrderTraversal(this.root);
    }

    // Print root, then left leaf and right lead
    private void preOrderTraversal(TreeNode root) {
        if (root != null) {
//            System.out.println(root.data + " ");
//            preOrderTraversal(root.left);
//            preOrderTraversal(root.right);
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(this.root);
    }

    private void inOrderTraversal(TreeNode root) {
        if (root != null) {
//            inOrderTraversal(root.left);
//            System.out.println(root.data + " ");
//            inOrderTraversal(root.right);
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(this.root);
    }
    // Root last
    private void postOrderTraversal(TreeNode root) {
        if (root !=null) {
//            postOrderTraversal(root.left);
//            preOrderTraversal(root.right);
//            System.out.println(root.data);
        }
    }
}
