package io.github.guilhermemessias.gamestore.modules.game.controllers;

import io.github.guilhermemessias.gamestore.modules.game.dtos.request.CreateGameRequestDTO;
import io.github.guilhermemessias.gamestore.modules.game.dtos.response.CreateGameResponseDTO;
import io.github.guilhermemessias.gamestore.modules.game.dtos.response.GetGameResponseDTO;
import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import io.github.guilhermemessias.gamestore.modules.game.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jogos")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<CreateGameResponseDTO> createGame(@Valid @RequestBody CreateGameRequestDTO gameEntity) {
        var result = gameService.createGame(gameEntity);

        return ResponseEntity.ok().body(new CreateGameResponseDTO(result.getId(),
                result.getTitle(), result.getGenre(), result.getPrice(), result.getCreatedAt()));
    }

    @GetMapping
    public ResponseEntity<Page<GetGameResponseDTO>> getGames(Pageable pageable) {
        var result = gameService.findAll(pageable);

        return ResponseEntity.ok().body(result.map(game -> new GetGameResponseDTO(game.getId(),
                game.getTitle(), game.getGenre(), game.getPrice(), game.getCreatedAt())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetGameResponseDTO> getGameById(@PathVariable UUID id) {
        var result = gameService.findById(id);
        return ResponseEntity.ok().body(new GetGameResponseDTO(result.getId(),
                result.getTitle(), result.getGenre(), result.getPrice(), result.getCreatedAt()));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<GetGameResponseDTO>> getGamesByTitle(@RequestParam String titulo) {
        var result = gameService.findByTitle(titulo);

        return ResponseEntity.ok().body(result.stream().map(game -> new GetGameResponseDTO(game.getId(),
                game.getTitle(), game.getGenre(), game.getPrice(), game.getCreatedAt())).toList());
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<GetGameResponseDTO>> getGamesByGenre(@PathVariable Genre genero) {
        var result = gameService.findByGenre(genero);

        return ResponseEntity.ok().body(result.stream().map(game -> new GetGameResponseDTO(game.getId(),
                game.getTitle(), game.getGenre(), game.getPrice(), game.getCreatedAt())).toList());
    }

    @GetMapping("ordenados-por-preco")
    public ResponseEntity<Page<GetGameResponseDTO>> getGamesOrderedByPrice(@PageableDefault(sort = "price") Pageable pageable) {
        var result = gameService.findAll(pageable);

        return ResponseEntity.ok().body(result.map(game -> new GetGameResponseDTO(game.getId(),
                game.getTitle(), game.getGenre(), game.getPrice(), game.getCreatedAt())));
    }

    @GetMapping("/mais-caro")
    public ResponseEntity<GetGameResponseDTO> getMostExpensiveGame() {
        var result = gameService.findMostExpensiveGame();
        return ResponseEntity.ok().body(new GetGameResponseDTO(result.getId(),
                result.getTitle(), result.getGenre(), result.getPrice(), result.getCreatedAt()));
    }

    @GetMapping("/cadastrados-no-ano-atual")
    public ResponseEntity<List<GetGameResponseDTO>> getGamesRegisteredInCurrentYear() {
        var result = gameService.findGamesRegisteredInCurrentYear();

        return ResponseEntity.ok().body(result.stream().map(game -> new GetGameResponseDTO(game.getId(),
                game.getTitle(), game.getGenre(), game.getPrice(), game.getCreatedAt())).toList());
    }

}
