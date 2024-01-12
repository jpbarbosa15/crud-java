package com.curso.crud.service;

import com.curso.crud.dto.ClientDTO;
import com.curso.crud.entities.Client;
import com.curso.crud.repositories.ClientRepository;
import com.curso.crud.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClientRepository repository;
    @Transactional(readOnly = true)
    public ClientDTO findById (Long id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        repository.deleteById(id);
    }

    @Transactional
    public ClientDTO update(Long id,ClientDTO dto){
        try {
            Client client = repository.getReferenceById(id);
            copyDtoToEntity(dto,client);
            client = repository.save(client);
            return new ClientDTO(client);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException ("Recurso nao encontrado");
        }
    }



    private void copyDtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        client.setIncome(dto.getIncome());

    }
}


