package com.example.egzaminrest.repository;


import com.example.egzaminrest.domain.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {

    List<Result> findAll();
    // z listy w strumien porozdzielam i wyciagam najwieksza wartosc powtarzalna
}
