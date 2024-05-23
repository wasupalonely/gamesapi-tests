package com.example.gamesapi.gamesapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.gamesapi.gamesapi.models.entities.Game;

public interface GameRepository extends CrudRepository<Game, String> {

}
