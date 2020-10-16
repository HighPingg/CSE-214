package Homework_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * This class contains the main method that tests the simulation. It also
 * contains the simulate class itself and its helper methods and all the
 * variables it needs to properly run the simulation.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *      <li><b>Solar_ID:</b> 113469839</li>
 *      <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *      <li><b>Assignment:</b> 4</li>
 *      <li><b>Course</b>: CSE 214</li>
 *      <li><b>Recitation</b>: R02</li>
 *      <li><b>TA</b>: William Simunek</li>
 */
public class Simulator {

    /**
     * The maximum amount of <code>Packets</code> that can arrive at the
     * <code>dispatcher</code> at one point in time.
     */
    public static final int MAX_PACKETS = 3;

    /**
     * Level 1 <code>Router</code>. New <code>Packets</code> arrive here to be
     * put to the Level 2 <code>routers</code>.
     */
    Router<Packet> dispatcher;

    /**
     * Level 2 <code>routers</code>. <code>Packets</code> are processed here and
     * wait to be shipped off to their destination.
     */
    Collection<Router<Packet>> routers;

    /**
     * Contains the running sum of the total time each <code>Packet</code> is in
     * the network.
     */
    int totalServiceTime;

    /**
     * Contains the total number of packets that has been successfully forwarded
     * to the destination.
     */
    int totalPacketsArrived;

    /**
     * Records the number of packets that have been dropped due to a congested
     * network.
     */
    int packetsDropped;

    /**
     * The probability of a new <code>Packet</code> arriving at the
     * <code>dispatcher</code>.
     */
    double arrivalProb;

    /**
     * The number of Intermediate <code>routers</code> in the network.
     */
    int numIntRouters;

    /**
     * The maximum number of <code>Packets</code> a <code>Router</code> can
     * accommodate for.
     */
    int maxBufferSize;

    /**
     * The minimum size of a <code>Packet</code>.
     */
    int minPacketSize;

    /**
     * The maximum size of a <code>Packet</code>.
     */
    int maxPacketSize;

    /**
     * The maximum number of <code>Packets</code> the Destination
     * <code>Router</code> can accept at a given simulation unit
     */
    int bandwidth;

    /**
     * The number of simulation units
     */
    int duration;

    /**
     * Default contructor initializes a new <code>Simulator</code> object with
     * all member variables set to 0.
     */
    public Simulator() {
        this.totalServiceTime = 0;
        this.packetsDropped = 0;
        this.totalPacketsArrived = 0;
        this.numIntRouters = 0;
        this.arrivalProb = 0;
        this.maxBufferSize = 0;
        this.minPacketSize = 0;
        this.maxPacketSize = 0;
        this.bandwidth = 0;
        this.routers = new ArrayList<Router<Packet>>();
        this.dispatcher = new Router<>(MAX_PACKETS);
    }

    /**
     * Overloader contructor initializes a new <code>Simulator</code> object
     * with all member variables set to its corresponding given value and the
     * rest to 0.
     * 
     * @param numIntRouters the value to set <code>numIntRouters</code> to.
     * 
     * @param arrivalProb   the value to set <code>arrivalProb</code> to.
     * 
     * @param maxBufferSize the value to set <code>maxBufferSize</code> to.
     * 
     * @param minPacketSize the value to set <code>minPacketSize</code> to.
     * 
     * @param maxPacketSize the value to set <code>maxPacketSize</code> to.
     * 
     * @param bandwidth     the value to set <code>bandwidth</code> to.
     */
    public Simulator(int numIntRouters, double arrivalProb, int maxBufferSize,
                    int minPacketSize, int maxPacketSize, int bandwidth)
    {
        this.totalServiceTime = 0;
        this.packetsDropped = 0;
        this.totalPacketsArrived = 0;
        this.numIntRouters = numIntRouters;
        this.arrivalProb = arrivalProb;
        this.maxBufferSize = maxBufferSize;
        this.minPacketSize = minPacketSize;
        this.maxPacketSize = maxPacketSize;
        this.bandwidth = bandwidth;
        this.routers = new ArrayList<Router<Packet>>();
        this.dispatcher = new Router<>(MAX_PACKETS);
    }

