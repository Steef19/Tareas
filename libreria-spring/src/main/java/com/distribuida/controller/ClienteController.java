package com.distribuida.controller;


import com.distribuida.model.Cliente;
import com.distribuida.service.ClienteServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")


public class ClienteController {


@Autowired
    private ClienteServce clienteServce;


    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> cliente = clienteServce.findAll();
        return  ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findOne(@PathVariable int id){
        Cliente cliente = clienteServce.findOne(id);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(cliente);
    }


    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteServce.save(cliente);
        return  ResponseEntity.ok(clienteNuevo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable int id, @RequestBody Cliente cliente){
        Cliente clienteActulizado = clienteServce.update(id,cliente);
        if (clienteActulizado == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(clienteActulizado);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable int id){
        clienteServce.delete(id);
        return  ResponseEntity.noContent().build();


    }

}
