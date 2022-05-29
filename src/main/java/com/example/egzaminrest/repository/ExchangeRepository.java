package com.example.egzaminrest.repository;

import com.example.egzaminrest.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Result, Integer> {
}
