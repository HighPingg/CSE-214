package Homework_5;

/**
 * Implements a tree of <code>DirectoryNodes</code>. The class contains a
 * reference to the <code>root</code> of the tree, a <code>cursor</code> for the
 * present working directory, and various methods for insertion, deletion,
 * moving, changing directories, and printing trees/children as well as their
 * helper methods.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 5</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
public class DirectoryTree {

    /**
     * The current node that cursor is pointing towards.
     */
    private DirectoryNode cursor;

    /**
     * The path of the node that cursor is referencing.
     */
    private String presentWorkingDirectory;

    /**
     * The root node of this tree.
     */
    private DirectoryNode root;

    /**
     * Initializes a <code>DirectoryTree</code> object with a single
     * <code>DirectoryNode</code> named <i>"root"</i> and sets
     * <code>presentWorkingDirectory</code> to the name of the <code>root</code>
     * node.
     * 
     * <p>
     * <b>Postcondition:</b> The tree contains a single DirectoryNode named
     * "root", and both <code>cursor</code> and root reference this node.
     * </p>
     */
    public DirectoryTree() {
        DirectoryNode newNode = new DirectoryNode("root", false);

        presentWorkingDirectory = newNode.getName();
        root = newNode;
        cursor = newNode;
    }

    /**
     * Moves the <code>cursor</code> to the root node of the tree and sets
     * <code>presentWorkingDirectory</code> to the name of the <code>root</code>
     * node.
     * 
     * <p>
     * <b>Postcondition:</b> The <code>cursor</code> now references the root
     * node of the tree.
     * </p>
     */
    public void resetCursor() {
        cursor = root;
        presentWorkingDirectory = root.getName();
    }

    /**
     * Moves the <code>cursor</code> to the directory with the name indicated by
     * <code>name</code> and updates <code>presentWorkingDirectory</code> if the
     * directory was successfully found.
     * 
     * @param name The path of the node.
     * 
     * @throws NotADirectoryException If name references a file or cannot be
     *                                found.
     */
    public void changeDirectory(String name) throws NotADirectoryException {

        // Splits name into an array by the '/'. nodePtr is a temporary pointer
        // so we don't change cursor unless nodePtr is able to find path to the
        // node.
        String[] nameArr = name.split("/");
        DirectoryNode nodePtr = cursor;

        // if the first node is root, then we set nodePtr to root and then take
        // out root in path using removeFirstElement(). If "root" is the only
        // thing in the path, then we reset cursor and exit without calling the
        // helper.
        if (nameArr[0].equals("root")) {
            nodePtr = root;
            nameArr = removeFirstElement(nameArr);

            if (nameArr.length == 0) {
                resetCursor();
                return;
            }
        }

        changeDirectoryHelper(nameArr, nodePtr);

        // Gets rid of the '/' at the end if there is one
        if (name.substring(name.length() - 1).equals("/")) {
            name = name.substring(0, name.length() - 1);
        }

        // Updating presentWorkingDirectory. If it's an absolute path, then sets
        // presentWorkingDirectory to name. Else append name to
        // presentWorkingDirectory.
        if (name.length() > root.getName().length()
                && name.substring(0, root.getName().length())
                .equals(root.getName()))
        {
            presentWorkingDirectory = name;
        } else {
            presentWorkingDirectory += "/" + name;
        }
    }

    /**
     * Helper recursive function that finds a node given a path in
     * <code>nameArr</code> and a temporary <code>nodePtr</code>. Once we find
     * the node and it's not a file, set <code>cursor</code> to that node.
     * 
     * @param nameArr The directory path to the node.
     * 
     * @param nodePtr The temporary pointer used to traverse through the tree.
     * 
     * @throws NotADirectoryException Thrown if the path points towards a file,
     *                                or the path cannot be found.
     */
    private void changeDirectoryHelper(String[] nameArr, DirectoryNode nodePtr)
                                            throws NotADirectoryException
    {

        // foundReference is a flag. True if the path exists, false if we cannot
        // find it. Iterate through all references inside the node and then
        // remove the found path in nameArr, and then call the helper again with
        // the updated nameArr and nodePtr.
        boolean foundReference = false;
        for (int i = 0; !foundReference && i < DirectoryNode.MAX_CHILDREN; i++)
        {
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
            throw new NotADirectoryException(
                            "The System Cannot Find the Given Path.");

        // We found the directory, we check whether or not it is a file. If it's
        // a file, then we throw an exception and exit the recursion. Otherwise,
        // set the cursor to nodePtr and then exit recursion.
        if (nameArr.length == 0) {
            if (nodePtr.isFile())
                throw new NotADirectoryException("This Path is a File!");

            cursor = nodePtr;
        }
    }

    /**
     * Removes the first element in a given <code>String[]</code> array.
     * 
     * @param arr The array where we want to remove the first
     *            <code>String[]</code> element.
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

    /**
     * Changes the <code>cursor</code> to the parent of the current node. This
     * is done by using <code>presentWorkingDirectory</code>, removing the last
     * <code>name</code> from it and changing directory to it.
     * 
     * @throws IllegalStateException  If the <code>cursor</code> is already at
     *                                the <code>root</code>.
     * 
     * @throws NotADirectoryException If the directory can't be found or the
     *                                path leads to a file.
     */
    public void moveToPreviousNode() throws IllegalStateException,
                                            NotADirectoryException
    {
        if (cursor == root)
            throw new IllegalStateException("The Cursor is at the root");

        changeDirectory(removeLastDirectory(presentWorkingDirectory));
    }

    /**
     * Returns a String containing the path of directory names from the root
     * node of the tree to the cursor, with each name separated by a forward
     * slash "/".
     */
    public String presentWorkingDirectory() {
        return presentWorkingDirectory;
    }

    /**
     * Returns a String containing a space-separated list of names of all the
     * child directories or files of the <code>cursor</code>.
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
     * Prints a formatted nested list of <code>names</code> of all the nodes in
     * the directory tree, starting from the <code>cursor</code>.
     */
    public void printDirectoryTree() {
        root.printChildren(0);
    }

    /**
     * Creates a directory with the indicated <code>name</code> and adds it to
     * the children of the cursor node.
     * 
     * @param name The <code>name</code> of the new directory.
     * 
     * @throws IllegalArgumentException Thrown if the <code>name</code> argument
     *                                  is invalid.
     * 
     * @throws FullDirectoryException   Thrown if all child references of this
     *                                  directory are occupied.
     */
    public void makeDirectory(String name) throws IllegalArgumentException,
                                                  FullDirectoryException
    {
        if (name.contains(" ") || name.contains("/"))
            throw new IllegalArgumentException("The entered name is illegal!");

        // Catch and print NotADirectoryException, but thorws the rest.
        try {
            cursor.addChild(new DirectoryNode(name, false));
        } catch (NotADirectoryException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (FullDirectoryException e) {
            throw e;
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
     * @throws IllegalArgumentException Thrown if the <code>name</code> argument
     *                                  is invalid.
     * 
     * @throws FullDirectoryException   Thrown if all child references of this
     *                                  directory are occupied.
     */
    public void makeFile(String name) throws IllegalArgumentException,
                                             FullDirectoryException
    {
        if (name.contains(" ") || name.contains("/"))
            throw new IllegalArgumentException("The entered name is illegal!");

        // Catch and print NotADirectoryException, but thorws the rest.
        try {
            cursor.addChild(new DirectoryNode(name, true));
        } catch (NotADirectoryException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (FullDirectoryException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Finds a node in the tree given its name. Uses its helper function to do
     * the searching.
     * 
     * @param name The name of the node we are trying to find.
     * 
     * @return The path to the node if it is found in the tree.
     * 
     * @throws IllegalArgumentException If the node cannot be found in the tree.
     */
    public String findInTree(String name) throws IllegalArgumentException {
        if (name.contains("/") || name.contains(" "))
            throw new IllegalArgumentException(
                                    "name Contains Illegal Characters!");

        // If the correct IllegalCallerException is thrown, return the stored 
        // path in message.
        try {
            findInTreeHelper(root, "", name);
        } catch (IllegalCallerException e) {
            return e.getMessage();
        } catch (Exception e) {
            throw e;
        }

        // If nothing has happened after caling findInTreeHelper(), then the
        // node wasn't found and throw the exception.
        throw new IllegalArgumentException("No such file/directory exists!");
    }

    /**
     * Recursively finds the the node that matches the given <code>name</code>.
     * Throws an <code>IllegalCallerException</code> if the node is found and
     * stores the path to the node in the message of the
     * <code>IllegalCallerException</code>. Else if the node is not found do
     * nothing.
     * 
     * @param nodePtr   The current node that nodePtr is on.
     * 
     * @param pathTaken The path taken to get to nodePtr.
     * 
     * @param name      The name of the node we are trying to find.
     */
    private void findInTreeHelper(DirectoryNode nodePtr, String pathTaken,
                                                            String name)
    {
        // If the node is found, throw an exception with the path to it as the
        // message and stop the function.
        if (nodePtr.getName().equals(name)) {
            throw new IllegalCallerException(
                                    (pathTaken + "/" + name).substring(1));
        }

        // Goes through all children and checks whether or not they match the
        // name.
        for (int i = 0; i < DirectoryNode.MAX_CHILDREN; i++) {
            if (nodePtr.getChild(i) != null) {
                findInTreeHelper(nodePtr.getChild(i),
                                    pathTaken + "/" + nodePtr.getName(), name);
            }
        }
    }

    /**
     * Removes the last reference in the given path.
     * 
     * @param str The path to remove the last reference.
     * 
     * @return The path with the removed reference.
     * 
     * @throws IllegalArgumentException If the <code>String</code> is empty.
     */
    private String removeLastDirectory(String str) throws
                                                IllegalArgumentException
    {
        if (str.equals(" "))
            throw new IllegalArgumentException("Empty Path!");

        int indexOfLastSlash = str.length() - 1;
        while (str.charAt(indexOfLastSlash) != '/') {
            indexOfLastSlash--;
        }

        return str.substring(0, indexOfLastSlash);
    }

    /**
     * Moves the node <code>from</code> is referencing to the <code>to</code>
     * directory. Maintains the node that <code>cursor</code> is currently
     * pointed towards.
     * 
     * @param from The node that we want to move.
     * 
     * @param to   The directory that we want to move the node to.
     * 
     * @throws NotADirectoryException If the nodes cannot be found or if
     *                                <code>to</code> references a file.
     * 
     * @throws FullDirectoryException If the <code>to</code> node is full.
     */
    public void moveDirectory(String from, String to) throws
                                NotADirectoryException, FullDirectoryException
    {
        DirectoryNode returnTo = cursor;

        // Get the name of the node we are moving
        String[] arr = from.split("/");
        String name = arr[arr.length - 1];

        try {
            // Change directory to the node before the one we want to move to
            // because it may be a file and an error is thrown, but its parent
            // node must be a directory.
            changeDirectory(removeLastDirectory(from));

            // Moves the cursor up to the node we want to remove if it can be
            // found. If it cannot be found, then throw an error.
            for (int i = 0; i < DirectoryNode.MAX_CHILDREN; i++) {
                if (cursor.getChild(i) != null &&
                                cursor.getChild(i).getName().equals(name))
                {
                    cursor = cursor.getChild(i);
                    break;
                } else if (i == DirectoryNode.MAX_CHILDREN - 1) {
                    throw new NotADirectoryException(
                                    "The System Cannot Find the Given Path!");
                }
            }

            // Save the location of the node we want to remove and change the
            // cursor to the move to location.
            DirectoryNode fromNode = cursor;
            changeDirectory(to);

            // Add the node to the to node and remove the from node. This is
            // node by moving the cursor to from's parent and calling
            // DirectoryNode.remove().
            cursor.addChild(fromNode);
            changeDirectory(removeLastDirectory(from));
            cursor.removeNode(name);

            // Restore the location of cursor to the return location. Also
            // restores presentWorkingDirectory because using changeDirectory().
            changeDirectory(findInTree(returnTo.getName()));
        } catch (Exception e) {

            // Restore the location of cursor to the return location. Also
            // restores presentWorkingDirectory because using changeDirectory().
            changeDirectory(findInTree(returnTo.getName()));

            throw e;
        }
    }
}
