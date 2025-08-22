package com.distribuida.dao;


import com.distribuida.model.Autor;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
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
@Rollback(false)
public class FacturaDetalleRepositoriTestIntegracion {
    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void findAll(){
        List<FacturaDetalle> detalles = facturaDetalleRepository.findAll();
        assertNotNull(detalles);
        for (FacturaDetalle detalle : detalles){
            System.out.println(detalle);
        }
    }

    @Test
    public void findOne(){
        Optional<FacturaDetalle> detalle = facturaDetalleRepository.findById(1);
        assertTrue(detalle.isPresent());
        System.out.println(detalle.get());
    }

    @Test
    public void save(){
        Optional<Libro> libro = libroRepository.findById(1);
        Optional<Factura> factura = facturaRepository.findById(1);
        Optional<Autor> autor = autorRepository.findById(1);

        assertTrue(libro.isPresent());
        assertTrue(factura.isPresent());
        assertTrue(autor.isPresent());

        FacturaDetalle detalle = new FacturaDetalle();
        detalle.setCantidad(8);
        detalle.setSubtotal(18.0);
        detalle.setLibro(libro.get());
        detalle.setFactura(factura.get());
        detalle.setAutor(autor.get());

        FacturaDetalle guardado = facturaDetalleRepository.save(detalle);
        assertNotNull(guardado.getIdFacturaDetalle());
        System.out.println("Registrado"+ guardado);

    }
    @Test
    public void update(){
        Optional<FacturaDetalle> opt = facturaDetalleRepository.findById(1);
        assertTrue(opt.isPresent());

        FacturaDetalle detalle = opt.get();
        detalle.setCantidad(15);
        detalle.setSubtotal(153.0);

        FacturaDetalle Actualizado = facturaDetalleRepository.save(detalle);
        assertEquals(15, Actualizado.getCantidad());
        assertEquals(153.0,Actualizado.getSubtotal());
        System.out.println("Actualizado"+ Actualizado);


    }

    @Test
    public void delete(){
        int id = 4;
        if (facturaDetalleRepository.existsById(id)){
            facturaDetalleRepository.deleteById(id);
            Optional<FacturaDetalle> eliminado = facturaDetalleRepository.findById(id);
            assertFalse(eliminado.isPresent());
            System.out.println("Se elimino correctamente");
        } else {
            System.out.println("No se encontro el ID"+ id);
        }

    }


}
