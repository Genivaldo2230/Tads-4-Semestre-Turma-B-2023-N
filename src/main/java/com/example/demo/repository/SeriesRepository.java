package com.example.demo.repository;

import com.example.demo.model.Series;
import jakarta.validation.ConstraintViolation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {




}
