package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteServce{


    @Override
    public List<Cliente> findAll() {
        return List.of();
    }

    @Override
    public Cliente findOne(int id) {
        return null;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente update(int id, Cliente cliente) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
