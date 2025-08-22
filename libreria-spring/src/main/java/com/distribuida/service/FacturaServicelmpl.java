package com.distribuida.service;

import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FacturaServicelmpl  implements FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository categoriaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findOne(int id) {
        Optional<Factura> factura = facturaRepository.findById(id);

        return factura.orElse(null);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(int id,int idCliente, Factura factura) {
        Factura facturaExistente = findOne(id);

        Optional<Cliente> clienteExistente = categoriaRepository.findById(idCliente);
        if(facturaExistente == null){
            return null;
        }


        facturaExistente.setNumFactura(factura.getNumFactura());
        facturaExistente.setFecha(factura.getFecha());
        facturaExistente.setTotalNeto(factura.getTotalNeto());
        facturaExistente.setIva(factura.getIva());
        facturaExistente.setTotal(factura.getTotal());
        facturaExistente.setCliente(clienteExistente.orElse(null));
        return facturaRepository.save(facturaExistente);
    }

    @Override
    public void delete(int id) {
        if (facturaRepository.existsById(id)){
            facturaRepository.deleteById(id);
        }


    }
}
