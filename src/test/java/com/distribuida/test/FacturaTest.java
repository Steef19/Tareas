package com.distribuida.test;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacturaTest {
    private Factura factura;
    @BeforeEach
    public void setup(){
        factura = new Factura(1,"FAC-001",new Date(),15.25,12.67,57.36,1,);

    }
    @Test
    public void testFacturaConstuctorAndGetters(){
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        //inyeccion de dependencias
        factura.setCliente(new Cliente());
    }




}



