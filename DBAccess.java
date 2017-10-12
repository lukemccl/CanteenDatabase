/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthprac;

//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DevUser
 */
public class DBAccess {

    //sets global variables for class, commonly needed varibales between methods
    static Connection m_Connection = null;
    static Statement m_Statement = null;
    static ResultSet m_ResultSet = null;
    static String m_Driver = "org.apache.derby.jdbc.ClientDriver";
    //static String m_Url = "jdbc:derby://localhost:1527/DBHOME";
    static String m_Url = "jdbc:derby://localhost:1527/DBSCHOOL";
    static int curRow = 0;

    public static float getBalance(int user) throws Exception {
        //initialise method variables
        float balance = 0;
        String query = "";
        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //creates query and executes within database
        m_Statement = m_Connection.createStatement();
        query = "SELECT BALANCE FROM ACCOUNTS WHERE ID = '" + Integer.toString(user) + "'";

        m_ResultSet = m_Statement.executeQuery(query);

        //finds balance value within resultset and returns it
        while (m_ResultSet.next()) {
            balance = m_ResultSet.getFloat(1);

        }
        return balance;
    }

    public static void setBalance(double input, int user) throws Exception {
        //initialise required variables
        String query = "";
        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //sets up query and executes within database
        m_Statement = m_Connection.createStatement();
        query = "UPDATE ACCOUNTS SET BALANCE = " + input + " WHERE ID = '" + Integer.toString(user) + "'";

        m_Statement.executeUpdate(query);
        System.out.println("Balance set");
    }

    public static float getPrice(int ID) throws Exception {
        //initialises variables to be used in method
        float price = 0;
        String query = "";
        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //creates query and executes
        m_Statement = m_Connection.createStatement();
        query = "SELECT PRICE FROM PRODUCTS WHERE ID = '" + Integer.toString(ID) + "'";

        m_ResultSet = m_Statement.executeQuery(query);

        //finds required value and returns
        while (m_ResultSet.next()) {
            price = m_ResultSet.getFloat(1);
        }
        return price;
    }

    public static int getSold(int ID) throws Exception {
        //initialises requried variables
        int sold = 0;
        String query = "";
        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //sets up necessary query and executes
        m_Statement = m_Connection.createStatement();
        query = "SELECT SOLD FROM PRODUCTS WHERE ID = '" + Integer.toString(ID) + "'";

        m_ResultSet = m_Statement.executeQuery(query);

        //finds correct value in resultset and returns it
        while (m_ResultSet.next()) {
            sold = m_ResultSet.getInt(1);
        }
        return sold;
    }

    public static void setSold(int ID, int inp) throws Exception {
        //initialises required variable and connection
        String query = "";
        int sold = inp + getSold(ID);

        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //sets correct query and executes
        m_Statement = m_Connection.createStatement();
        query = "UPDATE PRODUCTS SET SOLD = " + sold + " WHERE ID = '" + Integer.toString(ID) + "'";

        m_Statement.executeUpdate(query);
    }

    public static void setOrder(boolean[] order, String day) {
        try {
            //sets up necessary values and initialises specially called resultset type
            m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");
            m_Statement = m_Connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs;
            rs = m_Statement.executeQuery(
                    "SELECT * FROM ORDERS");

            //selects next row to write to in database
            rs.last();
            curRow = rs.getRow();
            rs.moveToInsertRow();
            
            
            //updates result set to include data for new entry in table
            rs.updateInt(1, (curRow + 1));
            rs.updateString(2, day);
            rs.updateString(3, Integer.toString(SecScreen.user));
            for (int i = 4; i < 10; i++) {
                rs.updateInt(i, Boolean.compare(order[i - 4], false));
            }
            
            //inserts row to table and closes resultset
            rs.insertRow();
            m_Statement.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DefaultTableModel getProducts() throws Exception {
        //creates defaulttablemodel to write to
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "NAME", "PRICE", "SOLD"}, 0);

        //sets up required connection and variables
        String query = "";
        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //creates query and executes
        m_Statement = m_Connection.createStatement();
        query = "SELECT * FROM PRODUCTS";

        m_ResultSet = m_Statement.executeQuery(query);

        //allocates parts of the resultset into correct format within DefaultTableModel model
        while (m_ResultSet.next()) {
            String d = m_ResultSet.getString("ID");
            String e = m_ResultSet.getString("NAME");
            String f = m_ResultSet.getString("PRICE");
            String g = m_ResultSet.getString("SOLD");
            model.addRow(new Object[]{d, e, f, g});
        }

        //return model;
        return model;
    }

    //initial getOrders method to get whole table of orders
    public static ResultSet getOrders() throws Exception {
        //sets up variables and connection
        String query = "";
        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //creates and executes query
        m_Statement = m_Connection.createStatement();
        query = "SELECT * FROM ORDERS";

        m_ResultSet = m_Statement.executeQuery(query);

        //returns whole resultset recieved
        return m_ResultSet;
    }

    //search lookup getOrders method to get specific results for searches
    public static ResultSet getOrders(String search, boolean day) throws Exception {
        //required variables and connection set up
        String query = "";
        Class.forName(m_Driver);
        m_Connection = DriverManager.getConnection(m_Url, "luke", "pass");

        //selects which query to use based on circumstance and executes
        m_Statement = m_Connection.createStatement();
        if (day == true) {
            //if the search is for a day
            query = "SELECT * FROM ORDERS WHERE DAY = '" + search.toLowerCase() + "'";
        } else {
            //if the search is for a product
            query = "SELECT * FROM ORDERS WHERE " + search.toUpperCase() + " = 1";
        }

        m_ResultSet = m_Statement.executeQuery(query);
        
        //returns entire resultset
        return m_ResultSet;
    }

    //method to convert boolean value to integer
    public int BooltoInt(boolean b) {
        if (b) {
            return 1;
        }
        return 0;
    }

}
