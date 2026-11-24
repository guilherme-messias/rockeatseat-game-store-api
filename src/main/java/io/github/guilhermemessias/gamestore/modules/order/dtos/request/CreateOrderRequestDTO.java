package io.github.guilhermemessias.gamestore.modules.order.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateOrderRequestDTO(
        @NotNull(message = "O ID do cliente não pode estar vazio ou em branco")
        UUID clientId,
        @NotNull(message = "O ID do jogo não pode estar vazio ou em branco")
        UUID gameId) {
}
