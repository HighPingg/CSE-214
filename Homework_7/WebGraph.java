package Homework_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebGraph {

    public static final int MAX_PAGES = 40;

    private ArrayList<WebPage> pages;

    private ArrayList<ArrayList<Integer>> edges;

    /**
     * Initializes a new WebGraph with new Arraylist and Integer array objects.
     */
    public WebGraph() {
        pages = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /**
     * Constructs a <code>WebGraph</code> object using the indicated files as the
     * source for pages and edges.
     * 
     * @param pagesFile
     * 
     * @param linksFile
     * 
     * @return
     * 
     * @throws IllegalArgumentException
     */
    public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException {
        WebGraph result = new WebGraph();

        try {
            Scanner pagesIn = new Scanner(new File(pagesFile));
            while (pagesIn.hasNextLine()) {
                String[] pagesArr = pagesIn.nextLine().trim().split(" ");
                String url = "";
                ArrayList<String> keywords = new ArrayList<>();

                if (pagesArr.length < 2) {
                    throw new IllegalArgumentException("There Is An Empty Line Or No Keywords On A Line!");
                }

                for (int i = 0; i < pagesArr.length; i++) {
                    if (pagesArr[i].equals(""))
                        throw new IllegalArgumentException("Text File Contains An Empty Keyword!");

                    if (i == 0) {
                        url = pagesArr[0];
                    } else {
                        keywords.add(pagesArr[i]);
                    }
                }

                result.addPage(url, keywords);
            }

            Scanner linksIn = new Scanner(new File(linksFile));
            while (linksIn.hasNextLine()) {
                String[] link = linksIn.nextLine().trim().split(" ");

                if (link.length != 2) {
                    throw new IllegalArgumentException("Links File Is Formatted Incorrectly!");
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
     * @param url
     * 
     * @param keywords
     * 
     * @throws IllegalArgumentException
     */
    public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException {

        // Checking the validity of the url.
        if (url == null || url.equals("")) {
            throw new IllegalArgumentException("Url Cannot Be Empty/Null!");
        }

        if (getIndexFromUrl(url) != -1)
            throw new IllegalArgumentException("Url Already Exists Inside The Graph!");

        // Checking the validity of the keywords.
        if (keywords == null || keywords.size() == 0) {
            throw new IllegalArgumentException("Keywords Cannot Be Empty/Null!");
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
     * @throws IllegalArgumentException If either of the URLs are null or could not
     *                                  be found in pages.
     */
    public void addLink(String source, String destination) throws IllegalArgumentException {
        int indexOfSource = getIndexFromUrl(source);
        int indexOfDestination = getIndexFromUrl(destination);

        if (indexOfSource == -1)
            throw new IllegalArgumentException("Source Cannot Be Found Inside The Graph!");

        if (indexOfDestination == -1)
            throw new IllegalArgumentException("Destination Cannot Be Found Inside The Graph!");

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

            // Removev the page and updates the indices.
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
     * Prints the <code>WebGraph</code> in tabular form.
     */
    public void printTable() {
        ArrayList<WebPage> sortedArrayList = pages;

        String pagesString = "";
        for (WebPage webPage : sortedArrayList) {
            String linkString = "";
            ArrayList<Integer> theseEdges = edges.get(pages.indexOf(webPage));
            for (int i = 0; i < theseEdges.size(); i++) {
                if (theseEdges.get(i) == 1) {
                    if (!linkString.equals(""))
                        linkString += ", ";

                    linkString += i;
                }
            }

            pagesString += webPage.toString().replace("***",
                    String.format(" %-" + (pages.size() * 3 - 2) + "s ", linkString)) + "\n";
        }

        System.out.printf(" %4s   %-20s   %8s   %-" + (pages.size() * 3 - 1) + "s   %s\n", "Index", "URL", "PageRank",
                "Links", "Keywords");
        System.out.println(
                "------------------------------------------------------------------------------------------------------");
        System.out.println(pagesString);
    }

}
