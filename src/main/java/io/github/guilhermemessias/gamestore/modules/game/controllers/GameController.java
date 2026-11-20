package io.github.guilhermemessias.gamestore.modules.game.controllers;

import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import io.github.guilhermemessias.gamestore.modules.game.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogos")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<GameEntity> createGame(@Valid @RequestBody GameEntity gameEntity) {
        var result = gameService.createGame(gameEntity);

        return ResponseEntity.ok().body(result);
    }
}
