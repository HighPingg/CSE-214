package Homework_3;

public class CodeBlock {

    /**
     * The six code blocks that are considered and stored with the String
     * representation of each block.
     * 
     * @DEF "def"
     * @FOR "for",
     * @WHILE "while"
     * @IF "if"
     * @ELIF "elif"
     * @ELSE "else"
     */
    public static enum BLOCK_TYPES {

        DEF("def"), FOR("for"), WHILE("while"), IF("if"), ELIF("elif"), ELSE("else");

        // Strores the String representation of the code block
        private String type;

        /**
         * Constructor that sets the <code>type</code> to the given value
         * 
         * @param type the value to set <code>type</code> to
         */
        private BLOCK_TYPES(String type) {
            this.type = type;
        }

        /**
         * Returns the current value of <code>type</code>
         * 
         * @return the value of <code>type</code>
         */
        public String getType() {
            return type;
        }
    };

    // Keeps track of the nested structure of the blocks
    private String name;

    // Keeps track of the Big-Oh complexity of this block
    private Complexity blockComplexity;

    // Keeps track of the Big-Oh complexity of the highest-order block nested within
    // this block
    private Complexity highestSubComplexity;

    // Used only for while blocks. Stores its loop variable.
    private String loopVariable;

    /**
     * Default constructor initializes a new <code>CodeBlock</code> and sets
     * <code>this.name = null</code>,
     * <code>this.blockComplexity = new Complexity()</code>,
     * <code>this.highestSubComplexity = new Complexity()</code>, and
     * <code>this.loopVariable = null</code>.
     */
    public CodeBlock() {
        this.name = null;
        this.blockComplexity = new Complexity();
        this.highestSubComplexity = new Complexity();
        this.loopVariable = null;
    }

    /**
     * Overloader constructor initializes a new <code>CodeBlock</code> and sets its
     * member variables to the given variables.
     * 
     * @param name                 the value to set <code>name</code> to.
     * 
     * @param blockComplexity      the value to set <code>blockComplexity</code> to.
     * 
     * @param highestSubComplexity the value to set
     *                             <code>highestSubComplexity</code> to.
     */
    public CodeBlock(String name, Complexity blockComplexity, Complexity highestSubComplexity) {

        this.name = name;
        this.blockComplexity = blockComplexity;
        this.highestSubComplexity = highestSubComplexity;
        this.loopVariable = null;
    }

    /**
     * Returns the current value of <code>name</code>
     * 
     * @return the value of <code>name</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current value of <code>blockComplexity</code>
     * 
     * @return the value of <code>blockComplexity</code>
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /**
     * Returns the current value of <code>highestSubComplexity</code>
     * 
     * @return the value of <code>highestSubComplexity</code>
     */
    public Complexity getHighestSubComplexity() {
        return highestSubComplexity;
    }

    /**
     * Returns the current value of <code>loopVaiable</code>
     * 
     * @return the value of <code>loopVariable</code>
     */
    public String getLoopVariable() {
        return loopVariable;
    }

    /**
     * Sets the value of <code>name</code> to the given value.
     * 
     * @param name the value to set <code>name</code> to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the value of <code>blockComplexity</code> to the given value.
     * 
     * @param blockComplexity the value to set <code>blockComplexity</code> to.
     */
    public void setBlockComplexity(Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
    }

    /**
     * Sets the value of <code>highestSubComplexity</code> to the given value.
     * 
     * @param highestSubComplexity the value to set
     *                             <code>highestSubComplexity</code> to.
     */
    public void setHighestSubComplexity(Complexity highestSubComplexity) {
        this.highestSubComplexity = highestSubComplexity;
    }

    /**
     * Sets the value of <code>loopVariable</code> to the given value.
     * 
     * @param loopVariable the value to set <code>loopVariable</code> to.
     */
    public void setLoopVariable(String loopVariable) {
        this.loopVariable = loopVariable;
    }

    /**
     * Returns the total complexity of this block by adding the powers of the outer
     * loop to the highest sub complexity of the inner loops.
     * 
     * @return the total complexity to this <code>CodeBlock</code>
     */
    public Complexity totalComplexity() {
        int nPower = blockComplexity.getNPower() + highestSubComplexity.getNPower();
        int logPower = blockComplexity.getLogPower() + highestSubComplexity.getLogPower();

        return new Complexity(nPower, logPower);
    }
}
