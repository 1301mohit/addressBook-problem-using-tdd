package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook implements IAddressBook {

    Utility utility = new Utility();
    List<PersonDTO> listOfPersonDetail = new LinkedList<>();

    @Override
    public boolean addNewPerson(PersonDTO person, String filePath) {
            if(person.getFirstName().equals(""))
                throw new AddressBookException("First name is missing", AddressBookException.ExceptionType.FIRST_NAME_MISSING);
            if(person.getLastName().equals(""))
                throw new AddressBookException("Last name is missing", AddressBookException.ExceptionType.LAST_NAME_MISSING);
            if(person.getPhoneNumber().equals(""))
                throw new AddressBookException("Phone number is missing", AddressBookException.ExceptionType.PHONE_NUMBER_MISSING);
            if(person.getAddress().getCity().equals(""))
                throw new AddressBookException("City is missing", AddressBookException.ExceptionType.CITY_MISSING);
            if(person.getAddress().getState().equals(""))
                throw new AddressBookException("State is missing", AddressBookException.ExceptionType.State_MISSING);
            if(person.getAddress().getZip().equals(""))
                throw new AddressBookException("Zip is missing", AddressBookException.ExceptionType.ZIP_MISSING);
            readPersonData(filePath);
            long countPhoneNumber = listOfPersonDetail.stream()
                                                      .filter(personData -> person.getPhoneNumber().equals(personData.getPhoneNumber()))
                                                      .count();
            if(countPhoneNumber > 0)
                throw new AddressBookException("Already exist", AddressBookException.ExceptionType.ALREADY_EXIST);
            listOfPersonDetail.add(person);
            listOfPersonDetail.stream().forEach(System.out::println);
            writePersonData(filePath);
            return true;
    }

    @Override
    public String deletePerson(String phoneNumber, String filePath) {
        readPersonData(filePath);
        PersonDTO person = listOfPersonDetail.stream()
                                             .filter(personData -> personData.getPhoneNumber().equals(phoneNumber))
                                             .findAny()
                                             .orElseThrow(() -> new AddressBookException("Person does not exist", AddressBookException.ExceptionType.NOT_EXIST));
        System.out.println(person);
        listOfPersonDetail.remove(person);
        writePersonData(filePath);
        return person.getPhoneNumber();
    }

    @Override
    public String editPersonDetails(String phoneNumber, String filePath, EnumOfFields fieldName, String newValue) {
        readPersonData(filePath);
        PersonDTO person = listOfPersonDetail.stream()
                .filter(personData -> personData.getPhoneNumber().equals(phoneNumber))
                .findAny()
                .orElseThrow(() -> new AddressBookException("Person does not exist", AddressBookException.ExceptionType.NOT_EXIST));
        System.out.println(person);
        listOfPersonDetail.remove(person);
        if(fieldName.equals(EnumOfFields.CITY))
            person.getAddress().setCity(newValue);
        else if(fieldName.equals(EnumOfFields.STATE))
            person.getAddress().setState(newValue);
        else if(fieldName.equals(EnumOfFields.ZIP))
            person.getAddress().setZip(newValue);
        listOfPersonDetail.add(person);
        System.out.println(person);
        writePersonData(filePath);
        return person.getPhoneNumber();
    }

    @Override
    public boolean sort(String filePath, AddressBookComparatorHandaler.SortingType fieldName) {
        readPersonData(filePath);
        Comparator<PersonDTO> comparatorForSorting = AddressBookComparatorHandaler.getComparator(fieldName);
        List<PersonDTO> sortedList = listOfPersonDetail.stream().sorted(comparatorForSorting).collect(Collectors.toList());
        sortedList.stream().forEach(System.out::println);
        return true;
    }

    @Override
    public boolean print(String filePath) {
        readPersonData(filePath);
        listOfPersonDetail.stream().forEach(System.out::println);
        return true;
    }

    @Override
    public boolean writePersonData(String filePath) {
        return utility.writeInJsonFile(listOfPersonDetail, filePath);
    }

    @Override
    public boolean readPersonData(String filePath) {
        listOfPersonDetail = utility.readFromJsonFile(filePath);
        return true;
    }

}
