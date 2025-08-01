package com.distribuida.dao;

import com.distribuida.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository si o no da lo mismo es un bin

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
    Cliente findByCedula(String cedula);




}
