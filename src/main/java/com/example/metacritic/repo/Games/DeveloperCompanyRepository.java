package com.example.metacritic.repo.Games;

import com.example.metacritic.Models.Games.DeveloperCompany;
import com.example.metacritic.Models.Games.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeveloperCompanyRepository extends CrudRepository<DeveloperCompany,Long> {
    DeveloperCompany findById(long id);


    List<DeveloperCompany> findByDevName(String devName);

    List<DeveloperCompany> findByDevNameContains(String devName);

}
