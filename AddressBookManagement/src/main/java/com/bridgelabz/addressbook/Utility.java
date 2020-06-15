package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Utility {

    Gson gson = new Gson();

    public boolean writeInJsonFile(List<PersonDTO> listOfPerson, String filePath) {
        try {
            String listOfPersonInJson = gson.toJson(listOfPerson);
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(listOfPersonInJson);
            fileWriter.close();
        } catch (IOException e) {
            throw new AddressBookException("Incorrect path", AddressBookException.ExceptionType.INCORRECT_PATH);
        }
        return true;
    }

    public List<PersonDTO> readFromJsonFile(String filePath)  {
        BufferedReader bufferedReader = null;
        List<PersonDTO> listOfPerson = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            listOfPerson.addAll(Arrays.asList(gson.fromJson(bufferedReader, PersonDTO[].class)));
        } catch (FileNotFoundException e) {
            throw new AddressBookException("File not found", AddressBookException.ExceptionType.NOT_FOUND);
        }
        return listOfPerson;
    }

}
