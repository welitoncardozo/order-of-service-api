package br.com.cardozo.orderofserviceapi.services;

import br.com.cardozo.orderofserviceapi.enuns.StatusOrderOfService;
import br.com.cardozo.orderofserviceapi.exceptions.BusinessException;
import br.com.cardozo.orderofserviceapi.models.Client;
import br.com.cardozo.orderofserviceapi.models.Comment;
import br.com.cardozo.orderofserviceapi.models.OrderService;
import br.com.cardozo.orderofserviceapi.repositories.OrderServiceRepository;
import br.com.cardozo.orderofserviceapi.services.interfaces.ClientService;
import br.com.cardozo.orderofserviceapi.services.interfaces.CommentService;
import br.com.cardozo.orderofserviceapi.services.interfaces.OrderServiceService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceServiceImpl implements OrderServiceService {
    private final ClientService clientService;
    private final CommentService commentService;
    private final OrderServiceRepository orderServiceRepository;

    public OrderServiceServiceImpl(OrderServiceRepository orderServiceRepository, ClientService clientService, CommentService commentService) {
        this.orderServiceRepository = orderServiceRepository;
        this.clientService = clientService;
        this.commentService = commentService;
    }

    @Override
    public OrderService createNewOrderService(final OrderService orderService) {
        final Client client = clientService.findById(orderService.getClient().getId())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
        orderService.setClient(client);
        orderService.setStatus(StatusOrderOfService.OPEN);
        orderService.setStartDate(LocalDateTime.now());
        return orderServiceRepository.save(orderService);
    }

    @Override
    public OrderService finishOrderService(final Long orderServiceId) {
        final OrderService orderService = orderServiceRepository.findById(orderServiceId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
        if (orderService.getStatus().isNotOpen()) {
            throw new BusinessException("Ordem de serviço não pode ser finalizada");
        }

        orderService.setStatus(StatusOrderOfService.FINISHED);
        orderService.setEndDate(LocalDateTime.now());
        return orderServiceRepository.save(orderService);
    }

    @Override
    public Comment addComment(final Long orderServiceId, final String description) {
        final OrderService orderService = orderServiceRepository.findById(orderServiceId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
        return commentService.save(orderService, description);
    }

    @Override
    public Optional<OrderService> findById(final Long id) {
        return orderServiceRepository.findById(id);
    }

    @Override
    public List<OrderService> findAll() {
        return orderServiceRepository.findAll();
    }
}
