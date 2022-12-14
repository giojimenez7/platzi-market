package com.platzi.market.persistence;

import com.platzi.market.domain.Client;
import com.platzi.market.domain.repository.ClientRepository;
import com.platzi.market.persistence.crud.ClienteCrudRepository;
import com.platzi.market.persistence.entity.Cliente;
import com.platzi.market.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClientMapper mapper;

    @Override
    public List<Client> getAll() {

        List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
        return mapper.toClients(clientes);

    }

    @Override
    public Optional<Client> getClient(String clientId) {

        return clienteCrudRepository.findById(clientId).map(cliente -> mapper.toClient(cliente));

    }

    @Override
    public Client save(Client client) {

        Cliente cliente = mapper.toCliente(client);
        return mapper.toClient(clienteCrudRepository.save(cliente));

    }

    @Override
    public void delete(String clientId) {

        clienteCrudRepository.deleteById(clientId);

    }
}
