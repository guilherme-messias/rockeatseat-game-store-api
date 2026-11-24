package io.github.guilhermemessias.gamestore.modules.order.repositories;

import io.github.guilhermemessias.gamestore.modules.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findAllByClient_Id(UUID id);

    List<OrderEntity> findAllByGame_Id(UUID id);

    boolean existsByClient_IdAndGame_Id(UUID id, UUID gameId);
}
