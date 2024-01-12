package com.curso.crud.service;

import com.curso.crud.dto.ClientDTO;
import com.curso.crud.entities.Client;
import com.curso.crud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClientRepository repository;
    @Transactional(readOnly = true)
    public ClientDTO findById (Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.get();
        ClientDTO dto = new ClientDTO(client);
        return dto;
    }
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll (Pageable pageable){
        Page<Client> result =  repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){
        Client client = new Client();
        copyDtoToEntity(clientDTO,client);
        client = repository.save(client);
        return clientDTO;
    }



    private void copyDtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        client.setIncome(dto.getIncome());

    }
}


