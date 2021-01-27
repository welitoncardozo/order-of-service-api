package br.com.cardozo.orderofserviceapi.dtos;

import br.com.cardozo.orderofserviceapi.enuns.StatusOrderOfService;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestOrderDto {
    private Long id;
    private String clientName;
    private String description;
    private Double price;
    private StatusOrderOfService status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
