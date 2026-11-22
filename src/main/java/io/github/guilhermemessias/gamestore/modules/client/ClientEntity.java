package io.github.guilhermemessias.gamestore.modules.client;

import io.github.guilhermemessias.gamestore.modules.client.dtos.request.CreateClientRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome não pode estar vazio ou em branco")
    private String name;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email não pode estar vazio ou em branco")
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ClientEntity(@Valid CreateClientRequestDTO clientEntity) {
        this.name = clientEntity.name();
        this.email = clientEntity.email();
    }

    public ClientEntity() {

    }
}
