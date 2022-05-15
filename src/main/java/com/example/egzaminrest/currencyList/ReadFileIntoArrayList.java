package com.example.egzaminrest.currencyList;


import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.List;

@UtilityClass
public class ReadFileIntoArrayList {

    public List<String> getReader(List<String> list) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("WorldCurrenies.txt"));
        String line;
        while ((line = reader.readLine()) != null ) {
            list.add(line.trim());
        }
        reader.close();
            return list;
        }
    }



