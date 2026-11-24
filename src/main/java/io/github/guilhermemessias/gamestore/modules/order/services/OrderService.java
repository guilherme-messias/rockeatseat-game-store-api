package io.github.guilhermemessias.gamestore.modules.order.services;

import io.github.guilhermemessias.gamestore.modules.client.ClientEntity;
import io.github.guilhermemessias.gamestore.modules.client.repositories.ClientRepository;
import io.github.guilhermemessias.gamestore.modules.exception.DuplicateGameOrderException;
import io.github.guilhermemessias.gamestore.modules.exception.ExistingClient;
import io.github.guilhermemessias.gamestore.modules.exception.ExistingGame;
import io.github.guilhermemessias.gamestore.modules.game.GameEntity;
import io.github.guilhermemessias.gamestore.modules.game.repositories.GameRepository;
import io.github.guilhermemessias.gamestore.modules.order.OrderEntity;
import io.github.guilhermemessias.gamestore.modules.order.dtos.request.CreateOrderRequestDTO;
import io.github.guilhermemessias.gamestore.modules.order.repositories.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ClientRepository clientRepository;

    public OrderEntity createOrder(@Valid CreateOrderRequestDTO orderEntity) {
        ClientEntity client = this.clientRepository.findById(orderEntity.clientId())
                .orElseThrow(() -> new ExistingClient("Cliente não encontrado com o ID: " + orderEntity.clientId()));

        GameEntity game = this.gameRepository.findById(orderEntity.gameId())
                .orElseThrow(() -> new ExistingGame("Jogo não encontrado com o ID: " + orderEntity.gameId()));

        if (this.orderRepository.existsByClient_IdAndGame_Id(client.getId(), orderEntity.gameId())) {
            throw new DuplicateGameOrderException("O cliente já possui um pedido para este jogo.");
        }

        OrderEntity order = new OrderEntity();
        order.setClient(client);
        order.setGame(game);
        order.setPrice(game.getPrice());

        return this.orderRepository.save(order);
    }
}
