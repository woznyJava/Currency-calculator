package com.example.egzaminrest.bean;

import com.example.egzaminrest.currencyList.ReadFileIntoArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan(basePackageClasses = ReadFileIntoArrayList.class)
public class ReaderConfig {

    @Bean
    public ReadFileIntoArrayList getReader() throws IOException {
        return new ReadFileIntoArrayList();
    }
}