    /**
     * Runs the simulator as described in the specs. Calculate and return the
     * average time each packet spends within the network.
     * 
     * @param duration the total number of simulation units that the simulation
     *                 should take.
     * 
     * @return the average time each packet spends within the network.
     */
    public double simulate(int duration) {
        // Reset packetCount to 0 for new simulation
        Packet.packetCount = 0;

        // Populate/Reset the ArrayList to empty Routers
        ArrayList<Router<Packet>> routersArrayList =
                                            (ArrayList<Router<Packet>>)routers;
        for (int i = 0; i < numIntRouters; i++) {
            routersArrayList.add(new Router<Packet>(maxBufferSize));
        }

        /*
         * finishedQueue is used to queue up the indexes of each router when the
         * first element has reached its destination.
         */
        ArrayList<Integer> finishedQueue = new ArrayList<>();

        for (int time = 1; time <= duration; time++) {
            System.out.println("\nTime: " + time);

            /*
             * Adds newly arrived Packets to the dispatcher. Uses helper
             * function arrival to determine whether or not a Packet has arrived
             * and then nitialze a new Packet if it indeed has arrived.
             */
            for (int i = 0; i < MAX_PACKETS; i++) {
                Packet newArrival = arrival(time);
                if (newArrival != null) {
                    dispatcher.enqueue(newArrival);

                    System.out.printf(
                        "Packet %d arrives at dispatcher with size %d.\n",
                        newArrival.getId(), newArrival.getPacketSize());
                }
            }

            /*
             * Empties out dispatcher and uses sendPacketTo to determine what
             * router to send the packet to.
             */
            while (!dispatcher.isEmpty()) {
                Packet newPacket = dispatcher.dequeue();

                try {
                    int index = Router.sendPacketTo(routersArrayList);

                    routersArrayList.get(index).enqueue(newPacket);
                    System.out.printf("Packet %d sent to Router %d.\n",
                                    newPacket.getId(), index + 1);
                } catch (IllegalStateException e) {
                    System.out.printf(
                        "Network is congested. Packet %d is dropped.\n",
                        newPacket.getId());

                    packetsDropped++;
                } catch (Exception e) {
                    System.out.println("Unexpected Error: " + e);
                }
            }

            /*
             * Decreases timeToDest in the first Packet in each Router unless
             * the router is empty or the Packet just arrived. If the time is
             * less than or equal to 1, queue it up in finishedQueue to be
             * dequeued.
             */
            for (int i = 0; i < routersArrayList.size(); i++) {
                if (!routersArrayList.get(i).isEmpty() &&
                            routersArrayList.get(i).peek().getTimeArrive() != time)
                {
                    int timeToDest = routersArrayList.get(i).peek().getTimeToDest();

                    if (timeToDest <= 1 && !finishedQueue.contains(i))
                        finishedQueue.add(i);

                    if (timeToDest != 0) {
                        timeToDest--;
                        routersArrayList.get(i).peek().setTimeToDest(timeToDest);
                    }
                }
            }

            /*
             * Removes the first element of finshedQueue and dequeues the router
             * of that index.
             */
            for (int i = 0; i < bandwidth && !finishedQueue.isEmpty(); i++) {
                int indexReachedDest = finishedQueue.remove(0);
                Packet reachedDest = routersArrayList.get(indexReachedDest).dequeue();

                int totalTimeInNetwork = time - reachedDest.getTimeArrive();

                System.out.printf(
                    "Packet %d has successfully reached its destination: +%d\n",
                    reachedDest.getId(), totalTimeInNetwork);

                totalServiceTime += totalTimeInNetwork;
                totalPacketsArrived++;
            }

            /*
             * Print the routers in the list.
             */
            for (int i = 1; i <= routersArrayList.size(); i++) {
                System.out.printf("R%d: %s\n", i, routersArrayList.get(i - 1));
            }
        }

        // Calculate average service time. Avoiding divide by 0 error.
        double averageServiceTime = 0;
        if (totalPacketsArrived != 0)
            averageServiceTime = (double) totalServiceTime /
                                 (double) totalPacketsArrived;

        System.out.println("\nSimulation Ending...");
        System.out.println("Total service time: " + totalServiceTime);
        System.out.println("Total packets served: " + totalPacketsArrived);
        System.out.printf("Average service time per packet: %.2f\n",
                          averageServiceTime);
        System.out.println("Total packets dropped: " + packetsDropped);

        return averageServiceTime;
    }

