package Homework_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Organizes <code> WebPage </code> objects into a directed graph. The WebPages
 * are thrown into an <code> ArrayList </code> pages and the 2D
 * <code> Arraylist </code> edges represents the directed edges between a source
 * node and a destination node. It provides public methods to add and remove a
 * link, add and remove a page, etc.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 7</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
public class WebGraph {

    /**
     * The maximum number of WebPages allowed inside the graph.
     */
    public static final int MAX_PAGES = 40;

    /**
     * The <code>ArrayList</code> of all of the pages inside the graph.
     */
    private ArrayList<WebPage> pages;

    /**
     * The 2D <code>ArrayList</code> representing the edges between the nodes.
     */
    private ArrayList<ArrayList<Integer>> edges;

    /**
     * Initializes a new WebGraph with new Arraylist and Integer array objects.
     */
    public WebGraph() {
        pages = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /**
     * Returns the amount of pages currently in the <code>WebGraph</code>.
     * 
     * @return the size of <code>pages</code>.
     */
    public int getSize() {
        return pages.size();
    }

    /**
     * Constructs a <code>WebGraph</code> object using the indicated files as
     * the source for pages and edges.
     * 
     * @param pagesFile String of the relative path to the file containing the
     *                  page information.
     * 
     * @param linksFile String of the relative path to the file containing the
     *                  link information.
     * 
     * @return The WebGraph constructed from the text files.
     * 
     * @throws IllegalArgumentException Thrown if either of the files does not
     *                                  reference a valid text file, or if the
     *                                  files are not formatted correctly.
     */
    public static WebGraph buildFromFiles(String pagesFile, String linksFile)
                                                throws IllegalArgumentException
    {
        WebGraph result = new WebGraph();

        try {
            // Taking in the pages in pagesFile and adding them to the graph.
            Scanner pagesIn = new Scanner(new File(pagesFile));
            while (pagesIn.hasNextLine()) {

                // Splits each line of pagesFile by the whitespace. Takes the
                // first element and stores it as the url and sets everything
                // else as a keyword.
                String[] pagesArr = pagesIn.nextLine().trim().split(" ");
                String url = "";
                ArrayList<String> keywords = new ArrayList<>();

                if (pagesArr.length < 2) {
                    throw new IllegalArgumentException(
                            "There Is An Empty Line Or No Keywords On A Line!");
                }

                for (int i = 0; i < pagesArr.length; i++) {
                    if (pagesArr[i].equals(""))
                        throw new IllegalArgumentException(
                                        "Text File Contains An Empty Keyword!");

                    if (i == 0) {
                        url = pagesArr[0];
                    } else {
                        keywords.add(pagesArr[i]);
                    }
                }

                result.addPage(url, keywords);
            }

            // Taking in the links in linksFile and checking whether or not
            // there are only 2 elements in each line separated by a whitespace
            // and calls the addLink() method.
            Scanner linksIn = new Scanner(new File(linksFile));
            while (linksIn.hasNextLine()) {
                String[] link = linksIn.nextLine().trim().split(" ");

                if (link.length != 2) {
                    throw new IllegalArgumentException(
                                        "Links File Is Formatted Incorrectly!");
                }

                result.addLink(link[0], link[1]);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Pages Text File Not Found!");
        }

        return result;
    }

    /**
     * Adds a page to the <code>WebGraph</code>.
     * 
     * @param url      The url of the <code>WebPage</code> we want to add.
     * 
     * @param keywords The keywords associated with the <code>WebPage</code>.
     * 
     * @throws IllegalArgumentException If <code>url</code> is not unique and
     *                                  already exists in the graph, or if
     *                                  either argument is <code>null</code>.
     */
    public void addPage(String url, ArrayList<String> keywords) throws
                                                        IllegalArgumentException
    {
        if (pages.size() == MAX_PAGES)
            throw new IllegalArgumentException(
                                        "The Graph is Already At Capacity!");

        // Checking the validity of the url.
        if (url == null || url.equals("")) {
            throw new IllegalArgumentException("Url Cannot Be Empty/Null!");
        }

        if (getIndexFromUrl(url) != -1)
            throw new IllegalArgumentException(
                                    "Url Already Exists Inside The Graph!");

        // Checking the validity of the keywords.
        if (keywords == null || keywords.size() == 0) {
            throw new IllegalArgumentException(
                                    "Keywords Cannot Be Empty/Null!");
        }

        pages.add(new WebPage(url, pages.size(), keywords));

        // Updating edges with '0's.
        for (ArrayList<Integer> links : edges) {
            links.add(0);
        }
        edges.add(new ArrayList<>());
        for (int i = 0; i < edges.size(); i++) {
            edges.get(edges.size() - 1).add(0);
        }
    }

    /**
     * Removes the <code>WebPage</code> from the graph with the given URL.
     * 
     * @param source      The URL of the page which contains the hyperlink to
     *                    <code>destination</code>.
     * 
     * @param destination The URL of the page which the hyperlink points to.
     * 
     * @throws IllegalArgumentException If either of the URLs are null or could
     *                                  not be found in pages.
     */
    public void addLink(String source, String destination) throws
                                                    IllegalArgumentException
    {
        int indexOfSource = getIndexFromUrl(source);
        int indexOfDestination = getIndexFromUrl(destination);

        if (indexOfSource == -1)
            throw new IllegalArgumentException(
                                "Source Cannot Be Found Inside The Graph!");

        if (indexOfDestination == -1)
            throw new IllegalArgumentException(
                            "Destination Cannot Be Found Inside The Graph!");

        edges.get(indexOfSource).set(indexOfDestination, 1);
        updatePageRanks();
    }

    /**
     * Removes the <code>WebPage</code> from the graph with the given URL.
     * 
     * @param url The Url of the <code>WebPage</code> we want to remove from the
     *            list.
     */
    public void removePage(String url) {
        int index = getIndexFromUrl(url);
        if (index != -1) {
            // Removing the edges to and from the url.
            edges.remove(index);
            for (ArrayList<Integer> arrayList : edges) {
                arrayList.remove(index);
            }

            // Removes the page and updates the indices.
            pages.remove(index);
            for (int i = 0; i < pages.size(); i++) {
                pages.get(i).setIndex(i);
            }
            updatePageRanks();
        }
    }

    /**
     * Removes the link from <code>WebPage</code> with the URL indicated by
     * <code>source</code> to the <code>WebPage</code> with the URL indicated by
     * <code>destination</code>.
     * 
     * @param source
     * 
     * @param destination
     */
    public void removeLink(String source, String destination) {
        int indexOfSource = getIndexFromUrl(source);
        int indexOfDestination = getIndexFromUrl(destination);

        if (indexOfSource != -1 && indexOfDestination != -1) {
            edges.get(indexOfSource).set(indexOfDestination, 0);
            updatePageRanks();
        }
    }

    /**
     * Calculates and assigns the PageRank for every page in the
     * <code>WebGraph</code> (ran after any change to the graph structure).
     */
    public void updatePageRanks() {

        // For every element in pages, check the destination column
        // corresponding to the page and counts how many 1s are there.
        for (int i = 0; i < pages.size(); i++) {
            int rank = 0;
            for (ArrayList<Integer> links : edges) {
                if (links.get(i).equals(1))
                    rank++;
            }

            pages.get(i).setRank(rank);
        }
    }

    /**
     * Returns the index of the given Url inside the given list.
     * 
     * @param url The url of the WebPage we want to find.
     * 
     * @return <b>-1</b> if the url cannot be found.
     *         <li><b>else</b> the index of the url in the graph.</li>
     */
    private int getIndexFromUrl(String url) {
        for (WebPage webPage : pages) {
            if (webPage.getUrl().equals(url))
                return pages.indexOf(webPage);
        }

        return -1;
    }

    /**
     * Prints the <code>WebGraph</code> in tabular form sorted by index.
     */
    public void printTable() {
        String pagesString = "";
        for (WebPage webPage : pages) {

            // Takes the source row of edges matrix and adds all of the links it
            // has to linkString separated by ", ".
            String linkString = "";
            ArrayList<Integer> theseEdges = edges.get(pages.indexOf(webPage));
            for (int i = 0; i < theseEdges.size(); i++) {
                if (theseEdges.get(i) != 0) {
                    if (!linkString.equals(""))
                        linkString += ", ";

                    linkString += i;
                }
            }

            // Uses the toString method on the webpage and replaces the
            // placeholder "***" with the links generated above. The size of the
            // Links space scales with the size of pages.
            pagesString += webPage.toString().replace("***",
                                String.format(" %-" + (pages.size() * 3 - 2) +
                                "s ", linkString)) + "\n";
        }

        System.out.printf(" %4s   %-20s   %8s   %-" + (pages.size() * 3 - 1) 
                            + "s   %s\n", "Index", "URL", "PageRank",
                            "Links", "Keywords");
        System.out.println(
                "--------------------------------------------------------------"
                + "----------------------------------------");
        System.out.println(pagesString + "\n");
    }

    /**
     * Prints the <code>WebGraph</code> in tabular form sorted by the
     * comparator.
     */
    public void printTable(Comparator<WebPage> comparator) {
        HashMap<String, String> pagesString = new HashMap<>();
        for (WebPage webPage : pages) {

            // Takes the source row of edges matrix and adds all of the links it
            // has to linkString separated by ", ".
            String linkString = "";
            ArrayList<Integer> theseEdges = edges.get(pages.indexOf(webPage));
            for (int i = 0; i < theseEdges.size(); i++) {
                if (theseEdges.get(i) != 0) {
                    if (!linkString.equals(""))
                        linkString += ", ";

                    linkString += i;
                }
            }

            // Uses the toString method on the webpage and replaces the
            // placeholder "***" with the links generated above. The size of the
            // Links space scales with the size of pages. Then puts it into a
            // HashMap with the url as the key.
            pagesString.put(webPage.getUrl(),
                    webPage.toString().replace("***", String.format(" %-" +
                    (pages.size() * 3 - 2) + "s ", linkString)));
        }

        System.out.printf(" %4s   %-20s   %8s   %-" + (pages.size() * 3 - 1) +
                                    "s   %s\n", "Index", "URL", "PageRank",
                                    "Links", "Keywords");
        System.out.println(
                "--------------------------------------------------------------"
                + "----------------------------------------");

        // Sorts the array with comparator and then prints the according table
        // according to the sorted ArrayList. After that, it sorts it back by
        // index.
        sortTable(comparator);
        for (WebPage webPage : pages) {
            System.out.println(pagesString.get(webPage.getUrl()));
        }
        sortTable(new IndexComparator());

        System.out.println();
    }

    /**
     * Returns a <code>String[][]</code> representation of this
     * <code>WebGraph</code>.
     * 
     * @return The 2D array with each row having the info of each
     *         <code>WebPage</code> sorted by indices.
     */
    public String[][] toStringArray() {
        String[][] result = new String[pages.size()][];

        // Goes through every element in pages and calls its toStringArray()
        // method. Index 3 is empty because we haven't determined its links yet
        for (int i = 0; i < result.length; i++) {
            result[i] = pages.get(i).toStringArray();

            // Sets linkString equal to a String representation of all the
            // WebPages this page is linked to (separated by ", ").
            String linkString = "";
            for (int link = 0; link < edges.get(i).size(); link++) {
                if (edges.get(i).get(link) != 0) {
                    if (!linkString.equals(""))
                        linkString += ", ";

                    linkString += link;
                }
            }
            
            result[i][3] = linkString;
        }

        return result;
    }

    /**
     * Sorts <code>pages</code> using the given <code>comparator</code>.
     * 
     * @param comparator The <code>comparator</code> we are going to sort
     *                   <code>pages</code> with.
     */
    public void sortTable(Comparator<WebPage> comparator) {
        Collections.sort(pages, comparator);
    }

    /**
     * Returns an <code>Arraylist</code> of type <code>WebPage</code> of
     * WebPages with the keyword in its description sorted using its ranks from
     * highest to lowest.
     * 
     * @param keyword The keyword we are searching <code>pages</code> for.
     * 
     * @return The <code>ArrayList</code> of <code>WebPage</code> with the
     *         matching keyword.
     */
    public ArrayList<WebPage> searchKeyword(String keyword) {
        ArrayList<WebPage> matches = new ArrayList<>();

        for (WebPage webPage : pages) {
            if (webPage.getKeywords().contains(keyword))
                matches.add(webPage);
        }

        Collections.sort(matches, new RankComparator());
        return matches;
    }
}
