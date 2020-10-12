package Homework_4;

import java.util.Collection;

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
     * Default constructor initializes a new <code>Router</code>. It sets the
     * <code>front</code> and <code>rear</code> references to <code>null</code>
     * because there are no <code>Packets</code> currently inside the list while
     * setting <code>size</code> to 0.
     */
    public Router() {
        this.front = null;
        this.rear = null;
        this.size = 0;
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

    public static int sendPacketTo(Collection<Router> routers) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
