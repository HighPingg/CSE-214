package Homework_5;

public class DirectoryNode {

    /**
     * The name of this file/folder.
     */
    private String name;

    /**
     * The left child of this <code>DirectoryNode</code>.
     */
    private DirectoryNode left;

    /**
     * The middle child of this tree node.
     */
    private DirectoryNode middle;

    /**
     * The right child of this tree node.
     */
    private DirectoryNode right;

    /**
     * Differentiates whether or not this node is a file or folder:
     * <ul>
     * <li><code>true</code> if this <code>DirectoryNode</code> is a file.</li>
     * <li><code>false</code> if this <code>DirectoryNode</code> is a folder.</li>
     * </ul>
     */
    private boolean isFile;

    /**
     * Default contructor initializes a new <code>DirectoryNode</code> object with
     * the <code>left</code>, <code>middle</code>, and <code>right</code> references
     * set to <code>null</code> while setting <code>name</code> to an empty
     * <code>String</code> and <code>isFile</code> to <code>true</code>.
     */
    public DirectoryNode() {
        this.name = "";
        this.left = null;
        this.middle = null;
        this.right = null;
        this.isFile = true;
    }

    /**
     * Overloader constructor sets <code>name</code> and <code>isFile</code> to the
     * given values while setting the <code>left</code>, <code>middle</code>, and
     * <code>right</code> references to <code>null</code>.
     * 
     * @param name   The name of this <code>DirectoryNode</code>.
     * 
     * @param isFile Whether or not this <code>DirectoryNode</code> is a file or a
     *               folder.
     */
    public DirectoryNode(String name, boolean isFile) {
        this.name = name;
        this.left = null;
        this.middle = null;
        this.right = null;
        this.isFile = isFile;
    }

    /**
     * Return the current value of <code>name</code> stored in this object.
     * 
     * @return The current value of <code>name</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name to the entered <code>String</code>.
     * 
     * @param name The <code>String</code> to set name to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the left <code>DirectoryNode</code> child.
     * 
     * @return the left <code>DirectoryNode</code> child.
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /**
     * Returns the middle <code>DirectoryNode</code> child.
     * 
     * @return the middle <code>DirectoryNode</code> child.
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /**
     * Returns the right <code>DirectoryNode</code> child.
     * 
     * @return the right <code>DirectoryNode</code> child.
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     * Adds <code>newChild</code> to any of the open child positions of this node
     * (<code>left</code>, <code>middle</code>, or <code>right</code>) checked from
     * left to right order.
     * 
     * @param newNode The new <code>DirectoryNode</code> to add as a child to this
     *                node.
     * 
     * @throws FullDirectoryException Thrown if all child references of this
     *                                directory are occupied.
     * 
     * @throws NotADirectoryException Thrown if the current node is a file, as files
     *                                cannot contain DirectoryNode references (i.e.
     *                                all files are leaves).
     */
    public void addChild(DirectoryNode newNode) throws FullDirectoryException, NotADirectoryException {
        if (isFile)
            throw new NotADirectoryException("The Current Node is a File!");

        // Checks the left, middle and right nodes for an empty spot. If there is, then
        // sets it to newNode, else throws FullDirectoryException
        if (left == null) {
            left = newNode;
            System.out.println("left");
        } else if (middle == null) {
            middle = newNode;

            System.out.println("mid");
        } else if (right == null) {
            right = newNode;
            System.out.println("right");
        } else {
            throw new FullDirectoryException("This Directory is Full!");
        }
    }

    public void printChildren(int depth) {

        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        if (isFile) {
            System.out.println("- " + name);
        } else {
            System.out.println("|- " + name);
        }

        if (left != null) {
            left.printChildren(depth + 1);
        }

        if (middle != null) {
            middle.printChildren(depth + 1);
        }

        if (right != null) {
            right.printChildren(depth + 1);
        }
    }
}