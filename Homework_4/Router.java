package Homework_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
@SuppressWarnings("all")

/**
 * This class represents a router in the network, which is ultimately a queue.
 * It extends ArrayList and implements Queue. Contin general Queue methods such
 * as enqueue and dequeue and uses the ArrayList storage.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *      <li><b>Solar_ID:</b> 113469839</li>
 *      <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *      <li><b>Assignment:</b> 4</li>
 *      <li><b>Course</b>: CSE 214</li>
 *      <li><b>Recitation</b>: R02</li>
 *      <li><b>TA</b>: William Simunek</li>
 */
public class Router<Packet> extends ArrayList<Packet> implements Queue<Packet> {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The maximum amount of <code>Packets</code> allowed to be stored inside
     * this <code>Router</code>.
     */
    private int maxBufferSize;

    /**
     * Default constructor initializes a new <code>Router</code>. Calls
     * <code>ArrayList</code>'s constructor to initialize a new
     * <code>ArrayList</code>;
     */
    public Router(int maxBufferSize) {
        super();
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
     * Adds a new Packet to the end of the router buffer.
     * 
     * @param packet
     */
    public void enqueue(Packet packet) {
        add(packet);
    }

    /**
     * Removes the first <code>Packet</code> in the router buffer.
     * 
     * @return The element that was just removed from the queue.
     * 
     * @throws IllegalStateException If the router is empty
     *                               (<code>size() == 0</code>).
     */
    public Packet dequeue() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("The Router is Empty!");

        return super.remove(0);
    }

    /**
     * Returns, but does not remove the first <code>Packet</code> in the router
     * buffer.
     * 
     * @return the first <code>Packet</code> in the router buffer or
     *         <code>null</code> the queue is empty.
     */
    @Override
    public Packet peek() {
        if (isEmpty())
            return null;
        return get(0);
    }

    /**
     * Returns the number of <code>Packets</code> that are in the router buffer.
     * 
     * @return the total number of <code>Packets</code> that are in the router
     *         buffer.
     */
    public int size() {
        return super.size();
    }

    /**
     * Determines whether or not the Queue is empty.
     * 
     * @return <b>true</b> if the <code>size</code> of the list is 0.
     *         <li><b>false</b> if the <code>size</code> of the list isn't 0.
     */
    public boolean isEmpty() {
        return super.size() == 0;
    }

    /**
     * Loops through the list Intermediate <code>routers</code>. Find the router
     * with the most free buffer space (contains least Packets), and return the
     * index of the router.
     * 
     * @param routers The list of routers that we are determining which one has
     *                the most free buffer space.
     * 
     * @return The index of the router with the most free buffer space.
     * 
     * @throws IllegalStateException    If all <code>routers</code> are full.
     * 
     * @throws IllegalArgumentException If <code>routers</code> isn't an
     *                                  instance of
     *                                  <code>ArrayList<Router></code>
     */
    public static int sendPacketTo(Collection routers)
                        throws IllegalStateException, IllegalArgumentException
    {
        // Casting into ArrayList
        ArrayList arrayList;
        if (routers instanceof ArrayList) {
            arrayList = (ArrayList) routers;
        } else {
            throw new IllegalArgumentException(
                                        "Parameter must be of type Arraylist!");
        }

        int indexOfLeastBuffer = -1;

        for (int i = 0; i < arrayList.size(); i++) {
            // Casting into a Router
            Router router;
            if (arrayList.get(i) instanceof Router) {
                router = (Router) arrayList.get(i);
            } else {

                throw new IllegalArgumentException(
                                "Parameter must be of type ArrayList<Router>");
            }

            /*
             * Changes the index of the router with the least buffer to i if and
             * only if the router isn't full and it's the first router with
             * space or the current router has more space than the router who's
             * index was stored.
             */

            if (router.size() < router.getMaxBufferSize() && 
                    (indexOfLeastBuffer == -1 || router.size() <
                    ((Router) arrayList.get(indexOfLeastBuffer)).size()))
            {
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

        for (int i = 0; i < size(); i++) {
            // Add the node into the String
            finalString += get(i).toString();

            // If no more nodes, print nothing, else add ", "
            if (i != size() - 1)
                finalString += ", ";
        }

        return finalString + "}";
    }

    /**
     * Appends the Packet into the ArrayList.
     * 
     * @param e The Packet to be added into the ArrayList.
     * 
     * @return true always.
     */
    @Override
    public boolean offer(Packet e) {
        add(e);
        return true;
    }

    /**
     * Retrieves and removes the head of this queue. This method differs from
     * poll() only in that it throws an exception if this queue is empty.
     * 
     * @return the head of this queue.
     * 
     * @throws IllegalStateException If the list is empty.
     */
    @Override
    public Packet remove() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("List is Empty!");
        return get(0);
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this
     * queue is empty.
     * 
     * @return the head of this queue, or null if this queue is empty
     */
    @Override
    public Packet poll() {
        if (isEmpty())
            return null;
        return get(0);
    }

    /**
     * Retrieves, but does not remove, the head of this queue. This method
     * differs from peek only in that it throws an exception if this queue is
     * empty.
     * 
     * @return the head of this queue
     * 
     * @throws IllegalStateException When the List is empty.
     */
    @Override
    public Packet element() throws IllegalStateException {
        if(isEmpty())
            throw new IllegalStateException("The List is Empty!");
        return get(0);
    }
}
