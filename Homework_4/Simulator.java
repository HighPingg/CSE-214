package Homework_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {

    public static final int MAX_PACKETS = 10;

    public double simulate(int numIntRouters, double arrivalProb, int maxBufferSize, int minPacketSize,
            int maxPacketSize, int bandwidth, int duration) {
        Router[] routers = new Router[numIntRouters];
        for (int i = 0; i < routers.length; i++) {
            routers[i] = new Router(maxBufferSize);
        }

        ArrayList<Integer> finishedQueue = new ArrayList<>();

        int totalServiceTime = 0;

        int packetsDropped = 0;

        for (int time = 1; time <= duration; time++) {
            Router dispatcher = new Router(MAX_PACKETS);

            System.out.println("\nTime: " + time);

            for (int i = 0; i < MAX_PACKETS; i++) {
                Packet newArrival = arrival(arrivalProb, maxPacketSize, time);
                if (newArrival != null) {
                    dispatcher.enqueue(newArrival);

                    System.out.printf("Packet %d arrives at dispatcher with size %d.\n", newArrival.getId(),
                            newArrival.getPacketSize());
                }
            }

            while (!dispatcher.isEmpty()) {
                Packet newPacket = dispatcher.dequeue();

                try {
                    int index = Router.sendPacketTo(routers);

                    routers[index].enqueue(newPacket);
                    System.out.printf("Packet %d sent to Router %d.\n", newPacket.getId(), index + 1);
                } catch (IllegalStateException e) {
                    System.out.printf("Network is congested. Packet %d is dropped.\n", newPacket.getId());
                    packetsDropped++;
                } catch (Exception e) {
                    System.out.println("Unexpected Error: " + e);
                }
            }

            for (int i = 0; i < routers.length; i++) {
                if (!routers[i].isEmpty() && routers[i].peek().getTimeArrive() != time) {
                    int timeToDest = routers[i].peek().getTimeToDest();

                    if (timeToDest <= 1 && !finishedQueue.contains(i))
                        finishedQueue.add(i);

                    if (timeToDest != 0) {
                        timeToDest--;
                        routers[i].peek().setTimeToDest(timeToDest);
                    }
                }
            }

            for (int i = 0; i < bandwidth && !finishedQueue.isEmpty(); i++) {
                int indexReachedDest = finishedQueue.remove(0);
                Packet reachedDest = routers[indexReachedDest].dequeue();

                int totalTimeInNetwork = time - reachedDest.getTimeArrive();

                System.out.printf("Packet %d has successfully reached its destination: +%d\n", reachedDest.getId(),
                        totalTimeInNetwork);
            }

            for (int i = 1; i <= routers.length; i++) {
                System.out.printf("R%d: %s\n", i, routers[i - 1]);
            }
        }

        return 0;
    }

    private Packet arrival(double arrivalProb, int maxPacketSize, int timeArrive) {
        if (Math.random() > arrivalProb) {
            return new Packet(++Packet.packetCount, randInt(1, maxPacketSize), timeArrive);
        }

        return null;
    }

    /**
     * Helper method that can generate a random number between minVal and maxVal,
     * inclusively.
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
     * simulator. It will then run the simulator, and outputs the result. Prompt the
     * user whether he or she wants to run another simulation.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("test.txt"));
        // Scanner input = new Scanner(System.in);

        boolean userQuit = false;
        do {
            try {
                System.out.print("Enter the number of Intermediate routers: ");

                int numIntRouters = Integer.parseInt(input.nextLine());
                if (numIntRouters < 1)
                    throw new IllegalInputException("\nNumber of Intermediate Routers can not be less than 1!");

                System.out.print("\nEnter the arrival probability of a packet: ");

                double arrivalProb = Double.parseDouble(input.nextLine());
                if (arrivalProb < 0 || arrivalProb > 1)
                    throw new IllegalInputException("\nArrival Probability Must be a Double between [0, 1]!");

                System.out.print("\nEnter the maximum buffer size of a router: ");

                int maxBufferSize = Integer.parseInt(input.nextLine());
                if (maxBufferSize < 1)
                    throw new IllegalInputException("\nMax Buffer Size can not be less than 1!");

                System.out.print("\nEnter the minimum size of a packet: ");

                int minPacketSize = Integer.parseInt(input.nextLine());
                if (minPacketSize < 1)
                    throw new IllegalInputException("\nMinimum Packet Size can not be less than 1!");

                System.out.print("\nEnter the maximum size of a packet: ");

                int maxPacketSize = Integer.parseInt(input.nextLine());
                if (maxPacketSize < minPacketSize)
                    throw new IllegalInputException("\nMaximum Packet Size can not be less than Minimum Packet Size!");

                System.out.print("\nEnter the bandwidth size: ");

                int bandwidth = Integer.parseInt(input.nextLine());
                if (bandwidth < 1)
                    throw new IllegalInputException("\nBandwidth can not be less than 1!");

                System.out.print("\nEnter the simulation duration: ");

                int duration = Integer.parseInt(input.nextLine());
                if (duration < 0)
                    throw new IllegalInputException("\nDuration can not be less than 0!");

                new Simulator().simulate(numIntRouters, arrivalProb, maxBufferSize, minPacketSize, maxPacketSize,
                        bandwidth, duration);

            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }

            boolean validIn = false;
            do {
                System.out.print("\nDo you want to try another simulation? (y/n): ");
                switch (input.nextLine()) {
                    case "n":

                        userQuit = true;

                    case "y":

                        validIn = true;

                        break;
                    default:

                        System.out.println("Invalid Input! Accepted: y or n.");

                        break;
                }
            } while (!validIn);

        } while (!userQuit);

        input.close();
        System.out.println("\n\nProgram terminating successfully...");
    }
}
