package com.distribuida.test;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacturaTest {
    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setup(){
       factura = new Factura();
       cliente = new Cliente(1,"1720392870","Juan","Taipe","Av.Guayauil","099986700","pepe@gmail.com");
       factura = new Factura();
       factura.setIdFactura(1);
       factura.setNumFactura("FAC-001");
       factura.setFecha(new Date());
       factura.setTotalNeto(1000.00);
       factura.setIva(15.25);
       factura.setTotal(1015.35);

       factura.setCliente(cliente);

    }

    @Test
    public void testFacturaConstructorAndGetter(){
        assertAll("Validar datos de factura",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("FAC-001", factura.getNumFactura()),
                () -> assertEquals(1000.00, factura.getTotalNeto()),
                () -> assertEquals(15.25, factura.getIva()),
                () -> assertEquals(1015.35, factura.getTotal()),
                () -> assertEquals("Juan", factura.getCliente().getNombre())

                );
    }

    @Test
    public void testFacturaSetters() throws Exception {

        factura.setIdFactura(2);
        factura.setNumFactura("F-002");
        //factura.setFecha(new Date());
        factura.setTotalNeto(200.0);
        factura.setIva(24.0);
        factura.setTotal(224.0);

        Cliente nuevoCliente = new Cliente(2, "1234567890", "Ana", "Pérez", "Av. Siempre Viva", "0987654321", "ana@example.com");
        factura.setCliente(nuevoCliente);

        assertAll("Validar datos Factura, Setters",
                () -> assertEquals(2, factura.getIdFactura()),
                () -> assertEquals("F-002", factura.getNumFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(200.0, factura.getTotalNeto()),
                () -> assertEquals(24.0, factura.getIva()),
                () -> assertEquals(224.0, factura.getTotal()),
                () -> assertEquals(nuevoCliente, factura.getCliente())
        );
    }

   @Test
    public void testFacturaToString(){
        String str = factura.toString();
        assertAll("Validar datos de factura",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-001")),
                () -> assertTrue(str.contains("1000.0")),
                () -> assertTrue(str.contains("15.2")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("pepe@gmail.com"))

                );
   }




}



