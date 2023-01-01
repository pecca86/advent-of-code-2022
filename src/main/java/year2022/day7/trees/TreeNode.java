package year2022.day7.trees;

import year2022.day7.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode {
    TreeNode parent;
    List<TreeNode> children;
    String name;
    List<File> files;
    int folderSize;

    public void addChild(TreeNode child) {
        if ( this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public void addFile(File file) {
        if ( this.files == null) {
            this.files = new ArrayList<>();
        }
        folderSize += file.getSize();
        this.files.add(file);
    }

    public String getName() {
        return this.name;
    }

    public List<TreeNode> getChildren() {
        if (this.children == null ) {
            return Collections.emptyList();
        }
        return this.children;
    }

    public List<File> getFiles() {
        if (this.files == null) {
            return Collections.emptyList();
        }
        return files;
    }

    public TreeNode getParent() {
        return parent;
    }

    public int getFolderSize() {
        return folderSize;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "name='" + name + '\'' +
                '}';
    }
}
