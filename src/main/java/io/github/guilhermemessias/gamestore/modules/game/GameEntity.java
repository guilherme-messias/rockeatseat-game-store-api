package io.github.guilhermemessias.gamestore.modules.game;

import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    private Genre genre;

    @Min(value = 1, message = "O preço do jogo deve ser maior que zero")
    private double price;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
