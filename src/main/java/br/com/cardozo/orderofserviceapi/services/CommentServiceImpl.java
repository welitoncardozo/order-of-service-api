package br.com.cardozo.orderofserviceapi.services;

import br.com.cardozo.orderofserviceapi.models.Comment;
import br.com.cardozo.orderofserviceapi.models.OrderService;
import br.com.cardozo.orderofserviceapi.repositories.CommentRepository;
import br.com.cardozo.orderofserviceapi.services.interfaces.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(final OrderService orderService, final String description) {
        final Comment comment = Comment.builder()
                .orderService(orderService)
                .description(description)
                .sendDate(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }
}
