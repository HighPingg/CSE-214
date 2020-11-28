package Homework_7;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * The Graphical Interface of the WebGraph using a simple <code>JFrame</code>
 * and <code>JTable</code>. It, however, doesn't have the functionality to be
 * directly edited, but rather must be interacted with through the menu printed
 * in the console.
 * 
 * @author <b>Name</b>: Vincent Zheng
 *      <li><b>Solar_ID:</b> 113469839</li>
 *      <li><b>Email:</b> vincent.zheng@stonybrook.edu</li>
 *      <li><b>Assignment:</b> 7</li>
 *      <li><b>Course</b>: CSE 214</li>
 *      <li><b>Recitation</b>: R02</li>
 *      <li><b>TA</b>: William Simunek</li>
 */
public class GUI {

    /**
     * The names of all the columns of the table.
     */
    private final String[] columnNames = {"Index", "URL", "PageRank", "Links",
                                         "Keywords"};

    /**
     * The <code>JFrame</code> the table will be put into.
     */
    private JFrame frame;

    /**
     * The <code>JTable</code> that the <code>WebGraph</code> information will
     * be put onto.
     */
    private JTable table;

    /**
     * The <code>DefaultTableModel</code> of <code>table</code>. Will help us
     * add and update entries of the table.
     */
    private DefaultTableModel model;

    public GUI(String[][] data) {

        // Initializing the table and setting it to the correct settings.
        table = new JTable(){

            private static final long serialVersionUID = 1L;
            
            /**
             * Disallows the user to edit entries on the table.
             */
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);

        // Setting the model of table to a new DefaultTableModel and adding the
        // columns to the model.
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table.setModel(model);

        // Loops through all elements of data and adding them as rows to the
        // table.
        for (String[] strings : data) {
            addRow(strings);
        }

        // Adding the table to a JScrollPane and then adding that to the JFrame.
        // Then changing the settings of the JFrame.
        JScrollPane scrollPane = new JScrollPane(table);
        frame = new JFrame();
        frame.setTitle("Vincent Zheng's Search Engine");
        frame.add(scrollPane);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Adds a new row to the table.
     * 
     * @param newRow The array of Strings that will populate that new row.
     */
    public void addRow(String[] newRow) {
        model.addRow(newRow);
    }

    /**
     * Refreshes the table with the updated data.
     * 
     * @param data The updated data that will populate the table.
     */
    public void refresh(String[][] data) {

        // Erase everything on the table, then goes through all elements of the
        // data array and adds it to the table.
        model.setRowCount(0);

        for (String[] strings : data) {
            model.addRow(strings);
        }
    }

    /**
     * Kills the JFrame to close the application.
     */
    public void stopProgram() {
        frame.setVisible(false);
        frame.dispose();
    }

}
