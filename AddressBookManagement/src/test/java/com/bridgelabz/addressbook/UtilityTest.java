package com.bridgelabz.addressbook;

import com.google.gson.Gson;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
public class UtilityTest {

    public static final String FILE_PATH = "/home/mohit/5.AddressBookManagementByUsingTDD/AddressBookManagement/src/test/resources/firstAddressBook.json";
    public static final String WRONG_FILE_PATH = "/home/mohit/5.AddressBookManagementByUsingTDD/AddressBookManagement/src/test/resource/firstAddressBook.json";

//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    @InjectMocks
//    Utility utility;
//
//    @Mock
//    Gson gson;

    Utility utility = null;

    @Before
    public void setUp() throws Exception {
        utility = new Utility();
    }

    @Test
    public void givenFilePath_whenReadFromJsonFile_shouldReturnSizeOfList() {
        try{
            List<PersonDTO> listOfPerson = utility.readFromJsonFile(FILE_PATH);
            Assert.assertEquals(1, listOfPerson.size());
        }catch(AddressBookException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void givenWrongFilePath_whenReadFromJsonFile_shouldThrowException() {
        try{
            List<PersonDTO> listOfPerson = utility.readFromJsonFile(WRONG_FILE_PATH);
        }catch(AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_FOUND, exception.type);
        }
    }

    @Test
    public void givenFilePath_whenWriteInJsonFile_shouldReturnTrue() {
        try {
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "898989");
            PersonDTO person1 = new PersonDTO("Ravi", "Kumar", address, "9898989898");
            List<PersonDTO> listOfPerson = Arrays.asList(person1);
            boolean result = utility.writeInJsonFile(listOfPerson, FILE_PATH);
            Assert.assertEquals(true, result);
        }catch(AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenWrongFilePath_whenWriteInJson_shouldThrowException() {
        try {
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "898989");
            PersonDTO person1 = new PersonDTO("Ravi", "Kumar", address, "9898989898");
            List<PersonDTO> listOfPerson = Arrays.asList(person1);
            boolean result = utility.writeInJsonFile(listOfPerson, WRONG_FILE_PATH);
        }catch(AddressBookException exception) {
            Assert.assertEquals(AddressBookException.ExceptionType.INCORRECT_PATH, exception.type);
        }
    }

}