    /**
     * Determines whether or not a new <code>Packet</code> has arrived. If yes,
     * then creates a new <code>Packet</code>, else returns <code>null</code>.
     * 
     * @param timeArrive The time unit that the new <code>Packet</code> arrived
     *                   at.
     * 
     * @return A new <code>Packet</code> if a new <code>Packet</code> has
     *         arrived, else <code>null</code>.
     */
    private Packet arrival(int timeArrive) {
        if (Math.random() < arrivalProb) {
            return new Packet(++Packet.packetCount,
                        randInt(minPacketSize, maxPacketSize), timeArrive);
        }

        return null;
    }

    /**
     * Helper method that can generate a random number between minVal and
     * maxVal, inclusively.
     * 
     * @param minVal The lower bound of the random number.
     * 
     * @param maxVal The upper bound of the random number.
     * 
     * @return The random value between [minVal, maxVal].
     */
    private int randInt(int minVal, int maxVal) {
        return (int) (Math.random() * (maxVal - minVal + 1) + minVal);
    }

    /**
     * The <code>main()</code> method will prompt the user for inputs to the
     * simulator. It will then run the simulator, and outputs the result. Prompt
     * the user whether he or she wants to run another simulation.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean userQuit = false;
        do {
            try {
                // getting simulation parameters and checking if theyre correct.
                System.out.print("Enter the number of Intermediate routers: ");

                int numIntRouters = Integer.parseInt(input.nextLine());
                if (numIntRouters < 1)
                    throw new IllegalInputException(
                    "\nNumber of Intermediate Routers can not be less than 1!");

                System.out.print(
                            "\nEnter the arrival probability of a packet: ");

                double arrivalProb = Double.parseDouble(input.nextLine());
                if (arrivalProb < 0 || arrivalProb > 1)
                    throw new IllegalInputException(
                      "\nArrival Probability Must be a Double between [0, 1]!");

                System.out.print(
                            "\nEnter the maximum buffer size of a router: ");

                int maxBufferSize = Integer.parseInt(input.nextLine());
                if (maxBufferSize < 1)
                    throw new IllegalInputException(
                                "\nMax Buffer Size can not be less than 1!");

                System.out.print("\nEnter the minimum size of a packet: ");

                int minPacketSize = Integer.parseInt(input.nextLine());
                if (minPacketSize < 1)
                    throw new IllegalInputException(
                               "\nMinimum Packet Size can not be less than 1!");

                System.out.print("\nEnter the maximum size of a packet: ");

                int maxPacketSize = Integer.parseInt(input.nextLine());
                if (maxPacketSize < minPacketSize)
                    throw new IllegalInputException(
                                "\nMaximum Packet Size can not be less than Min"
                                + "imum Packet Size!");

                System.out.print("\nEnter the bandwidth size: ");

                int bandwidth = Integer.parseInt(input.nextLine());
                if (bandwidth < 1)
                    throw new IllegalInputException(
                                        "\nBandwidth can not be less than 1!");

                System.out.print("\nEnter the simulation duration: ");

                int duration = Integer.parseInt(input.nextLine());
                if (duration < 0)
                    throw new IllegalInputException(
                                        "\nDuration can not be less than 0!");

                // Initializes a new Simulator and calls the simulate method
                Simulator simulator = new Simulator(numIntRouters, arrivalProb,
                                    maxBufferSize, minPacketSize, maxPacketSize,
                                    bandwidth);

                simulator.simulate(duration);

                // Keeps prompting user for an input until a valid one is given.
                boolean validIn = false;
                do {
                    System.out.print("\nDo you want to try another simulation? "
                                    + "(y/n): ");
                    switch (input.nextLine()) {
                        case "n":

                            userQuit = true;

                        case "y":

                            validIn = true;

                            break;
                        default:

                            System.out.println("Invalid Input! Accepted: y or n"
                                            + ".");

                            break;
                    }
                } while (!validIn);

            } catch (IllegalInputException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e);
            }

        } while (!userQuit);

        input.close();
        System.out.println("\n\nProgram terminating successfully...");
    }
}
