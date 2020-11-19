package Homework_7;

import java.util.ArrayList;

public class WebPage {

    /**
     * The URL of the website.
     */
    private String url;

    /**
     * The index of the website inside the graph.
     */
    private int index;

    /**
     * Represents the rank of this website.
     */
    private int rank;

    /**
     * The keywords associated with this webpage that describes it.
     */
    private ArrayList<String> keywords;

    /**
     * Constructor initializes a new <code>WebPage</code> with default variables.
     */
    public WebPage() {
        this.url = "";
        this.index = -1;
        this.rank = 0;
        this.keywords = new ArrayList<>();
    }

    /**
     * Overloaded contructor initializes a new WebPage with member variables equal
     * to the given parameters.
     * 
     * @param url      The URL of the website.
     * 
     * @param index    The index of the website inside the graph.
     * 
     * @param keywords The keywords associated with this webpage that describes it.
     */
    public WebPage(String url, int index, ArrayList<String> keywords) {
        this.url = url;
        this.index = index;
        this.rank = 0;
        this.keywords = keywords;
    }

    /**
     * Return the current value of <code>url</code> stored in this object.
     * 
     * @return The current value of <code>url</code>.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Return the current value of <code>index</code> stored in this object.
     * 
     * @return The current value of <code>index</code>.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Return the current value of <code>rank</code> stored in this object.
     * 
     * @return The current value of <code>rank</code>.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Return the current value of <code>keywords</code> stored in this object.
     * 
     * @return The current value of <code>keywords</code>.
     */
    public ArrayList<String> getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of <code>url</code> to the entered <code>String</code>.
     * 
     * @param url The <code>String</code> to set <code>url</code> to.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets the value of <code>index</code> to the entered <code>int</code>.
     * 
     * @param index The <code>int</code> to set <code>index</code> to.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Sets the value of <code>rank</code> to the entered <code>int</code>.
     * 
     * @param rank The <code>int</code> to set <code>rank</code> to.
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Sets the value of <code>keywords</code> to the entered
     * <code>ArrayList</code>.
     * 
     * @param keywords The <code>ArrayList<String></code> to set
     *                 <code>keywords</code> to.
     */
    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns string of data members in tabular form.
     */
    @Override
    public String toString() {
    
        // Add a ", " before the keyword as long as it's not the first element in the
        // list.
        String keywordString = "";
        for (String keyword : keywords) {
            if (!keywordString.equals("")) {
                keywordString += ", ";
            }

            keywordString += keyword;
        }


        return String.format(" %4d | %20s | %8d |***| %s", index, url, rank, keywordString);
    }

}