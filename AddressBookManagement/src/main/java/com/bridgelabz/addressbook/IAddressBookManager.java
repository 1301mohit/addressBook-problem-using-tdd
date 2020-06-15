package com.bridgelabz.addressbook;

import java.util.List;

public interface IAddressBookManager {
    public boolean create(String filePath, String addressBookName);
    public boolean open(String filePath, String addressBookName);
   // public boolean open(String filePath, String addressBookName, PersonDTO person, EnumForPersonOperation operation, EnumOfFields fields, String newValue, AddressBookComparatorHandaler.SortingType sortingType);
    public boolean save(String filePath, String addressBookName);
    public boolean saveAs(String filePath, String firstAddressBook, String newAddressBook);
    public List<String> getListOFAddressBookName(String filePath);
}
