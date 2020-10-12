package Homework_4;

public class RouterNode {

    /**
     * The reference to the <code>next</code> node in the list
     */
    private RouterNode next;

    /**
     * The <code>Packet</code> stored inside this node
     */
    private Packet packet;

    /**
     * Initializes a new <code>RouterNode</code> and sets the <code>packet</code> to
     * the given value while setting <code>next</code> to <code>null</code>.
     * 
     * @param packet the value to set <code>packet</code> to.
     */
    public RouterNode(Packet packet) {
        this.next = null;
        this.packet = packet;
    }

    /**
     * Returns the current value of <code>next</code>
     * 
     * @return the value of <code>next</code>
     */
    public RouterNode getNext() {
        return next;
    }

    /**
     * Returns the current value of <code>packet</code>
     * 
     * @return the value of <code>packet</code>
     */
    public Packet getPacket() {
        return packet;
    }

    /**
     * Sets the value of <code>next</code> to the given value.
     * 
     * @param next the value to set <code>next</code> to.
     */
    public void setNext(RouterNode next) {
        this.next = next;
    }

    /**
     * Sets the value of <code>packet</code> to the given value.
     * 
     * @param packet the value to set <code>packet</code> to.
     */
    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
