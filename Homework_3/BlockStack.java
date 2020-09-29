package Homework_3;

public class BlockStack {

    // The reference to the "top" of the stack or the "head" of the LinkedList
    private BlockStackNode top;

    // The total number of elements currently inside the Stack
    private int size;

    /**
     * Initializes a new <code>BlockStack</code> while setting <code>top</code> to
     * <code>null</code> and <code>size</code> to <code>0</code>.
     */
    public BlockStack() {
        top = null;
        size = 0;
    }

    /**
     * Adds a new node to the top of the list.
     * 
     * @param block the value to set the node's <code>codeBlock</code> to.
     * 
     * @throws IllegalArgumentException thrown if the entered <code>CodeBLock</code>
     *                                  is null
     */
    public void push(CodeBlock block) throws IllegalArgumentException {

        if (block == null) {
            throw new IllegalArgumentException("The CodeBlock entered is null");
        }

        BlockStackNode newNode = new BlockStackNode(block);

        // if the stack is not, empty set the link of the newNode to the current top
        if (top != null) {
            newNode.setNext(top);
        }

        top = newNode;
        size++;
    }

    /**
     * Removes the most recently added <code>BlockStackNode</code> from the list.
     * 
     * @return The <code>BlockStackNode</code> that was jus removed.
     * 
     * @throws IllegalStateException if the list is empty.
     */
    public CodeBlock pop() throws IllegalStateException {

        CodeBlock storeCodeBlock = peek();

        top = top.getNext();

        size--;
        return storeCodeBlock;
    }

    /**
     * Returns the reference to the top <code>top</code> vaLue of the list.
     * 
     * @return the <code>top</code> value of the list.
     * 
     * @throws Ille alStateException if the list is empty.
     */
    public CodeBlock peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("There are no elements in this list!");
        }

        return top.getCodeBlock();
    }

    /**
     * Returns the current size of the list. The size is updated whenever an element
     * is pushed or popped.
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Tells whether or not the list is currently empty
     * 
     * @return <code>true</code>, if the list is empty. <code>false</code>,
     *         otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
