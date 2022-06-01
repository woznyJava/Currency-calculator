package com.example.currencyCalculator.repository;

import com.example.currencyCalculator.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Result, Integer> {
}
