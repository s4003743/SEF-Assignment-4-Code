package au.edu.rmit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class testAddPerson {
    
    // This test is for the validation of the personID variable
    @Test
    public void testPersonID() {
        
        Person person = new Person(); 
        boolean result;
        
        // This checks a valid ID (seen in the assignment spec)
        result = person.validatePersonID("56s_d%&fAB");
        assertEquals(true, result);
        
        // This checks an invalid ID (wrong number at the start)
        result = person.validatePersonID("60s_d%&fAB");
        assertEquals(false, result);

        // This checks an invalid ID (wrong length)
        result = person.validatePersonID("52s_d%&fA");
        assertEquals(false, result);

        // This checks an invalid ID (last letter not capitalised)
        result = person.validatePersonID("52s_d%&fAl");
        assertEquals(false, result);
    }

    // This test is for the address validation
    @Test
    public void testAddress() {
        Person person = new Person();
        boolean result;

        // This checks a valid address
        result = person.validateAddress("1|Downing Street|Bendigo|Victoria|Australia");
        assertEquals(true, result);

        // This checks an invalid address (must be in Victoria, Australia)
        result = person.validateAddress("10|Agen Lane|Morrabin|Toronto|Canada");
        assertEquals(false, result);

        // This checks an invalid address (wrong format/delimiter)
        result = person.validateAddress("10,Agen Lane,Morrabin,Victoria,Australia");
        assertEquals(false, result);

        // This checks an invalid address (street number must be numeric)
        result = person.validateAddress("ten|Agen Lane|Morrabin|Victoria|Australia");
        assertEquals(false, result);
    }

    // This test is for the birthday validation
    @Test
    public void testBirthday() {
        Person person = new Person();
        boolean result;

        // Checks a valid birthday
        result = person.validateBirthdate("04-09-1901");
        assertEquals(true, result);

        // Checks an invalid birthday (invalid format, year must be 4 digits)
        result = person.validateBirthdate("4-09-19");
        assertEquals(false, result);

        // Checks an invalid birthday (2056 is in the future, unreasonable value)
        result = person.validateBirthdate("01-01-2056");
        assertEquals(false, result);

        // Checks an invalid birthday (wrong delimiter, should instead be '-')
        result = person.validateBirthdate("01/01/2000");
        assertEquals(false, result);
    }
}
