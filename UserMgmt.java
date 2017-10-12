/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthprac;

import java.io.*;

/**
 *
 * @author DevUser
 */
public class UserMgmt {

    //read from file all user numbers
    //place into public array
    //find if one matches (within other screen)
    //relay which user number allocates to
    static int rows = 0;

    //returns two dimensional users array where needed
    public static String[][] getUsers() throws Exception {
        //initialises needed variables and readers
        String path = System.getProperty("user.dir");
        File f = new File(path + "\\users.txt");
        FileReader fread = new FileReader(f);
        BufferedReader users = new BufferedReader(fread);
        
        //read how many rows in file and create array of that many rows 
        //and 4 columns
        rows = getRows();
        String[][] userlist = new String[rows][4];

        //loops every row, reading + decrypt then splitting string with ,
        for (int count = 0; count < rows; count++) {
            String line = Encrypt.decryptLine(users.readLine());
            String[] test = line.split(",");

            //creates the seperate fields within the csv array for each user
            for (int counter = 0; counter < 4; counter++) {
                userlist[count][counter] = test[counter];
            }

        }
        users.close();
        return userlist;
    }

 //gets the number of rows in user file, so the number of users to process
    public static int getRows() throws Exception {
        //runs code once if hasnt been run before, otherwise simply returns
        //value
        if (rows == 0) {
            //initialises needed variables and filereaders
            String path = System.getProperty("user.dir");
            File f = new File(path + "\\users.txt");
            rows = 0;
            FileReader fread = new FileReader(f);
            BufferedReader lCounter = new BufferedReader(fread);

            String inpu = lCounter.readLine();
            //increases rows by one until next line is null (EOF)
            while (inpu != null) {
                rows++;
                inpu = lCounter.readLine();
            }
            lCounter.close();
        }
        return rows;
    }

}
