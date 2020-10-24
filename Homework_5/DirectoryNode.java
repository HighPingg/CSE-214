package Homework_5;

/**
 * Represents a node in the file tree. The <code>DirectoryNode</code> class
 * contains <code>MAX_CHILDREN</code> references. In addition, the class
 * contains a <code>String</code> member variable <code>name</code>, which
 * indicates the name of the node in the tree. Also has a getter and remove
 * fundtion for the children, a funtion to print the subtree of this node, and a
 * <code>boolean isFile</code> reference to determine whether or not this node
 * is a file or directory.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 5</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
public class DirectoryNode {

    /**
     * The maximum number of children a node can have.
     */
    public static final int MAX_CHILDREN = 10;

    /**
     * The name of this file/folder.
     */
    private String name;

    /**
     * The children of this <code>DirectoryNode</code>.
     */
    private DirectoryNode[] children;

    /**
     * Differentiates whether or not this node is a file or folder:
     * <ul>
     * <li><code>true</code> if this <code>DirectoryNode</code> is a file.</li>
     * <li><code>false</code> if this <code>DirectoryNode</code> is a folder.
     * </li>
     * </ul>
     */
    private boolean isFile;

    /**
     * Default contructor initializes a new <code>DirectoryNode</code> object
     * with the <code>children</code> set to <code>null</code> while setting
     * <code>name</code> to an empty <code>String</code> and <code>isFile</code>
     * to <code>true</code>.
     */
    public DirectoryNode() {
        this.name = "";
        this.children = new DirectoryNode[MAX_CHILDREN];
        this.isFile = true;
    }

    /**
     * Overloader constructor sets <code>name</code> and <code>isFile</code> to
     * the given values while setting the <code>children</code> to
     * <code>null</code>.
     * 
     * @param name   The name of this <code>DirectoryNode</code>.
     * 
     * @param isFile Whether or not this <code>DirectoryNode</code> is a file or
     *               a folder.
     * 
     * @throws IllegalArgumentException If <code>name</code> contains illegal
     *                                  characters ' ' or '/'.
     */
    public DirectoryNode(String name, boolean isFile) throws
                                                IllegalArgumentException
    {
        if (name.contains(" ") || name.contains("/"))
            throw new IllegalArgumentException(
                                        "name Contains Illegal Characters!");

        this.name = name;
        this.children = new DirectoryNode[MAX_CHILDREN];
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
     * Return whether or not this <code>DirectoryNode</code> is a file or not.
     * 
     * @return <b>true</b> if this <code>DirectoryNode</code> is a file.
     *         <li><b>false</b> if this <code>DirectoryNode</code> is a folder.
     *         </li>
     */
    public boolean isFile() {
        return isFile;
    }

    /**
     * Returns the <code>DirectoryNode</code> child corresponding to
     * <code>index</code>.
     * 
     * @return the <code>DirectoryNode</code> child at <code>index</code>.
     * 
     * @throws IllegalArgumentException If <code>index</code> is out of bounds.
     */
    public DirectoryNode getChild(int index) throws IllegalArgumentException {
        if (index < 0 || index >= MAX_CHILDREN)
            throw new IllegalArgumentException(
                                    "The index of getChild() Out-Of-Bounds");

        return children[index];
    }

    /**
     * Adds <code>newChild</code> to any of the open child positions of this
     * node <code>children</code> checked from left to right order.
     * 
     * @param newNode The new <code>DirectoryNode</code> to add as a child to
     *                this node.
     * 
     * @throws FullDirectoryException Thrown if all child references of this
     *                                directory are occupied.
     * 
     * @throws NotADirectoryException Thrown if the current node is a file, as
     *                                files cannot contain DirectoryNode
     *                                references (i.e. all files are leaves).
     */
    public void addChild(DirectoryNode newNode) throws FullDirectoryException,
                                                NotADirectoryException
    {
        if (isFile)
            throw new NotADirectoryException("The Current Node is a File!");

        // Checks children for an empty spot. If there is, then
        // sets it to newNode, else throws FullDirectoryException

        for (int i = 0; i < children.length; i++) {
            if (children[i] == null) {
                children[i] = newNode;
                return;
            }
        }

        throw new FullDirectoryException("This Directory is Full!");
    }

    /**
     * Removes the node in children corresponding to the given name.
     * 
     * @param name The name of the node to remove.
     * 
     * @throws NotADirectoryException If the node cannot be found in the
     *                                children of this node.
     */
    public void removeNode(String name) throws NotADirectoryException {
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null && children[i].getName().equals(name)) {
                children[i] = null;
                return;
            }
        }

        throw new NotADirectoryException("The Node Isn't In the Parent!");
    }

    /**
     * Recursively prints out the current <code>DirectoryNode</code> and its
     * entire subtree.
     * 
     * @param depth the depth that the current <code>DirectoryNode</code> is in.
     *              This corresponds to the number of tabs that will be in front
     *              of the <code>DirectoryNode</code> when it prints.
     */
    public void printChildren(int depth) {

        // Print out spaces in front of the file/folder according to its depth
        // in the tree.
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        if (isFile) {
            System.out.println("- " + name);
        } else {
            System.out.println("|- " + name);
        }

        // Loops through children and prints out all its children
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                children[i].printChildren(depth + 1);
            }
        }
    }

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
}