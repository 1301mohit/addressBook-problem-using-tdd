package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

    public static final String FILE_PATH = "/home/mohit/5.AddressBookManagementByUsingTDD/AddressBookManagement/src/test/resources/";
    public static final String WRONG_FILE_PATH = "/home/mohit/5.AddressBookManagementByUsingTDD/AddressBookManagement/src/test/resource/";
    IAddressBook addressBook = null;

    @Before
    public void setUp() throws Exception {
        addressBook = new AddressBook();
    }

    @Test
    public void givenPersonDetailsAndFilePath_whenAdded_shouldReturnTrue() {
        try{
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "123456");
            PersonDTO person = new PersonDTO("Amit", "Kumar", address, "9876541223");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
            Assert.assertEquals(true, result);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenPersonDetailsAndWrongFilePath_whenAdded_shouldThrowException() {
        try {
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "123456");
            PersonDTO person = new PersonDTO("Mohit", "Kumar", address, "9876543210");
            boolean result = addressBook.addNewPerson(person, WRONG_FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_FOUND, exception.type);
        }
    }

    @Test
    public void givenPersonDetailANDFirstNameMissing_whenAdded_shouldThrowException() {
        try{
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "123456");
            PersonDTO person = new PersonDTO("","Kumar", address, "9876543211");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.FIRST_NAME_MISSING, exception.type);
        }
    }

    @Test
    public void givenPersonDetailANDLastNameMissing_whenAdded_shouldThrowException() {
        try{
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "123456");
            PersonDTO person = new PersonDTO("Mohit","", address, "9876543212");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.LAST_NAME_MISSING, exception.type);
        }
    }

    @Test
    public void givenPersonDetailANDPhoneNumberMissing_whenAdded_shouldThrowException() {
        try{
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "123456");
            PersonDTO person = new PersonDTO("Mohit","Kumar", address, "");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.PHONE_NUMBER_MISSING, exception.type);
        }
    }

    @Test
    public void givenPersonDetailANDCityMissing_whenAdded_shouldThrowException() {
        try{
            AddressDTO address = new AddressDTO("", "Maharastra", "123456");
            PersonDTO person = new PersonDTO("Mohit","Kumar", address, "9876543211");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.CITY_MISSING, exception.type);
        }
    }

    @Test
    public void givenPersonDetailANDStateMissing_whenAdded_shouldThrowException() {
        try{
            AddressDTO address = new AddressDTO("Mumbai", "", "123456");
            PersonDTO person = new PersonDTO("Mohit","Kumar", address, "9876543211");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.State_MISSING, exception.type);
        }
    }

    @Test
    public void givenPersonDetailANDZipMissing_whenAdded_shouldThrowException() {
        try{
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "");
            PersonDTO person = new PersonDTO("Mohit","Kumar", address, "9876543211");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.ZIP_MISSING, exception.type);
        }
    }

    @Test
    public void givenPersonDetailAndDublicatePhoneNumber_whenAdded_shouldThrowException() {
        try{
            AddressDTO address = new AddressDTO("Mumbai", "Maharastra", "123456");
            PersonDTO person = new PersonDTO("Mohit","Kumar", address, "9876543211");
            boolean result = addressBook.addNewPerson(person, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.ALREADY_EXIST, exception.type);
        }
    }

    @Test
    public void givenPersonNumber_whenDeleted_shouldReturnDeletedPersonPhoneNumber() {
        try {
            String phoneNumber = "9876543211";
            String phoneNumberOfDeletedPerson = addressBook.deletePerson(phoneNumber, FILE_PATH+"firstAddressBook.json");
            Assert.assertEquals(phoneNumber, phoneNumberOfDeletedPerson);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenNotAvailablePersonPhoneNumber_whenDeleted_shouldThrowException() {
        try{
            String phoneNumber = "9876543222";
            String phoneNumberOfDeletedPerson = addressBook.deletePerson(phoneNumber, FILE_PATH+"firstAddressBook.json");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_EXIST, exception.type);
        }
    }

    @Test
    public void givenNotAvailablePersonPhoneNumber_whenEdited_shouldThrowException() {
        try{
            String phoneNumber = "9876543222";
            String phoneNumberOfDeletedPerson = addressBook.editPersonDetails(phoneNumber, FILE_PATH+"firstAddressBook.json",EnumOfFields.CITY, "Pune");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_EXIST, exception.type);
        }
    }

    @Test
    public void givenPersonPhoneNumber_whenEditedCity_shouldReturnTrue() {
        try{
            String phoneNumber = "9876543211";
            String phoneNumberOfEditedPerson = addressBook.editPersonDetails(phoneNumber, FILE_PATH+"firstAddressBook.json",EnumOfFields.CITY, "Pune");
            Assert.assertEquals(phoneNumberOfEditedPerson, phoneNumber);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenPersonPhoneNumber_whenEditedState_shouldReturnTrue() {
        try{
            String phoneNumber = "9876543211";
            String phoneNumberOfEditedPerson = addressBook.editPersonDetails(phoneNumber, FILE_PATH+"firstAddressBook.json",EnumOfFields.STATE, "Jharkhand");
            Assert.assertEquals(phoneNumberOfEditedPerson, phoneNumber);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenPersonPhoneNumber_whenEditedZip_shouldReturnTrue() {
        try{
            String phoneNumber = "9876543211";
            String phoneNumberOfEditedPerson = addressBook.editPersonDetails(phoneNumber, FILE_PATH+"firstAddressBook.json",EnumOfFields.ZIP, "66666");
            Assert.assertEquals(phoneNumberOfEditedPerson, phoneNumber);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenFile_whenSortedByLastName_shouldReturnTrue() {
        try{
            boolean result = addressBook.sort(FILE_PATH+"firstAddressBook.json", AddressBookComparatorHandaler.SortingType.LASTNAME);
            Assert.assertEquals(true, result);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenFile_whenSortedByZip_shouldReturnTrue() {
        try{
            boolean result = addressBook.sort(FILE_PATH+"firstAddressBook.json", AddressBookComparatorHandaler.SortingType.ZIP);
            Assert.assertEquals(true, result);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenFile_whenPrintAllPersonData_shouldReturnTrue() {
        try {
            boolean result = addressBook.print(FILE_PATH+"firstAddressBook.json");
            Assert.assertEquals(true, result);
        }catch(AddressBookException exception) {
            exception.printStackTrace();
        }
    }

}
