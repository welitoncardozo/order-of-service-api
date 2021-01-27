package br.com.cardozo.orderofserviceapi.repositories;

import br.com.cardozo.orderofserviceapi.models.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
}
