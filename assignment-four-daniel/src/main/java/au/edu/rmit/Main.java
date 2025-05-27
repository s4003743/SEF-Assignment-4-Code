package au.edu.rmit;

public class Main {
    public static void main(String[] args) {

        // Reset for testing
        Person.resetTextFile("people.txt");

        // Person 1
        Person johnSmith = new Person("56s_d%&fAB", "John", "Smith", 
            "1|Downing Street|Bendigo|Victoria|Australia", "02-06-1999", null, false);

        // Person 2
        Person bobJane = new Person("57s_d%&fAB", "Bob", "Jane", 
            "1|T-Mart|Melbourne|Victoria|Australia", "01-05-1984", null, true);
        
        // Person 3
        Person aliceNewman = new Person("58s_d%&fAB", "Alice", "Newman", 
            "18|Ocean Avenue|Geelong|Victoria|Australia", "11-09-2001", null, false);
        
        // Person 4
        Person georgeBush = new Person("5*s_d%&fAB", "George", "Bush", 
            "7|Vegas Strip|Las Vegas|Victoria|Australia", "04-01-1987", null, false);
        
        // Person 5
        Person albertPerkins = new Person("52s_d%&fAB", "Albert", "Perkins", 
            "10|Agen Lane|Morrabin|Victoria|Canada", "04-09-1901", null, false);
        
        // Person 6
        Person daveLincoln = new Person("59s_d%&fAB", "Dave", "Lincoln", 
            "197|Royale Drive|Melbourne|Victoria|Australia", "04-01-2056", null, false);
        
        
        // These should work...
        johnSmith.addPerson();
        bobJane.addPerson();
        aliceNewman.addPerson();

        // These shouldn't...
        georgeBush.addPerson();
        albertPerkins.addPerson();
        daveLincoln.addPerson();
    }
}