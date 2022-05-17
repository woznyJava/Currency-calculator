package com.example.egzaminrest.currencyList;

import org.springframework.stereotype.Component;


import java.io.*;
import java.util.List;

@Component
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



