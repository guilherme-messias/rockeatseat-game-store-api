package io.github.guilhermemessias.gamestore.modules.order;

import io.github.guilhermemessias.gamestore.modules.client.ClientEntity;
import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clients_id", nullable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "games_id", nullable = false)
    private GameEntity game;

    private Double price;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
