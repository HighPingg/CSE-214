package Homework_7;

import java.util.ArrayList;

public class WebGraph {

    public static final int MAX_PAGES = 40;

    private ArrayList<WebPage> pages;

    private int[][] edges;

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
        // TODO finish buildFromFiles()
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
        // TODO finish addPage()
    }

    /**
     * Removes the <code>WebPage</code> from the graph with the given URL.
     * 
     * @param source
     * 
     * @param destination
     * 
     * @throws IllegalArgumentException
     */
    public void addLink(String source, String destination) throws IllegalArgumentException {
        // TODO finish addLink()
    }

    /**
     * Removes the <code>WebPage</code> from the graph with the given URL.
     * 
     * @param url
     */
    public void removePage(String url) {
        // TODO finish removePage()
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
        // TODO finish removeLink()
    }

    /**
     * Calculates and assigns the PageRank for every page in the
     * <code>WebGraph</code> (ran after any change to the graph structure).
     */
    public void updatePageRanks() {
        // TODO finish updatePageRanks()
    }

    /**
     * Prints the <code>WebGraph</code> in tabular form.
     */
    public void printTable() {
        // TODO finish printTable()
    }

}
