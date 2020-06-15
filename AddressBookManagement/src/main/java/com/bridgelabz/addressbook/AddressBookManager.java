package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressBookManager implements IAddressBookManager {

    Utility utility = new Utility();
    Gson gson = new Gson();
    IAddressBook addressBook = new AddressBook();

    @Override
    public boolean create(String filePath, String addressBookName) {
        List<PersonDTO> listOfPerson = new ArrayList<PersonDTO>();
        utility.writeInJsonFile(listOfPerson,filePath+addressBookName+".json");
        return true;
    }

    @Override
    public boolean open(String filePath, String addressBookName) {
        return addressBook.readPersonData(filePath+addressBookName+".json");
    }

    @Override
    public boolean save(String filePath, String addressBookName) {
        addressBook.writePersonData(filePath+addressBookName+".json");
        return true;
    }

    @Override
    public boolean saveAs(String filePath, String oldAddressBook, String newAddressBook) {
        File oldFile = new File(filePath+oldAddressBook+".json");
        if(!oldFile.exists())
            throw new AddressBookException("File Not Exist", AddressBookException.ExceptionType.NOT_EXIST);
        File newFile = new File(filePath+newAddressBook+".json");
        if(newFile.exists() && oldFile.exists())
            throw new AddressBookException("File Already Exist", AddressBookException.ExceptionType.ALREADY_EXIST);
        return oldFile.renameTo(newFile);
    }

    @Override
    public List<String> getListOFAddressBookName(String filePath) {
        List<String> listOFAddressBook = null;
        try{
            File file = new File(filePath);
            listOFAddressBook = Arrays.asList(file.list());
        }catch(NullPointerException exception) {
            throw new AddressBookException("Path is incorrect", AddressBookException.ExceptionType.NOT_FOUND);
        }
        return listOFAddressBook;
    }

}
