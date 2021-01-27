package br.com.cardozo.orderofserviceapi.services.interfaces;

import br.com.cardozo.orderofserviceapi.models.Comment;
import br.com.cardozo.orderofserviceapi.models.OrderService;

import java.util.List;
import java.util.Optional;

public interface OrderServiceService {
    OrderService createNewOrderService(final OrderService orderService);
    OrderService finishOrderService(final Long orderServiceId);
    Comment addComment(final Long orderServiceId, final String description);
    Optional<OrderService> findById(final Long id);
    List<OrderService> findAll();
}
