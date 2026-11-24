package io.github.guilhermemessias.gamestore.modules.order.controllers;

import io.github.guilhermemessias.gamestore.modules.order.dtos.request.CreateOrderRequestDTO;
import io.github.guilhermemessias.gamestore.modules.order.dtos.response.CreateOrderResponseDTO;
import io.github.guilhermemessias.gamestore.modules.order.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        var result = orderService.createOrder(createOrderRequestDTO);
        return ResponseEntity.ok().body(new CreateOrderResponseDTO(result.getId(),
                result.getClient().getId(), result.getGame().getId(), result.getPrice(), result.getCreatedAt()));
    }
}
