package io.github.guilhermemessias.gamestore.modules.game.dtos.request;

import io.github.guilhermemessias.gamestore.modules.game.enums.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGameRequestDTO(
        @NotBlank(message = "Título não pode estar vazio ou em branco") String title,
        @NotNull(message = "Gênero não pode ser nulo") Genre genre,
        @NotNull(message = "Preço não pode ser nulo") @Min(value = 1, message = "O preço do jogo deve ser maior que zero") Double price) {

}
