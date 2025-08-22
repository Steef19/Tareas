package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class FacturaServicioTestUnitaria {
    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private FacturaServicelmpl facturaService;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(1,"17255866","Juan","Av","Av.set","099867","n@gmail.com");

        factura = new Factura(1,new Date(),"FAC-001",135.00,10.0,145.0,cliente);
    }

    public void testFindAll(){
        when(facturaRepository.findAll()).thenReturn(Arrays.asList(factura));
        List<Factura> facturas = facturaService.findAll();
        assertNotNull(facturas);
        assertEquals(1,facturas.size());
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        Factura factura = facturaService.findOne(1);
        assertNotNull(factura);
        assertEquals("FAC-001",factura.getNumFactura());
        verify(facturaRepository,times(1)).findById(1);


    }

    @Test
    public void save(){
        when(facturaRepository.save(factura)).thenReturn(factura);
        Factura factura1 = facturaService.save(factura);
        assertNotNull(factura1);
        assertEquals("FAC-001",factura.getNumFactura());
        verify(facturaRepository,times(1)).save(factura);

    }

    @Test
    public void update(){
        Factura facturaActualizada = new Factura(1,new Date(),"FAC-002",250.0,10.0,260.0,cliente);
        when(facturaRepository.findById(1)).thenReturn(Optional.of(facturaActualizada));
        when(facturaRepository.save(any(Factura.class))).thenReturn(facturaActualizada);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        Factura factura1 = facturaService.update(1,1,facturaActualizada);
        assertNotNull(factura1);
        assertEquals("FAC-002",factura1.getNumFactura());
        assertEquals(260.00,factura1.getTotal());
        verify(facturaRepository).save(any(Factura.class));


    }
    @Test
    public void testDelete(){
        when(facturaRepository.existsById(1)).thenReturn(false);
        facturaService.delete(1);
        verify(facturaRepository,times(0)).deleteById(1);
    }



}
