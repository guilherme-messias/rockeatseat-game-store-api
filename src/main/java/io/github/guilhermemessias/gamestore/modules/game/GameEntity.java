package io.github.guilhermemessias.gamestore.modules.game;

import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import jakarta.persistence.*;
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

    @Min(value = 1, message = "O preço do jogo deve ser maior que zero")
    private double price;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
