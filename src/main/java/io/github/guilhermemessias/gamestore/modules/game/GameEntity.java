package io.github.guilhermemessias.gamestore.modules.game;

import io.github.guilhermemessias.gamestore.modules.game.dtos.request.CreateGameRequestDTO;
import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Título não pode estar vazio ou em branco")
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gênero não pode ser nulo")
    private Genre genre;

    @NotNull(message = "Preço não pode ser nulo")
    @Min(value = 1, message = "O preço do jogo deve ser maior que zero")
    private Double price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public GameEntity(@Valid CreateGameRequestDTO gameEntity) {
        this.title = gameEntity.title();
        this.genre = gameEntity.genre();
        this.price = gameEntity.price();
    }

    public GameEntity() {

    }
}
