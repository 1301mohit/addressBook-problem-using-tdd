package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AddressBookManagerTest {

    public static final String FILE_PATH = "/home/mohit/5.AddressBookManagementByUsingTDD/AddressBookManagement/src/test/resources/";
    public static final String WRONG_FILE_PATH = "/home/mohit/5.AddressBookManagementByUsingTDD/AddressBookManagement/src/test/resource/";

    IAddressBookManager addressBookManager = null;

    @Before
    public void setUp() throws Exception {
        addressBookManager = new AddressBookManager();
    }

    @Test
    public void givenAddressBookName_whenCreate_shouldReturnTrue() {
        try{
            boolean result = addressBookManager.create(FILE_PATH,"secondAddressBook");
            Assert.assertEquals(true, result);
        }catch(AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenWrongFilePath_whenCreate_shouldThrowException() {
        try {
            boolean result = addressBookManager.create(WRONG_FILE_PATH,"secondAddressBook");
        }catch(AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.INCORRECT_PATH, exception.type);
        }
    }

    @Test
    public void givenFilePath_whenOpen_shouldReturnTrue() {
        try{
            boolean result = addressBookManager.open(FILE_PATH, "firstAddressBook");
            Assert.assertEquals(true, result);
        }catch(AddressBookException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void givenWrongFileName_whenOpen_shouldThrowException() {
        try{
            boolean result = addressBookManager.open(FILE_PATH, "secondAddressBook");
        }catch(AddressBookException exception){
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_FOUND, exception.type);
        }
    }

    @Test
    public void givenFilePath_WhenSave_ShouldReturnTrue() {
        try{
            boolean result = addressBookManager.save(FILE_PATH, "firstAddressBook");
            Assert.assertEquals(true, result);
        }catch(AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenWrongFilePath_WhenSave_ShouldReturnTrue() {
        try{
            boolean result = addressBookManager.save(FILE_PATH, "firstAddressBook");
        }catch(AddressBookException exception) {
            Assert.assertEquals(AddressBookException.ExceptionType.INCORRECT_PATH, exception.type);
        }
    }

    @Test
    public void givenFilePathAndNewAddressBookName_WhenSaveAs_ShouldReturnTrue() {
        try{
            boolean result = addressBookManager.saveAs(FILE_PATH, "thirdAddressBook", "newSecondAddressBook");
            Assert.assertEquals(true, result);
        }catch (AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenFilePathAndWrongOldFileName_whenSaveAs_ShouldThrowException() {
        try{
            boolean result = addressBookManager.saveAs(FILE_PATH, "thirdAddressBookName", "newSecondAddressBook");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_EXIST, exception.type);
        }
    }

    @Test
    public void givenWrongFilePathAndNewFileName_whenSaveAs_ShouldThrowException() {
        try{
            boolean result = addressBookManager.saveAs(WRONG_FILE_PATH, "thirdAddressBookName", "newSecondAddressBook");
        }catch (AddressBookException exception) {
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_EXIST, exception.type);
        }
    }

    @Test
    public void givenFilePathAndNewFileAlreadyExist_whenSaveAs_shouldThrowException() {
        try{
            boolean result = addressBookManager.saveAs(FILE_PATH, "thirdAddressBook", "firstAddressBook");
        }catch (AddressBookException exception) {
            System.out.println(exception.getMessage());
            Assert.assertEquals(AddressBookException.ExceptionType.ALREADY_EXIST, exception.type);
        }
    }

    @Test
    public void givenFilePath_whenGetListOfFiles_shouldReturnList() {
        try{
            List<String> listOFAddressBookName = addressBookManager.getListOFAddressBookName(FILE_PATH);
            Assert.assertEquals(3, listOFAddressBookName.size());
        }catch(AddressBookException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenWrongFilePath_whenGetListOfFiles_shouldThrowEception() {
        try{
            List<String> listOfAddressBookName = addressBookManager.getListOFAddressBookName(WRONG_FILE_PATH);
        }catch (AddressBookException exception) {
            Assert.assertEquals(AddressBookException.ExceptionType.NOT_FOUND, exception.type);
        }
    }

}
