package com.bridgelabz.addressbook;

import java.util.Comparator;
import java.util.EnumMap;

public class AddressBookComparatorHandaler {

    public enum SortingType {
        LASTNAME, ZIP;
    }

    static EnumMap<SortingType, Comparator<PersonDTO>> enumForSorting = new EnumMap<>(SortingType.class);

    static {
        enumForSorting.put(SortingType.LASTNAME, Comparator.comparing(personData -> personData.getLastName().toUpperCase()));
        enumForSorting.put(SortingType.ZIP, Comparator.comparing(personData -> personData.getAddress().getZip()));
    }

    public static Comparator<PersonDTO> getComparator(SortingType type) {
        return enumForSorting.get(type);
    }

}
