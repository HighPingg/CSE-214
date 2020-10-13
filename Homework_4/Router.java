package Homework_4;

public class Router {

    /**
     * The reference to the <code>front</code> of the Queue or the head of the
     * LinkedList. We dequeue from here.
     */
    private RouterNode front;

    /**
     * The reference to the <code>rear</code> of the Queue or the head of the
     * LinkedList. We enqueue from here.
     */
    private RouterNode rear;

    /**
     * The total number of <code>Packets</code> inside this <code>Router</code>.
     */
    private int size;

    /**
     * The maximum amount of <code>Packets</code> allowed to be stored inside this
     * <code>Router</code>.
     */
    private int maxBufferSize;

    /**
     * Default constructor initializes a new <code>Router</code>. It sets the
     * <code>front</code> and <code>rear</code> references to <code>null</code>
     * because there are no <code>Packets</code> currently inside the list while
     * setting <code>size</code> to 0.
     */
    public Router(int maxBufferSize) {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.maxBufferSize = maxBufferSize;
    }

    /**
     * Returns the current value of <code>maxBufferSize</code>
     * 
     * @return the value of <code>maxBufferSize</code>
     */
    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    /**
     * Adds a new Packet to the <code>rear</code> of the router buffer and
     * increments <code>size</code> by 1.
     * 
     * @param p the <code>Packet</code> we want to add into the Queue.
     */
    public void enqueue(Packet p) {
        RouterNode newNode = new RouterNode(p);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }

        size++;
    }

    /**
     * Removes the first <code>Packet</code> in the router buffer and decrements
     * <code>size</code> by 1.
     * 
     * @return The <code>Packet</code> that was just removed from the Queue.
     * 
     * @throws IllegalStateException Thrown if the Queue is empty.
     */
    public Packet dequeue() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("Cannot Dequeue. This Router is Empty!");

        // Return this value. This is the front of the Queue
        Packet packet = peek();

        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.getNext();
        }

        size--;

        return packet;
    }

    /**
     * Returns, but does not remove the first <code>Packet</code> in the router
     * buffer.
     * 
     * @return The first <code>Packet</code> in the router buffer.
     */
    public Packet peek() {
        return front.getPacket();
    }

    /**
     * Returns the number of <code>Packets</code> that are in the router buffer.
     * 
     * @return the total number of <code>Packets</code> that are in the router
     *         buffer.
     */
    public int size() {
        return size;
    }

    /**
     * Determines whether or no the Queue is empty.
     * 
     * @return <b>true</b> if the <code>size</code> of the list is 0.
     *         <li><b>false</b> if the <code>size</code> of the list isn't 0.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Loops through the list Intermediate routers. Find the router with the most
     * free buffer space (contains least Packets), and return the index of the
     * router.
     * 
     * @param routers The list of routers that we are determining which one has the
     *                most free buffer space.
     * 
     * @return The index of the router with the most free buffer space.
     * 
     * @throws IllegalStateException If all routers are full.
     */
    public static int sendPacketTo(Router[] routers) throws IllegalStateException {
        int indexOfLeastBuffer = -1;

        for (int i = 0; i < routers.length; i++) {
            /*
             * Changes the index of the router with the least buffer to i if and only if the
             * router isn't full and it's the first router with space or the current router
             * has more space than the router who's index was stored.
             */
            if (routers[i].size() < routers[i].getMaxBufferSize()
                    && (indexOfLeastBuffer == -1 || routers[i].size() < routers[indexOfLeastBuffer].size())) {

                indexOfLeastBuffer = i;
            }
        }

        // if indexOfLeastBuffer hasn't updated, then all routers are full.
        if (indexOfLeastBuffer == -1)
            throw new IllegalStateException("All Routers Are Full!");

        return indexOfLeastBuffer;

    }

    /**
     * Returns a <code>String</code> representation of the router buffer in the
     * following format: {[packet1], [packet2], ... , [packetN]}.
     * 
     * @return the <code>String</code> representation of the router buffer.
     */
    @Override
    public String toString() {
        String finalString = "{";

        RouterNode nodePtr = front;
        while (nodePtr != null) {
            // Add the node into the String
            finalString += nodePtr.getPacket().toString();

            // Move to the next node
            nodePtr = nodePtr.getNext();

            // If no more nodes, print nothing, else add ", "
            if (nodePtr != null)
                finalString += ", ";
        }

        return finalString + "}";
    }
}
