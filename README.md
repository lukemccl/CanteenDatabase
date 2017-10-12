# CanteenDatabase
A-Level Coursework 2017, wrote a DB and application that interacts with it.
DB was set up through NetBeans and was always run through the IDE to test; 
Application is Java based and allows a user to login, place an order or view the orders in the DB.

Application starts in the 'SecScreen' class, I have included grab files of the tables within the DB. Must be populated in order to function.
A text file of the users will also be necessary to keep in the project folder for this application, the text file is a csv table where it runs in order of: username,password,5digitcode,usertype (0 for student, 1 for admin staff who are able to view DB). This file will need to be encrypted in order to be accessed, key is in the Encrypt class. 
