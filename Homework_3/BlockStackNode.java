package Homework_3;

/**
 * Node class for the Single LinkedList used in this assignment. It holds a
 * reference to the next node as well as holds the <code>CodeBlock</code>
 * contained in this Node. Also has all the necessary getter and setter
 * functions needed to work the list.
 * 
 * @author Vincent Zheng
 *      <li>Solar_ID: 113469839</li>
 *      <li>Email: vincent.zheng@stonybrook.edu</li>
 *      <li>Assignment: 3</li>
 *      <li>Course: CSE 214</li>
 *      <li>Recitation: R02</li>
 *      <li>TA: William Simunek</li>
 */
public class BlockStackNode {

    // The reference to the next node in the list
    private BlockStackNode next;

    // The codeBlock stored inside this node
    private CodeBlock codeBlock;

    /**
     * Initializes a new <code>BlockStackNode</code> while setting
     * <code>next</code> and <code>codeBlock</code> to <code>null</code>.
     */
    public BlockStackNode() {
        next = null;
        codeBlock = null;
    }

    /**
     * Initializes a new <code>BlockStackNode</code> and sets the
     * <code>codeBlock</code> to the given value while setting <code>next</code>
     * to <code>null</code>.
     * 
     * @param codeBlock the value to set <code>codeBlock</code> to.
     */
    public BlockStackNode(CodeBlock codeBlock) {
        next = null;
        this.codeBlock = codeBlock;
    }

    /**
     * Returns the current value of <code>next</code>
     * 
     * @return the value of <code>next</code>
     */
    public BlockStackNode getNext() {
        return next;
    }

    /**
     * Returns the current value of <code>codeBlock</code>
     * 
     * @return the value of <code>codeBlock</code>
     */
    public CodeBlock getCodeBlock() {
        return codeBlock;
    }

    /**
     * Sets the value of <code>next</code> to the given value.
     * 
     * @param next the value to set <code>next</code> to.
     */
    public void setNext(BlockStackNode next) {
        this.next = next;
    }

    /**
     * Sets the value of <code>codeBlock</code> to the given value.
     * 
     * @param codeBlock the value to set <code>codeBlock</code> to.
     */
    public void setCodeBlock(CodeBlock codeBlock) {
        this.codeBlock = codeBlock;
    }
}
