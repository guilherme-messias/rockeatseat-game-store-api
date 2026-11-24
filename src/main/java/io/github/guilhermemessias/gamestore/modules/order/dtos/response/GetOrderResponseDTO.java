package io.github.guilhermemessias.gamestore.modules.order.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetOrderResponseDTO(UUID id, UUID clientId, UUID gameId, Double price, LocalDateTime createdAt) {
}
