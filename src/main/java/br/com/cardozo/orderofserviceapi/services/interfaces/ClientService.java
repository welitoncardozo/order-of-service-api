package br.com.cardozo.orderofserviceapi.services.interfaces;

import br.com.cardozo.orderofserviceapi.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void deleteById(final Long id);
    boolean isNotExists(final Long id);
    Optional<Client> findById(final Long id);
    Client save(final Client client);
    List<Client> findAll();
}
