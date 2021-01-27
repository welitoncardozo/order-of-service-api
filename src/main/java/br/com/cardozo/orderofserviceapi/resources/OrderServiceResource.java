package br.com.cardozo.orderofserviceapi.resources;

import br.com.cardozo.orderofserviceapi.dtos.RequestOrderDto;
import br.com.cardozo.orderofserviceapi.models.OrderService;
import br.com.cardozo.orderofserviceapi.services.interfaces.OrderServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/order-of-services")
public class OrderServiceResource {
    private final OrderServiceService orderServiceService;
    private final ModelMapper modelMapper;

    public OrderServiceResource(OrderServiceService orderServiceService, ModelMapper modelMapper) {
        this.orderServiceService = orderServiceService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderService createNewOrderService(@Valid @RequestBody final OrderService orderService) {
        return orderServiceService.createNewOrderService(orderService);
    }

    @PutMapping("/{orderServiceId}/completion")
    @ResponseStatus(NO_CONTENT)
    public void finishOrderService(@PathVariable final Long orderServiceId) {
        orderServiceService.finishOrderService(orderServiceId);
    }

    @GetMapping
    public List<OrderService> findAll() {
        return orderServiceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestOrderDto> findById(@PathVariable final Long id) {
        final Optional<OrderService> orderService = orderServiceService.findById(id);
        if (orderService.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final RequestOrderDto requestOrderDto = modelMapper.map(orderService.get(), RequestOrderDto.class);
        return ResponseEntity.ok(requestOrderDto);
    }
}
