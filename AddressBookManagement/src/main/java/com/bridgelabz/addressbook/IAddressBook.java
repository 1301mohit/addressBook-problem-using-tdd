package com.bridgelabz.addressbook;

public interface IAddressBook {
    public boolean addNewPerson(PersonDTO person, String filePath);
    public String deletePerson(String phoneNumber, String filePath);
    public String editPersonDetails(String phoneNumber, String filePath, EnumOfFields fieldName, String newValue);
    public boolean sort(String filePath, AddressBookComparatorHandaler.SortingType fieldName);
    public boolean print(String filePath);
    public boolean writePersonData(String filePath);
    public boolean readPersonData(String filePath);
}
