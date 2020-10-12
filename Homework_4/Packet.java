package Homework_4;

public class Packet {

    /**
     * This value is used to assign an id to a newly created packet. It will start
     * with the value 0, and every time a new packet object is created, increment
     * this counter and assign the value as the id of the Packet.
     */
    public static int packetCount = 0;

    /**
     * A unique identifier for the packet. This will be systematically determined by
     * using packetCount.
     */
    private int id;

    /**
     * The size of the packet being sent. This value is randomly determined by the
     * simulator by using the Math.random() method.
     */
    private int packetSize;

    /**
     * The time this Packet is created should be recorded in this variable
     */
    private int timeArrive;

    /**
     * This variable contains the number of simulation units that it takes for a
     * packet to arrive at the destination router. The value will start at one
     * hundredth of the packet size, that is: packetSize/100. At every simulation
     * time unit, this counter will decrease. Once it reaches 0, we can assume that
     * the packet has arrived at the destination.
     */
    private int timeToDest;

    public Packet(int id, int packetSize, int timeArrive) {
        this.id = id;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;

        // Set timeToDest to (packetSize / 100)
        this.timeToDest = packetSize / 100;
    }

    /**
     * Returns the current value of <code>id</code>
     * 
     * @return the value of <code>id</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the current value of <code>packetSize</code>
     * 
     * @return the value of <code>packetSize</code>
     */
    public int getPacketSize() {
        return packetSize;
    }

    /**
     * Returns the current value of <code>timeArrive</code>
     * 
     * @return the value of <code>timeArrive</code>
     */
    public int getTimeArrive() {
        return timeArrive;
    }

    /**
     * Returns the current value of <code>timeToDest</code>
     * 
     * @return the value of <code>timeToDest</code>
     */
    public int getTimeToDest() {
        return timeToDest;
    }

    /**
     * Sets the value of <code>id</code> to the given value.
     * 
     * @param id the value to set <code>id</code> to.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the value of <code>packetSize</code> to the given value.
     * 
     * @param packetSize the value to set <code>packetSize</code> to.
     */
    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    /**
     * Sets the value of <code>timeArrive</code> to the given value.
     * 
     * @param timeArrive the value to set <code>timeArrive</code> to.
     */
    public void setTimeArrive(int timeArrive) {
        this.timeArrive = timeArrive;
    }

    /**
     * Sets the value of <code>timeToDest</code> to the given value.
     * 
     * @param timeToDest the value to set <code>timeToDest</code> to.
     */
    public void setTimeToDest(int timeToDest) {
        this.timeToDest = timeToDest;
    }

    /**
     * Returns a <code>String</code> representation of this Packet.
     * 
     * @return the <code>Packet</code> as a <code>String</code>.
     */
    @Override
    public String toString() {
        return String.format("[%d, %d, %d]", this.id, this.timeArrive, this.timeToDest);
    }
}