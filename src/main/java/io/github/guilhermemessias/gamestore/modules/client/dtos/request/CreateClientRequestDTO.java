package io.github.guilhermemessias.gamestore.modules.client.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio ou em branco") String name,
        @Email(message = "Email inválido")
        @NotBlank(message = "Email não pode estar vazio ou em branco") String email) {
}
