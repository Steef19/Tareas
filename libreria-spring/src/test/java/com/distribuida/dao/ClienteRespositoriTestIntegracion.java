package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)


public class ClienteRespositoriTestIntegracion {



        @Autowired
        private ClienteRepository clienteRepository;

        @Test
        public void findAll() {
            List<Cliente> cliente = clienteRepository.findAll();
            assertNotNull(cliente);
            assertTrue(cliente.size() > 0);

            for (Cliente item : cliente) {
                System.out.println(item.toString());
            }

        }

        @Test
    public void fineOne(){
            Optional<Cliente> cliente = clienteRepository.findById(39);
            assertTrue(cliente.isPresent(),"El clietne con id=1, deberia existir");
            System.out.println(cliente.toString());
        }


        @Test
    public void save() {
            Cliente cliente = new Cliente(0, "1755804091", "maria", "TAP", "Av.XD", "09999999", "mar@gmail.com");

            clienteRepository.save(cliente);
            assertNotNull(cliente.getIdCliente(), "El cliente guardado debe tener un id");
            assertEquals("175580808", cliente.getCedula());
            assertEquals("Juan", cliente.getNombre());

        }


        @Test
    public  void update(){
            Optional<Cliente> cliente = clienteRepository.findById(40);
            assertTrue(cliente.isPresent(),"El clietne con id=40 debe existir XD");
            cliente.orElse(null).setCedula("22222222");
            cliente.orElse(null).setNombre("maria2");
            cliente.orElse(null).setApellido("TPS");
            cliente.orElse(null).setDireccion("Av.XD");
            cliente.orElse(null).setTelefono("099999");
            cliente.orElse(null).setCorreo("jaj@gmail.com");


           Cliente clienteActualizado = clienteRepository.save(cliente.orElse(null));

            assertEquals("maria2",clienteActualizado.getNombre());
            assertEquals("Taipe22", clienteActualizado.getApellido());
        }



        @Test
    public  void  delete(){
            if(clienteRepository.existsById(39)){
                clienteRepository.deleteById(39);


            }
            assertFalse(clienteRepository.existsById(39),"El id=39, debe haberce eliminado");

        }



}

