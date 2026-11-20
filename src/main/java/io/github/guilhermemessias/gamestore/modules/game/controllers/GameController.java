package io.github.guilhermemessias.gamestore.modules.game.controllers;

import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogos")
public class GameController {
    @PostMapping("/cadastrar")
    public void createGame(@Valid @RequestBody GameEntity gameEntity) {

    }
}
