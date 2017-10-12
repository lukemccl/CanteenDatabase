/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthprac;

/**
 *
 * @author DevUser
 */
import javax.crypto.spec.*;
import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.DatatypeConverter;

public class Encrypt {

    //common variables and objects within class, private for security
    private static String key = "xnhytmhfdnxnrytg";
    private static SecretKeySpec Key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    private static Cipher encryption;

    public static String encryptLine(String s) throws Exception {

        //initialisation 
        encryption = Cipher.getInstance("AES");
        encryption.init(Cipher.ENCRYPT_MODE, Key);
        String target = s;

        //encrypts and encodes line to base 64
        byte[] finished = encryption.doFinal(target.getBytes(StandardCharsets.UTF_8));
        String encoded = DatatypeConverter.printBase64Binary(finished);

        //packages and return
        return encoded;
    }

    public static String decryptLine(String s) throws Exception {

        //initialisation
        encryption = Cipher.getInstance("AES");
        encryption.init(Cipher.DECRYPT_MODE, Key);
        String target = s;

        //decrypting and decoding line from base 64
        byte[] decoded = DatatypeConverter.parseBase64Binary(target);
        String decrypted = new String(encryption.doFinal(decoded));

        return decrypted;
    }

    public static void encryptFile(File f) throws Exception {

        //initialising all objects and variables required
        encryption = Cipher.getInstance("AES");
        encryption.init(Cipher.ENCRYPT_MODE, Key);
        int rows = UserMgmt.getRows();
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String[] encrypted = new String[rows];

        //loop to encrypt and encode file line by like
        for (int i = 0; i < rows; i++) {
            String target = br.readLine();
            byte[] start = target.getBytes(StandardCharsets.UTF_8);
            byte[] base = encryption.doFinal(start);
            String encrypt = DatatypeConverter.printBase64Binary(base);
            encrypted[i] = encrypt;
        }

        //writes array of encypted data to file
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String s : encrypted) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
    }

    public static void decryptFile(File f) throws Exception {

        //initialises all needed variables and pbjects
        encryption = Cipher.getInstance("AES");
        encryption.init(Cipher.DECRYPT_MODE, Key);
        int rows = UserMgmt.getRows();
        System.out.println(rows);
        FileReader read = new FileReader(f);
        BufferedReader line = new BufferedReader(read);
        String[] decrypted = new String[rows];

        //decrypts every line
        for (int i = 0; i < rows; i++) {
            byte[] decoded = DatatypeConverter.parseBase64Binary(line.readLine());
            decrypted[i] = new String(encryption.doFinal(decoded));
            System.out.println(encryption.doFinal(decoded));
        }

        //sets up writers and prints
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String s : decrypted) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
    }

}
