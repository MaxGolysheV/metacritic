package com.example.metacritic.repo.Games;

import com.example.metacritic.Models.Games.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findById(long id);
    List<Publisher> findByPublisherName(String publisher);
    List<Publisher> findByPublisherNameContains(String publisher);
}
