package br.com.cardozo.orderofserviceapi.resources;

import br.com.cardozo.orderofserviceapi.models.Comment;
import br.com.cardozo.orderofserviceapi.services.interfaces.OrderServiceService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/order-of-services/{orderServiceId}/comments")
public class CommentResource {
    private final OrderServiceService orderServiceService;

    public CommentResource(OrderServiceService orderServiceService) {
        this.orderServiceService = orderServiceService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Comment addComment(@PathVariable final Long orderServiceId,
                              @RequestBody final Comment comment) {
        return orderServiceService.addComment(orderServiceId, comment.getDescription());
    }
}
