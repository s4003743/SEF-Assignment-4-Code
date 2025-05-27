package au.edu.rmit;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Person {
    
    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<Date, Integer> demeritPoints;
    private boolean isSuspended;
    private final String specialChars = "!@#$%^&*()-_=+\\\\|{};:/?.><~";
    private final String person_file = "people.txt";

    // This is the default constructer, for when you create an new person prior to adding details
    public Person() 
    {
        this.personID = null;
        this.firstName = null;
        this.lastName = null;
        this.address = null;
        this.birthdate = null;
        this.demeritPoints = null;
        this.isSuspended = false;
    }

    // This is the main constructer, for when you create an new person with details
    // This one should be primarilt used as to avoid the need for setters and getters
    public Person(String personID, String firstName, String lastName, String address, 
        String birthdate, HashMap<Date, Integer> demeritPoints, boolean isSuspended) 
    {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
        this.demeritPoints = demeritPoints;
        this.isSuspended = isSuspended;
    }

    // The main add person function, simply calls all the validation checks
    // and then calls the function to write the data into the text file
    public boolean addPerson() {
        System.out.println("\n===\tADDING " + firstName + " " + lastName + "\t===\n");
        if (validatePersonID(personID) && validateAddress(address)
            && validateBirthdate(birthdate)) 
        {
            if (personAlreadyInTextFile(person_file)) {
                System.out.println("Add Person Not Successful\t:(");
                System.out.println("This person is already in people.txt (duplicate personID warning).");
                return false;
            }
            saveToTextFile(person_file);
            System.out.println("Add Person Successful!\t:)");
            return true;
        }
        System.out.println("Add Person Not Successful\t:(");
        return false;  
    }

    // Wipes the text file clean
    public static void resetTextFile(String filename) {
        try {
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(file);
            pw.write("");
        }
        catch (IOException e) {
            System.out.println("Error wiping file...\n" + e.getMessage());
        }
    }

    // Checks whether the personID already exists in the text file
    // Only checks personID since the ID MUST be unique for each person
    private boolean personAlreadyInTextFile(String filename) {
        try {
            BufferedReader fr = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = fr.readLine()) != null) {
                String[] lineList = line.split(",");

                if (personID.equals(lineList[0])) {
                    return true;
                }
            }
            fr.close();
        }
        catch (IOException e) {
            System.out.println("Error reading file...\n" + e.getMessage());
        }
        return false;
    }

    // Simply appends the basic info into the text file for a new person
    private void saveToTextFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(personID + "," + firstName + "," + lastName + "," + address + ","
                 + birthdate + "," + isSuspended + ",\n");
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error writing to file...\n" + e.getMessage());
        }
    }

    // Checks birthdays, whether the text format is correct
    // and whether the numbers are reasonable (not the 14th month for example)
    public boolean validateBirthdate(String inputBirthDate) {
        String[] birthdateList = inputBirthDate.split("-");

        if (inputBirthDate.length() != 10 || birthdateList.length != 3) {
            System.out.println("Birthday has wrong length\t:(");
            return false;
        }
        if (Integer.parseInt(birthdateList[0]) <= 0 || 
            Integer.parseInt(birthdateList[0]) > 31) 
        {
            System.out.println("Birthday day is wrong\t:(");
            return false;
        }
        if (Integer.parseInt(birthdateList[1]) <= 0 ||
            Integer.parseInt(birthdateList[1]) > 12) 
        {
            System.out.println("Birthday month is wrong\t:(");
            return false;
        }
        if ((birthdateList[2]).length() != 4 || Integer.parseInt(birthdateList[2]) > 2025) {
            System.out.println("Birthday year is wrong\t:(");
            return false;
        }
        System.out.println("Birthday is valid\t:)");
        return true;
    }

    // Checks whether the address is in the correct format
    public boolean validateAddress(String inputAddress) {
        List<String> addressList = Arrays.asList(inputAddress.split("\\|"));

        if (addressList.size() != 5) {
            System.out.println("Address in wrong format\t:(");
            return false;
        }

        if (addressList.get(3).equals("Victoria") && addressList.get(4).equals("Australia") 
            && addressList.size() == 5 && isNumeric(addressList.get(0))) 
        {
            System.out.println("Address is valid\t:)");
            return true;
        }
        System.out.println("Address is invalid\t:(");
        return false;
    }

    // Checks whether an input string is a numeric value
    private boolean isNumeric(String inputNumber) {
        try {
            Integer.parseInt(inputNumber);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // Checks the personID against the requirements
    public boolean validatePersonID(String inputID) {
        if (inputID.length() != 10) {
            System.out.println("PersonID was wrong length\t:(");
            return false;
        }
        if (!startsWithCorrectNumbers(inputID)) {
            System.out.println("Doesn't start with valid numbers\t:(");
            return false;
        }
        if (!hasSpecialCharacters(inputID)) {
            System.out.println("Incorrect amount of special characters\t:(");
            return false;
        }
        if (!hasCapitalLettersAtEnd(inputID)) {
            System.out.println("Missing capital letters at the end\t:(");
            return false;
        }
        System.out.println("PersonID is valid\t:)");
        return true;
    }

    // Checks whether the last two characters are capital letters
    private boolean hasCapitalLettersAtEnd(String inputString) {
        int length = inputString.length();

        return (Character.isUpperCase(inputString.charAt(length - 1)) 
            && Character.isUpperCase(inputString.charAt(length - 2)));
    }

    // Checks whether there are at least 2 special characters between (inclusive) index 3 and 8
    private boolean hasSpecialCharacters(String inputString) {
        int count = 0;
        for (int i = 2; i < 8; i++) {
            if (isSpecialChar(inputString.charAt(i))) {
                count++;
            }
        }
        if (count >= 2) {
            return true;
        }
        return false;
    }

    // Checks whether the input character is in the special characters list
    private boolean isSpecialChar(char c) {
        for (int i = 0; i < specialChars.length(); i++) {
            if (c == specialChars.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    // Checks whether the personID starts with two valid numbers
    // (Both between 2 and 9 inclusive)
    private boolean startsWithCorrectNumbers(String inputString) {
        int firstNumber = Character.getNumericValue(inputString.charAt(0));
        int secondNumber = Character.getNumericValue(inputString.charAt(1));

        return (firstNumber <= 9 && firstNumber >= 2) 
            && (secondNumber <= 9 && secondNumber >= 2);
    }

    // === SETTERS AND GETTERS ===
    // (Here in case you need them, except for demerits)

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonID() {
        return personID;
    }

    public boolean getIsSuspended() {
        return isSuspended;
    }
}
