package com.example.metacritic.repo.Games;

import com.example.metacritic.Models.Games.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameGenreRepository extends JpaRepository<GameGenre,Long>{

    GameGenre findById(long id);

    List<GameGenre> findByGenreName(String genreName);

    List<GameGenre> findByGenreNameContains(String genreName);

}
