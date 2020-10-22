package Homework_5;

public class DirectoryTree {

    private DirectoryNode cursor;

    private DirectoryNode root;

    /**
     * Initializes a <code>DirectoryTree</code> object with a single
     * <code>DirectoryNode</code> named <i>"root"</i>.
     * 
     * <p>
     * <b>Postcondition:</b> The tree contains a single DirectoryNode named "root",
     * and both <code>cursor</code> and root reference this node.
     * </p>
     */
    public DirectoryTree() {
        DirectoryNode newNode = new DirectoryNode("root", false);

        root = newNode;
        cursor = newNode;
    }

    /**
     * Moves the <code>cursor</code> to the root node of the tree.
     * 
     * <p>
     * <b>Postcondition:</b> The <code>cursor</code> now references the root node of
     * the tree.
     * </p>
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     * Moves the <code>cursor</code> to the directory with the name indicated by
     * <code>name</code>.
     * 
     * @param name The path of the node.
     * 
     * @throws NotADirectoryException If name references a file or cannot be found.
     */
    public void changeDirectory(String name) throws NotADirectoryException {

        // Splits name into an array by the '/'. nodePtr is a temporary pointer so we
        // don't change cursor unless nodePtr is able to find path to the node.
        String[] nameArr = name.split("/");
        DirectoryNode nodePtr = cursor;

        // if the first node is root, then we set nodePtr to root and then take out root
        // in path using removeFirstElement(). If "root" is the only thing in the path,
        // then we reset cursor and exit without calling the helper.
        if (nameArr[0].equals("root")) {
            nodePtr = root;
            nameArr = removeFirstElement(nameArr);

            if (nameArr.length == 0) {
                resetCursor();
                return;
            }
        }

        changeDirectoryHelper(nameArr, nodePtr);
    }

    /**
     * Helper recursive function that finds a node given a path in
     * <code>nameArr</code> and a temporary <code>nodePtr</code>. Once we find the
     * node and it's not a file, set <code>cursor</code> to that node.
     * 
     * @param nameArr The directory path to the node.
     * 
     * @param nodePtr The temporary pointer used to traverse through the tree.
     * 
     * @throws NotADirectoryException Thrown if the path points towards a file, or
     *                                the path cannot be found.
     */
    private void changeDirectoryHelper(String[] nameArr, DirectoryNode nodePtr) throws NotADirectoryException {

        // foundReference is a flag. True if the path exists, false if we cannot find
        // it. Iterate through all references inside the node and then remove the found
        // path in nameArr, and then call the helper again with the updated nameArr and
        // nodePtr.
        boolean foundReference = false;
        for (int i = 0; !foundReference && i < DirectoryNode.MAX_CHILDREN; i++) {
            DirectoryNode node = nodePtr.getChild(i);

            if (node != null && node.getName().equals(nameArr[0])) {
                foundReference = true;
                nodePtr = node;
                nameArr = removeFirstElement(nameArr);

                if (nameArr.length != 0) {
                    changeDirectoryHelper(nameArr, nodePtr);
                }
            }
        }

        // If we couldn't find the path, throw an error and exit the recursion.
        if (!foundReference)
            throw new NotADirectoryException("The System Cannot Find the Given Path.");

        // We found the directory, we check whether or not it is a file. If it's a file,
        // then we throw an exception and exit the recursion. Otherwise, set the cursor
        // to nodePtr and then exit recursion.
        if (nameArr.length == 0) {
            if (nodePtr.isFile())
                throw new NotADirectoryException("This Path is a File!");

            cursor = nodePtr;
        }
    }

    /**
     * Removes the first element in a given <code>String[]</code> array.
     * 
     * @param arr The array where we want to remove the first <code>String[]</code>
     *            element.
     * 
     * @return The <code>arr</code> with the first element removed.
     */
    private String[] removeFirstElement(String[] arr) {

        String[] arrCopy = new String[arr.length - 1];

        for (int i = 0; i < arrCopy.length; i++) {
            arrCopy[i] = arr[i + 1];
        }

        return arrCopy;
    }

    public String presentWorkingDirectory() {
        // TODO Finish presentWorkingDirectory()
    }

    /**
     * Returns a String containing a space-separated list of names of all the child
     * directories or files of the <code>cursor</code>.
     * 
     * @return the list of children of the <code>cursor</code>.
     */
    public String listDirectory() {
        String dir = "";

        for (int i = 0; i < DirectoryNode.MAX_CHILDREN; i++) {
            if (cursor.getChild(i) != null) {
                dir += cursor.getChild(i).getName() + " ";
            }
        }

        return dir;
    }

    /**
     * Prints a formatted nested list of <code>names</code> of all the nodes in the
     * directory tree, starting from the <code>cursor</code>.
     */
    public void printDirectoryTree() {
        root.printChildren(0);
    }
//TODO Check if name is valid or no
    /**
     * Creates a directory with the indicated <code>name</code> and adds it to the
     * children of the cursor node.
     * 
     * @param name The <code>name</code> of the new directory.
     * 
     * @throws IllegalArgumentException Thrown if the <code>name</code> argument is
     *                                  invalid.
     * 
     * @throws FullDirectoryException   Thrown if all child references of this
     *                                  directory are occupied.
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException {
        try {
            cursor.addChild(new DirectoryNode(name, false));
        } catch (NotADirectoryException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (FullDirectoryException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Creates a file with the indicated <code>name</code> and adds it to the
     * children of the cursor node.
     * 
     * @param name The <code>name</code> of the new file.
     * 
     * @throws IllegalArgumentException Thrown if the <code>name</code> argument is
     *                                  invalid.
     * 
     * @throws FullDirectoryException   Thrown if all child references of this
     *                                  directory are occupied.
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException {
        try {
            cursor.addChild(new DirectoryNode(name, true));
        } catch (NotADirectoryException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (FullDirectoryException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }
}
