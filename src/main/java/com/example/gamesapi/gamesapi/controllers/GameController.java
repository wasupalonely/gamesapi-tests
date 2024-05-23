package com.example.gamesapi.gamesapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.gamesapi.gamesapi.models.entities.Game;
import com.example.gamesapi.gamesapi.services.GameService;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game findGameById(@PathVariable String id) {
        return gameService.findGameById(id);
    }

    @PostMapping
    public Game create(@RequestBody Game game) {
        return gameService.create(game);
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable String id, @RequestBody Game game) {
        return gameService.update(id, game);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return gameService.delete(id);
    }
}
