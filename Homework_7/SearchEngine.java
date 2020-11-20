package Homework_7;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <code>SearchEngine</code> is a class that initializes a <code>WebGraph</code>
 * from the links given from the <code>PAGES_FILE</code> and
 * <code>LINKS_FILE</code>. It then provides functionality to the user by
 * allowing them to input and perform different actions on the
 * <code>WebGraph</code>.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 7</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
public class SearchEngine {

    /**
     * The link to the pages text file to take the webpages from.
     */
    public static final String PAGES_FILE = "Homework_7\\given_data\\pages.txt";

    /**
     * The link to the links text file to take the edges from.
     */
    public static final String LINKS_FILE = "Homework_7\\given_data\\links.txt";

    /**
     * The web graph of WebPages of this <code>SearchEngine</code>.
     */
    private WebGraph web;

    /**
     * Initializes a new <code>SearchEngine</code>. Calls the
     * <code>WebGraph</code> constructor to initialize <code>web</code> with a
     * new <code>WebGraph</code> and also tries to build from the two files
     * using <code>WebGraph.buildFromFiles()</code>.
     */
    public SearchEngine() {
        web = new WebGraph();
        web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
    }

    /**
     * Does something according to what <code>input</code> is determined by a
     * <code>switch</code>. It can add or remove a page from <code>web</code>,
     * add or remove a link from <code>web</code>, print a sorted version of the
     * <code>web</code> table, or search the <code>web</code> for a certain
     * keyword.
     * 
     * @param input  The input choice of the user.
     * 
     * @param userIn The input stream to take <code>nextLine()</code> inputs
     *               from.
     * 
     * @throws IllegalArgumentException If any of the entered inputs from userIn
     *                                  are invalid to be caught inside
     *                                  <code>main()</code>.
     */
    public void action(String input, Scanner userIn) throws
                                                    IllegalArgumentException
    {
        switch (input.toUpperCase()) {
            case "AP":

                // Takes the next input as the url and checks whether or not
                // it's an empty string.
                System.out.print("Enter a URL: ");
                String url = userIn.nextLine();

                if (url.length() == 0)
                    throw new IllegalArgumentException("Url Cannot Be Empty!");

                // Then takes the next input as keywords and splits them by the
                // whitespace.
                System.out.print("Enter keywords (space-separated): ");
                String[] keywordsArr = userIn.nextLine().split(" ");

                if (keywordsArr.length == 0)
                    throw new IllegalArgumentException("Url Cannot Be Empty!");

                // Adds all of the entered keywords from the array to an
                // ArrayList while also checking whether or not the strings are
                // empty.
                ArrayList<String> keywords = new ArrayList<>();
                for (String string : keywordsArr) {
                    if (string.length() == 0)
                        throw new IllegalArgumentException(
                                        "Description Items Cannot Be Empty!");

                    keywords.add(string);
                }

                web.addPage(url, keywords);
                System.out.println(
                        "\n" + url + " successuflly added to the WebGraph!\n");

                break;
            case "RP":

                // Takes in the next input as the url and removes the page
                // associated with that url.
                System.out.print("Enter a URL: ");
                String Url = userIn.nextLine();
                web.removePage(Url);
                System.out.println(
                            "\n" + Url + " has been removed from the graph!\n");

                break;
            case "AL":

                // Takes in the inputs as the link of the source and the link of
                // the destination and adds a link between the 2 nodes.
                System.out.print("Enter a source URL: ");
                String addSource = userIn.nextLine();

                System.out.print("Enter a destination URL: ");
                String addDestination = userIn.nextLine();

                web.addLink(addSource, addDestination);
                System.out.println("\nLink successfully added from " + addSource
                                    + " to " + addDestination + "!\n");

                break;
            case "RL":

                // Takes in the inputs as the link of the source and the link of
                // the destination and removes the link between the 2 nodes.
                System.out.print("Enter a source URL: ");
                String removeSource = userIn.nextLine();

                System.out.print("Enter a destination URL: ");
                String removeDestination = userIn.nextLine();

                web.removeLink(removeSource, removeDestination);
                System.out.println("\nLink removed from " + removeSource +
                                        " to " + removeDestination + "!\n");

                break;
            case "P":

                // If user indicated they want to print the table, then show
                // them the menu of what to sort by. Then takes the next input
                // and puts it in a switch.
                System.out.println("\n\t(I) Sort based on index (ASC)");
                System.out.println("\t(U) Sort based on URL (ASC)");
                System.out.println("\t(R) Sort based on rank (DSC)\n");
                System.out.print("Please select an option: ");

                // Everything other than sorting by index, we sort the pages
                // list to either sorted by rank or url, then sort back to
                // ascending index.
                switch (userIn.nextLine().toUpperCase()) {
                    case "I":
                        web.printTable();
                        break;
                    case "U":
                        web.printTable(new URLComparator());
                        break;
                    case "R":
                        web.printTable(new RankComparator());
                        break;

                    default:
                        System.out.println("ERROR: " + input +
                                            "is not a valid option!");
                        break;
                }

                break;
            case "S":

                // Takes in a keyword, checks if it is empty, then calls the
                // search method and prints the results.
                System.out.print("Search keyword: ");
                String keyword = userIn.nextLine();

                if (keyword.length() == 0) {
                    throw new IllegalArgumentException(
                                                "Keyword Cannot Be Empty!");
                }

                ArrayList<WebPage> matches = web.searchKeyword(keyword);
                System.out.printf("\n Rank   PageRank    URL\n");
                System.out.println("-------------------------------------------"
                                    + "--");
                for (int i = 0; i < matches.size(); i++) {
                    System.out.printf(" %4d | %8d | %s\n", (i + 1),
                            matches.get(i).getRank(), matches.get(i).getUrl());
                }
                System.out.println();

                break;

            default:
                System.out.println(
                                "ERROR: " + input + "is not a valid option!");
                break;
        }
    }

    /**
     * Constructs a new <code>WebGraph</code> by calling the
     * <code>SearchEngine</code> constructor using the files indicated by the
     * static constant Strings <code>PAGES_FILE</code> and
     * <code>LINKS_FILE</code>. The user is then be presented with a menu
     * allowing them to add a page, remove a page, add a link, remove a link,
     * print the graph, search for a keyword, or quit the program implemented by
     * calling <code>SearchEngine.action()</code>.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SearchEngine engine = new SearchEngine();

        boolean userQuit = false;
        do {
            System.out.println("Menu:");
            System.out.println("\t(AP) - Add a new page to the graph.");
            System.out.println("\t(RP) - Remove a page from the graph.");
            System.out.println(
                            "\t(AL) - Add a link between pages in the graph.");
            System.out.println(
                          "\t(RL) - Remove a link between pages in the graph.");
            System.out.println("\t(P)  - Print the graph.");
            System.out.println("\t(S)  - Search for pages with a keyword.");
            System.out.println("\t(Q)  - Quit.\n");
            System.out.print("Please select an option: ");

            String userIn = input.nextLine();

            // Checks if the user entered "Q". If it did, then stops the loop by
            // setting userQuit to true, else just calls action().
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
