package br.com.cardozo.orderofserviceapi.services;

import br.com.cardozo.orderofserviceapi.exceptions.BusinessException;
import br.com.cardozo.orderofserviceapi.models.Client;
import br.com.cardozo.orderofserviceapi.repositories.ClientRepository;
import br.com.cardozo.orderofserviceapi.services.interfaces.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void deleteById(final Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public boolean isNotExists(final Long id) {
        return !clientRepository.existsById(id);
    }

    @Override
    public Optional<Client> findById(final Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(final Client client) {
        final Optional<Client> clientDatabaseByEmail = clientRepository.findByEmail(client.getEmail());
        if (clientDatabaseByEmail.isPresent() && !clientDatabaseByEmail.get().equals(client)) {
            throw new BusinessException("Já existe um cliente cadastrado com este e-mail");
        }

        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
