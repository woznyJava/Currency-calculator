package com.example.egzaminrest.currencyList;


import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.List;

@UtilityClass
// jesli ma tylko funkcjie (narzedziowa klasa)
public class ReadFileIntoArrayList {

    public List<String> getReader(List<String> list) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("WorldCurrenies.txt"));
        String line;
        while ((line = reader.readLine()) != null ) {
            list.add(line);
        }
        reader.close();
            return list;
        }
    }



