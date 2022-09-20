package com.example.metacritic.repo.Films;

import com.example.metacritic.Models.Films.Films;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Films,Long> {

List<Films> findByFilmName(String name);

Films findById(long id);

    List<Films> findByFilmNameContains(String name);
}
