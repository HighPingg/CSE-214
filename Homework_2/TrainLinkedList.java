package Homework_2;

public class TrainLinkedList {

    // The first node inside the list
    private TrainCarNode head;

    // The last node inside the list
    private TrainCarNode tail;

    // The current selected node inside the list
    private TrainCarNode cursor;

    // Variables holding the total, length, weight, value, and wheter or not the
    // Train has dangerous goods. This is changed every time a car is added and
    // removed.
    private double totalLength;
    private double totalWeight;
    private double totalValue;
    private int totalCars;
    private boolean hasDanger;

    /**
     * Default constructor initializes a new TrainLinkedList with head, tail and
     * cursor variables set to null and length, weight, and value set to 0.
     * hasDanger is set to false because there are no TrainCars.
     */
    public TrainLinkedList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;

        this.totalLength = 0;
        this.totalWeight = 0;
        this.totalValue = 0;
        this.totalCars = 0;
        this.hasDanger = false;
    }

    /**
     * Returns the total length of the train in meters.
     * 
     * @return The sum of the lengths of each TrainCar in the train.
     */
    public double getLength() {
        return totalLength;
    }

    /**
     * Returns the total weight in tons of the train. Note that the weight of the
     * train is the sum of the weights of each empty TrainCar, plus the weight of
     * the ProductLoad carried by that car.
     * 
     * @return The sum of the weight of each TrainCar plus the sum of the
     *         ProductLoad carried by that car.
     */
    public double getWeight() {
        return totalWeight;
    }

    /**
     * Returns the total value of product carried by the train.
     * 
     * @return The sum of the values of each TrainCar in the train.
     */
    public double getValue() {
        return totalValue;
    }

    /**
     * Determines the number of TrainCar objects currently on the train.
     * 
     * @return The number of TrainCar objects on this train.
     */
    public int size() {
        return totalCars;
    }

    /**
     * Whether or not there is a dangerous product on one of the TrainCar objects on
     * the train.
     * 
     * @return Returns true if the train contains at least one TrainCar carrying a
     *         dangerous ProductLoad, false otherwise.
     */
    public boolean isDangerous() {
        return hasDanger;
    }

    /**
     * Returns a reference to the <code>TrainCar</code> at the node currently
     * referenced by the cursor.
     * 
     * @return The reference to the <code>TrainCar</code> at the node currently
     *         referenced by the cursor.
     * 
     * @throws IllegalStateException if cursor and therefore the list is null
     */
    public TrainCar getCursorData() throws IllegalStateException {
        if (cursor == null)
            throw new IllegalStateException("The cursor is empty!");

        return cursor.getCar();
    }

    /**
     * Places car in the node currently referenced by the cursor.
     * 
     * @param car the TrainCar object that is to be set as the car reference in the
     *            node referenced by cursor
     * 
     * @throws IllegalStateException    if the <code>cursor</code> is empty
     * 
     * @throws IllegalArgumentException if the entered car is null
     */
    public void setCursorData(TrainCar car) throws IllegalStateException, IllegalArgumentException {
        if (cursor == null) {
            throw new IllegalStateException("The cursor is empty!");
        } else if (car == null) {
            throw new IllegalArgumentException("The entered car is empty!");
        }

        this.cursor.setCar(car);
    }

    /**
     * Moves the cursor to the next TrainCarNode
     * 
     * @throws IllegalStateException if the cursor is equal to <code>tail</code>, or
     *                               if the list is empty
     *                               (<code>cursor == null</code>)
     */
    public void cursorForward() throws IllegalStateException {
        if (cursor == tail) {
            throw new IllegalStateException("The cursor is at the tail of the list.");
        } else if (cursor == null) {
            throw new IllegalStateException("The list is empty");
        }

        // advance cursor
        cursor = cursor.getNext();
    }

    /**
     * Moves the cursor to the previous TrainCarNode
     * 
     * @throws IllegalStateException if the cursor is equal to <code>head</code> or
     *                               if the list is empty
     *                               (<code>cursor == null</code>)
     */
    public void cursorBackward() throws IllegalStateException {
        if (cursor == head) {
            throw new IllegalStateException("The cursor is at the head of the list.");
        } else if (cursor == null) {
            throw new IllegalStateException("The list is empty");
        }

        // advance cursor
        cursor = cursor.getPrev();
    }

    /**
     * Inserts a car into the train after the cursor position.
     * 
     * @param car the new <code>TrainCar</code> to be inserted into the train.
     * 
     * @throws IllegalArgumentException Indicates that <code>car</code> is
     *                                  <code>null</code>.
     */
    public void insertAfterCursor(TrainCar car) throws IllegalArgumentException {
        if (car == null) {
            throw new IllegalArgumentException("The car you entered is null!");
        }

        // creates a newNode
        TrainCarNode newNode = new TrainCarNode(car);

        // if list is empty
        if (cursor == null) {

            // set all cursor, head, and tail to newNode
            cursor = newNode;
            head = newNode;
            tail = newNode;

            // increment the counters
            totalWeight += car.getCarWeight();
            totalLength += car.getCarLength();
            totalCars++;

            // if cursor is the tail
        } else if (cursor.getNext() == null) {

            // appends the newNode
            cursor.setNext(newNode);
            newNode.setPrev(cursor);

            // advance the cursor and moves tail backwards
            tail = newNode;
            cursor = newNode;

            // increment the counters
            totalWeight += car.getCarWeight();
            totalLength += car.getCarLength();
            totalCars++;

            // if cursor is at head or middle of list
        } else {

            // insert the newNode into the list
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor);
            cursor.getNext().setPrev(newNode);
            cursor.setNext(newNode);

            // advance cursor
            cursor = newNode;

            // increment the counters
            totalWeight += car.getCarWeight();
            totalLength += car.getCarLength();
            totalCars++;
        }
    }

    /**
     * Adds a new load object to the train at the <code>cursor</code> and updates
     * the counters accordingly
     * 
     * @param load the <code>ProductLoad</code> to add to the <code>TrainCar</code>
     *             at <code>cursor</code>
     * 
     * @throws IllegalArgumentException thrown if the given <code>load</code> is
     *                                  empty
     * 
     * @throws IllegalStateException    thrown if the list is empty or there is
     *                                  already an existing load at the car
     */
    public void addLoadAtCursor(ProductLoad load) throws IllegalArgumentException, IllegalStateException {
        if (load == null) {
            throw new IllegalArgumentException("The load you entered is null!");
        } else if (cursor == null) {
            throw new IllegalStateException("The list is empty!");
        } else if (cursor.getCar().getLoad() != null) {
            throw new IllegalStateException("There already is a load here!");
        }

        // sets the load
        cursor.getCar().setLoad(load);

        // increments counters
        totalWeight += load.getWeight();
        totalValue += load.getValue();

        // sets hasDanger to true if it already has dangerous items or if load contains
        // dangerous items
        hasDanger = (hasDanger || load.isDangerous());
    }

    /**
     * Removes the <code>TrainCarNode</code> referenced by the cursor and returns
     * the <code>TrainCar</code> contained within the node.
     * 
     * @return the TrainCar reference that was just removed
     * 
     * @throws IllegalStateException if the list is empty
     */
    public TrainCar removeCursor() throws IllegalStateException {
        if (cursor == null) {
            throw new IllegalStateException("The list is empty");
        }

        // will hold the removed car which will then be returned
        TrainCar carRemoved;

        // if there is only 1 node in the list
        if (cursor.getNext() == null && cursor.getPrev() == null) {

            // saves the TrainCar reference
            carRemoved = cursor.getCar();

            // removes all references to the node
            head = null;
            tail = null;
            cursor = null;

            // if the cursor is at the head
        } else if (cursor.getPrev() == null) {

            // removes the previous link of the next car
            cursor.getNext().setPrev(null);

            // saves the TrainCar reference
            carRemoved = cursor.getCar();

            // moves the head and cursor to the next train
            head = cursor.getNext();
            cursor = cursor.getNext();

            // if the cursor is at the tail
        } else if (cursor.getNext() == null) {

            // removes the next link of the previous car
            cursor.getPrev().setNext(null);

            // saves the TrainCar reference
            carRemoved = cursor.getCar();

            // moves the head and cursor to the previous train
            tail = cursor.getPrev();
            cursor = cursor.getPrev();

            // if the cursor is in the middle of the list
        } else {

            // make the next and previous nodes point at eachother
            cursor.getNext().setPrev(cursor.getPrev());
            cursor.getPrev().setNext(cursor.getNext());

            // saves the TrainCar reference
            carRemoved = cursor.getCar();

            // advance the cursor
            cursor = cursor.getNext();
        }

        // update counters if car is not null
        if (carRemoved != null) {
            totalCars--;
            totalLength -= carRemoved.getCarLength();
            totalWeight -= carRemoved.getCarWeight();

            // if the load is not null
            if (carRemoved.getLoad() != null) {
                totalWeight -= carRemoved.getLoad().getWeight();
                totalValue -= carRemoved.getLoad().getValue();

                // goes through the updated list to check if there are still dangerous cars in
                // it. if there isn't then update hasDanger
                if (hasDanger) {
                    boolean stillHasDanger = false;
                    TrainCarNode tempPointer = head;

                    while (!stillHasDanger && tempPointer != null) {
                        if (tempPointer.getCar().getLoad() != null && tempPointer.getCar().getLoad().isDangerous()) {
                            stillHasDanger = true;
                        }

                        tempPointer = tempPointer.getNext();
                    }

                    if (!stillHasDanger) {
                        hasDanger = false;
                    }
                }
            }
        }

        return carRemoved;
    }

    public void findProduct(String name) {
        
    }

    /**
     * Removes all dangerous cars from the train using <code>.removeCursor()</code>,
     * maintaining the order of the cars in the train.
     */
    public void removeDangerousCars() {

        cursor = head;

        while (cursor != null) {
            if (cursor.getCar().getLoad() == null) {
                cursor = cursor.getNext();
            } else if (cursor.getCar().getLoad().isDangerous()) {
                removeCursor();
            }
        }

        cursor = head;
    }

    /**
     * Prints a neatly formatted table of the car number, car length, car weight,
     * load name, load weight, load value, and load dangerousness for all of the car
     * on the train.
     */
    public void printManifest() {

        String headerString = ("\n    CAR:                               LOAD:");

        String tableHeaderString = String.format("\n      %s   %s    %s  |    %25s      %s     %s   %s\n", "Num",
                "Length (m)", "Weight (t)", "Name", "Weight (t)", "Value ($)", "Dangerous");

        String bigLine = "    ==================================+===============================================================\n";

        String tableInsides = "";

        // temporatry pointer to go through the whole list
        TrainCarNode tempPointer = head;

        for (int i = 1; tempPointer != null; i++) {

            // prints arrow if this is the node cursor is pointing to otherwise prints a
            // space
            if (tempPointer == cursor) {
                tableInsides += " -> ";
            } else {
                tableInsides += "    ";
            }

            tableInsides += String.format("%5d   %s\n", i, tempPointer.toString());

            // advance tempPointer
            tempPointer = tempPointer.getNext();
        }

        System.out.println(headerString + tableHeaderString + bigLine + tableInsides);
    }

    /**
     * Returns a neatly formatted String representation of the train.
     * 
     * @return A neatly formatted string containing information about the train,
     *         including it's size (number of cars), length in meters, weight in
     *         tons, value in dollars, and whether it is dangerous or not.
     */
    @Override
    public String toString() {

        String isDangerouString;

        if (hasDanger) {
            isDangerouString = "is dangerous";
        } else {
            isDangerouString = "not dangerous";
        }

        return String.format("Train: %d cars, %f meters, %f tons, $%f value, %s", totalCars, totalLength, totalWeight,
                totalValue, isDangerouString);
    }
}