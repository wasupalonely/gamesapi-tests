package com.example.gamesapi.gamesapi.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.gamesapi.gamesapi.models.entities.Game;
import com.example.gamesapi.gamesapi.services.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;

    private Game game1;
    private Game game2;

    @BeforeEach
    public void setUp() {
        game1 = new Game("Game1", "Description1", "Genre1", "Author1");
        game2 = new Game("Game2", "Description2", "Genre2", "Author2");
    }

    @Test
    public void testFindAll() throws Exception {
        List<Game> games = Arrays.asList(game1, game2);
        when(gameService.findAll()).thenReturn(games);

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Game1"))
                .andExpect(jsonPath("$[1].name").value("Game2"));
    }

    @Test
    public void testFindGameById() throws Exception {
        when(gameService.findGameById("1")).thenReturn(game1);

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Game1"));
    }

    @Test
    public void testCreateGame() throws Exception {
        when(gameService.create(any(Game.class))).thenReturn(game1);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(game1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Game1"));
    }

    @Test
    public void testUpdateGame() throws Exception {
        Game updatedGame = new Game("Game2", "Description2", "Genre2", "Author2");
        when(gameService.update(eq("1"), any(Game.class))).thenReturn(updatedGame);

        mockMvc.perform(put("/games/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedGame)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Game2"));
    }

    @Test
    public void testDeleteGame() throws Exception {
        when(gameService.delete("1")).thenReturn(true);

        mockMvc.perform(delete("/games/1"))
                .andExpect(status().isOk());
    }
}

