package Homework_7;

import java.util.Comparator;

/**
 * Compares the <code>WebPages</code> based on its index. Helps the sort methods
 * sort by increasing order of indices.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *         <li><b>Solar_ID:</b> 113469839</li>
 *         <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *         <li><b>Assignment:</b> 7</li>
 *         <li><b>Course</b>: CSE 214</li>
 *         <li><b>Recitation</b>: R02</li>
 *         <li><b>TA</b>: William Simunek</li>
 */
public class IndexComparator implements Comparator<WebPage> {

    /**
     * Compares the index of two <code>WebPage</code> objects. Takes the index
     * of each <code>WebPage</code> and stores it, then uses the
     * <code>Integer.compareTo()</code> method.
     * 
     * @param o1 The <code>WebPage</code> we are comparing against
     *           <code>o2</code>.
     * 
     * @param o2 The <code>WebPage</code> we are comparing against
     *           <code>o1</code>.
     * 
     * @return <code>0</code> if the index of <code>o1</code> is equal to the
     *         index of <code>o2</code>, a number greater than <code>0</code> if
     *         the index of <code>o1</code> is greater than the index of
     *         <code>o2</code>, and a number less than <code>0</code> if the
     *         index of <code>o1</code> is less than the index of
     *         <code>o2</code>.
     */
    @Override
    public int compare(WebPage o1, WebPage o2) {
        int index1 = ((WebPage) o1).getIndex();
        int index2 = ((WebPage) o2).getIndex();

        return ((Integer) index1).compareTo(index2);
    }

}