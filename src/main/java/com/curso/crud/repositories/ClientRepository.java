package com.curso.crud.repositories;

import com.curso.crud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client,Long> {
}

