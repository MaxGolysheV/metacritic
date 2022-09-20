package com.example.metacritic.repo;

import com.example.metacritic.Models.Age;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeRepository extends JpaRepository<Age,Long> {


    Age findByValue(String value);

    Age findById(long id);
}
