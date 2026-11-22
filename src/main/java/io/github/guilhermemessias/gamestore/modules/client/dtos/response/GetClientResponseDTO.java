package io.github.guilhermemessias.gamestore.modules.client.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetClientResponseDTO(UUID id, String name, String email, LocalDateTime createdAt) {
}
