package com.example.egzaminrest.repository;

import com.example.egzaminrest.domain.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends CrudRepository<Result, Integer> {
    List<Result> findALL();
}
// exchange bedzie szedl do bazy
// w serwisie zlicza wszystko robi stats i zwraca
