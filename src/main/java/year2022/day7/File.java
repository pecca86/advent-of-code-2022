package year2022.day7;

import year2022.day7.trees.TreeNode;

public class File extends TreeNode {
    private final int size;
    private final String fileName;

    public File(int size, String fileName) {
        this.size = size;
        this.fileName = fileName;
    }

    public int getSize() {
        return size;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "File{" +
                "size=" + size +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
