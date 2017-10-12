/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthprac;

import javax.swing.JOptionPane;

/**
 *
 * @author DevUser
 */
public class Order {

    //creates and initialises variables and arrays 
    //to be used throughout the object
    int FPOrder = 0;
    int HPOrder = 0;
    int SSOrder = 0;
    int JOrder = 0;
    int MOrder = 0;
    int SROrder = 0;
    int[] ordarray = new int[6];
    boolean[] ordbool = new boolean[6];
    String Day = null;

    //constructor method to update the array storing order and to hold 
    //the day of the order
    public Order(String day) {

        ordarray[0] = FPOrder;
        ordarray[1] = HPOrder;
        ordarray[2] = MOrder;
        ordarray[3] = SROrder;
        ordarray[4] = SSOrder;
        ordarray[5] = JOrder;
        Day = day;

    }

    public float getCost() throws Exception {
        float cost = 0;
        //cost = number of product * price of that product (From database) 
        //for every product available
        cost = (FPOrder * DBAccess.getPrice(1))
                + (HPOrder * DBAccess.getPrice(2))
                + (MOrder * DBAccess.getPrice(3))
                + (SROrder * DBAccess.getPrice(4))
                + (SSOrder * DBAccess.getPrice(5))
                + (JOrder * DBAccess.getPrice(6));
        return cost;
    }

    //reupdates the order array
    public void updateOrder() {
        ordarray[0] = FPOrder;
        ordarray[1] = HPOrder;
        ordarray[2] = MOrder;
        ordarray[3] = SROrder;
        ordarray[4] = SSOrder;
        ordarray[5] = JOrder;
    }

    //makes sure at least one prouct is selected before order is processed
    public void confirm() throws Exception {
        //runs checkOrdered method as check
        if(checkOrdered()){
            //executes code to add order to database
        for (int i = 0; i < ordarray.length; i++) {
            DBAccess.setSold(i + 1, ordarray[i]);
        }
        DBAccess.setOrder(ordbool, Day);
        }
        else{
            //outputs error message to explain
            JOptionPane.showMessageDialog(null, "Error: No options selected.", "Selection error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //used in confirm method to check if one is selected
    private Boolean checkOrdered(){
        Boolean Order = false;
        //if a '1' is found in array then boolean set to true, otherwise 
        //false is returned
        for (int i = 0; i < ordarray.length; i++) {
            if(ordarray[i]==1){
                Order = true;
            }
        }
        return Order;
    } 
}

