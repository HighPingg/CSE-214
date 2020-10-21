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
     * and both cursor and root reference this node.
     * </p>
     */
    public DirectoryTree() {
        DirectoryNode newNode = new DirectoryNode("root", false);

        root = newNode;
        cursor = newNode;
    }

    /**
     * Moves the cursor to the root node of the tree.
     * 
     * <p>
     * <b>Postcondition:</b> The cursor now references the root node of the tree.
     * </p>
     */
    public void resetCursor() {
        cursor = root;
    }

    public void changeDirectoryHelper(String name) throws NotADirectoryException {
        changeDirectory(name, cursor);
    }

    private void changeDirectory(String name, DirectoryNode nodePtr) throws NotADirectoryException {
        String[] nameArr = name.split("/");

        if (nameArr[0].equals("root")) {
            nodePtr = root;
            nameArr = removeFirstElement(nameArr);

            if (nameArr.length == 0) {
                cursor = nodePtr;
                return;
            }
        }

        for (int i = 0; i < nameArr.length; i++)
            System.out.println(nameArr[i]);

        if (nodePtr.getLeft() != null && nodePtr.getLeft().getName().equals(nameArr[0])) {
            nodePtr = nodePtr.getLeft();
            nameArr = removeFirstElement(nameArr);

            if (nameArr.length != 0) {
                changeDirectory(arrToString(nameArr), nodePtr);
            }

        } else if (nodePtr.getMiddle() != null && nodePtr.getMiddle().getName().equals(nameArr[0])) {
            System.out.println("mid");
            nodePtr = nodePtr.getMiddle();
            nameArr = removeFirstElement(nameArr);

            if (nameArr.length != 0) {
                changeDirectory(arrToString(nameArr), nodePtr);
            }

        } else if (nodePtr.getMiddle() != null && nodePtr.getRight().getName().equals(nameArr[0])) {
            System.out.println("righ");
            nodePtr = nodePtr.getRight();
            nameArr = removeFirstElement(nameArr);

            if (nameArr.length != 0) {
                changeDirectory(arrToString(nameArr), nodePtr);
            }

        } else {
            throw new NotADirectoryException("The System Cannot Find the Given Path.");
        }

        if (nameArr.length == 0)
            cursor = nodePtr;
    }

    private String[] removeFirstElement(String[] arr) {

        String[] arrCopy = new String[arr.length - 1];

        for (int i = 0; i < arrCopy.length; i++) {
            arrCopy[i] = arr[i + 1];
        }

        return arrCopy;
    }

    private String arrToString(String[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                str += arr[i];
            else
                str += arr[i] + "/";
        }
        return str;
    }

    public String presentWorkingDirectory() {
        // TODO Finish presentWorkingDirectory()
    }

    public String listDirectory() {
        String dir = "";

        if (cursor.getLeft() != null)
            dir += cursor.getLeft().getName() + " ";

        if (cursor.getMiddle() != null)
            dir += cursor.getMiddle().getName() + " ";

        if (cursor.getRight() != null)
            dir += cursor.getRight().getName();

        return dir;
    }

    public void printDirectoryTree() {
        root.printChildren(0);
    }

    public void makeDirectory(String name)
            throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
        cursor.addChild(new DirectoryNode(name, false));
    }

    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
        cursor.addChild(new DirectoryNode(name, true));
    }
}
