package Homework_7;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {

    public static final String PAGES_FILE = "Homework_7\\given_data\\pages.txt";

    public static final String LINKS_FILE = "Homework_7\\given_data\\links.txt";

    private WebGraph web;

    public SearchEngine() {
        web = new WebGraph();
        web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
    }

    public void action(String input, Scanner userIn) throws IllegalArgumentException {
        switch (input.toUpperCase()) {
            case "AP":
                System.out.print("Enter a URL: ");
                String url = userIn.nextLine();

                if (url.length() == 0)
                    throw new IllegalArgumentException("Url Cannot Be Empty!");

                System.out.print("Enter keywords (space-separated): ");
                String[] keywordsArr = userIn.nextLine().split(" ");

                if (keywordsArr.length == 0)
                    throw new IllegalArgumentException("Url Cannot Be Empty!");

                ArrayList<String> keywords = new ArrayList<>();
                for (String string : keywordsArr) {
                    if (string.length() == 0)
                        throw new IllegalArgumentException("Description Items Cannot Be Empty!");

                    keywords.add(string);
                }

                web.addPage(url, keywords);
                System.out.println("\n" + url + " successuflly added to the WebGraph!");

                break;
            case "RP":
                System.out.print("Enter a URL: ");
                String Url = userIn.nextLine();
                web.removePage(Url);
                System.out.println(Url + " has been removed from the graph!");
                break;
            case "AL":
                System.out.print("Enter a source URL: ");
                String addSource = userIn.nextLine();

                System.out.print("Enter a destination URL: ");
                String addDestination = userIn.nextLine();

                web.addLink(addSource, addDestination);
                System.out.println("\nLink successfully added from " + addSource + " to " + addDestination + "!");
                break;
            case "RL":
                System.out.print("Enter a source URL: ");
                String removeSource = userIn.nextLine();

                System.out.print("Enter a destination URL: ");
                String removeDestination = userIn.nextLine();

                web.removeLink(removeSource, removeDestination);
                System.out.println("\nLink removed from " + removeSource + " to " + removeDestination + "!");
                break;
            case "P":
                System.out.println("\n\t(I) Sort based on index (ASC)");
                System.out.println("\t(U) Sort based on URL (ASC)");
                System.out.println("\t(R) Sort based on rank (DSC)\n");
                System.out.print("Please select an option: ");

                switch (userIn.nextLine().toUpperCase()) {
                    case "I":
                        web.printTable();
                        break;
                    case "U":

                        break;
                    case "R":

                        break;

                    default:
                        System.out.println("ERROR: " + input + "is not a valid option!");
                        break;
                }
                break;
            case "S":
                // TODO implement S
                break;
            default:
                System.out.println("ERROR: " + input + "is not a valid option!");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SearchEngine engine = new SearchEngine();

        boolean userQuit = false;
        do {
            System.out.println("Menu:");
            System.out.println("\t(AP) - Add a new page to the graph.");
            System.out.println("\t(RP) - Remove a page from the graph.");
            System.out.println("\t(AL) - Add a link between pages in the graph.");
            System.out.println("\t(RL) - Remove a link between pages in the graph.");
            System.out.println("\t(P)  - Print the graph.");
            System.out.println("\t(S)  - Search for pages with a keyword.");
            System.out.println("\t(Q)  - Quit.\n");
            System.out.print("Please select an option: ");

            String userIn = input.nextLine();

            if (userIn.toUpperCase().equals("Q")) {
                userQuit = true;
            } else {
                try {
                    engine.action(userIn, input);
                } catch (IllegalArgumentException e) {
                    System.out.println("\nERROR: " + e.getMessage() + "\n");
                }
            }
        } while (!userQuit);

        input.close();
        System.out.println("\nGoodbye.");
    }
}
