package br.com.cardozo.orderofserviceapi.services.interfaces;

import br.com.cardozo.orderofserviceapi.models.Comment;
import br.com.cardozo.orderofserviceapi.models.OrderService;

public interface CommentService {
    Comment save(final OrderService orderService, final String description);
}
