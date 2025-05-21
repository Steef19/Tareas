package com.distribuida.test;

import com.distribuida.entities.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    public  void setup(){
        cliente = new Cliente(1,"1701234567","Juan","Taipe","Av.niidea","09999999","taipw@gamil.com");
    }
    @Test
    public void  testClienteConstructorAndGetters(){
        assertAll("Validar datos Cliente,Constructor y Getters",
                () -> assertEquals(1,cliente.getIdCliente()),
                () -> assertEquals("1701234567",cliente.getCedula()),
                () -> assertEquals("Juan", cliente.getNombre()),
                () -> assertEquals("Taipe",cliente.getApellido()),
                () -> assertEquals("Av.niidea",cliente.getDireccion()),
                () -> assertEquals("09999999",cliente.getTelefono()),
                () -> assertEquals("taipw@gamil.com",cliente.getCorreo())

        );
        /// /

    }
    @Test
    public void testClienteConstructorAndSetters(){

        cliente.setIdCliente(2);
        cliente.setCedula("173549565564");
        cliente.setNombre("Pepe");
        cliente.setApellido("chupin");
        cliente.setDireccion("perro");
        cliente.setTelefono("0978896435");
        cliente.setCorreo("pepeAgmail.com");


        assertAll("Validar Datos Clientes - Setters",
                () -> assertEquals(2,cliente.getIdCliente()),
                () -> assertEquals("173549565564",cliente.getCedula()),
                () -> assertEquals("Pepe",cliente.getNombre()),
                () -> assertEquals("chupin",cliente.getApellido()),
                () -> assertEquals("perro",cliente.getDireccion()),
                () -> assertEquals("0978896435",cliente.getTelefono()),
                () -> assertEquals("pepeAgmail.com",cliente.getCorreo())
        );


    }


    @Test
    public void testClienteToString(){
        String str = cliente.toString();
        assertAll("Validar Datos clietne - ToString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1701234567")),
                () -> assertTrue(str.contains("Juan")),

                () -> assertTrue(str.contains("Taipe")),
                () -> assertTrue(str.contains("Av.niidea")),
                () -> assertTrue(str.contains("09999999")),
                () -> assertTrue(str.contains("taipw@gamil.com"))

                );
//k//


    }

}