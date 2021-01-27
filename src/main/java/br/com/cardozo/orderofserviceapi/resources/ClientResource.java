package br.com.cardozo.orderofserviceapi.resources;

import br.com.cardozo.orderofserviceapi.models.Client;
import br.com.cardozo.orderofserviceapi.services.interfaces.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/clients")
public class ClientResource {
    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<Client>> findAll() {
        final List<Client> clientsList = this.clientService.findAll();
        if (clientsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clientsList);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client save(@Valid @RequestBody final Client client) {
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        if (clientService.isNotExists(id)) {
            return ResponseEntity.notFound().build();
        }

        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
