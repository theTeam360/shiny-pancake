/**
 * theTeam360
 * Winter TCES 360
 * Bill Sylvia
 * Final Project.
 * 
 * 	This is static class for submitting user information to the data base which is just a text file
 *  within the project. It will also check to see if a user exists and retrieve an email for a specific
 *  user.
 */

package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Registration {
	
	
	/**
	 * This is static method for adding a user to the database.
	 * 
	 * @param UN = String value for user name.
	 * @param PW = String value for password.
	 * @param FN = String value for First Name.
	 * @param LN = String value for Last Name.
	 * @param age = String value for age.
	 * @param email = String value for email address.
	 * @param phone = String value for phone number.
	 * @param address = String value for address. This is not used in our application.
	 * @return Returns an integer 0 for successful submission or -1 one for a fail.
	 * @throws IOException
	 */
	public static int add(String UN, String PW, String FN, String LN, String age, String email, String phone, String address) throws IOException {
        int result = -1;
        if(FN.equals("")) {
        	FN = " ";
        }
        if(LN.equals("")) {
        	LN = " ";
        }
        if(age.equals("")) {
        	age = " ";
        }
        if(email.equals("")) {
        	email = " ";
        }
        if(phone.equals("")) {
        	phone = " ";
        }
        if(address.equals("")) {
        	address = " ";
        }
        if (userExists(UN, null)) {
        	JOptionPane.showMessageDialog(
        		    null, "You're Already Registered",
        		    "Registration error",
        		    JOptionPane.ERROR_MESSAGE);
        	
        } else {
			
			    String filename= "./src/model/RegData.txt";
			    File myFile = new File(filename);
			    FileWriter fw = new FileWriter(myFile,true); //the true will append the new data
			    fw.write(UN.toLowerCase() + "," + PW + "," + FN + "," + LN + "," + age + "," + email + "," + phone + "," + address + "\n");//appends the string to the file
			    fw.close();
			    result = 0;



        }
        return result;
	}
	
	/**
	 * User for login and to check if user exists. If you pass in Null for the password, 
	 * it will only to see if user exists.
	 * 
	 * @param un = User name, this is not case sensitive.
	 * @param pw = Password or Null
	 * @return boolean True is user exists, False if user does not exist.
	 * @throws IOException
	 */
	public static boolean userExists(String un, String pw) throws IOException {
		boolean found = false;
        String filename = "./src/model/RegData.txt";
        File myFile = new File(filename);
        String line = null;
        String userName[];
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            
        while((line = bufferedReader.readLine()) != null) {
        	userName = line.split(",");
            	
            if (userName[0].toLowerCase().equals(un.toLowerCase())) {
            	if(pw == null) {
                	found = true;
                } else {
                	if (pw.equals(userName[1]) && userName[0].equals(un)) {
                		found = true;
                	} else {
                		found = false;
                	}
                }
                	
                break;
            }
        }   

        bufferedReader.close();
   
		return found;
	}
	
	
	/**
	 * Returns the email address of the user. If the user exists but there is no email address,
	 * the function returns a string value of "-1". If the user does not exists, it returns a 
	 * string value of "0".
	 * @param un = String value for user name;
	 * @return a string value for the email address. See note above.
	 * @throws IOException
	 */
	public static String findEmail(String un) throws IOException {
		
        String filename = "./src/model/RegData.txt";
        File myFile = new File(filename);
        String line = null;
        String userName[];
        String email = "0";
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
            
        while((line = bufferedReader.readLine()) != null) {
        	userName = line.split(",");
            if (userName[0].toLowerCase().equals(un.toLowerCase())) {
            	
            	if(userName[5].equals(" ")){
            		email = "-1";
                } else {
                	email = userName[5];
                }
                break;
            }
        }   
            
        bufferedReader.close();  
		return email;
	}
}
