package com.distribuida.service;


import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.model.*;
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
public class FacturaDetalleServicioTestUnitaria {
    @Mock
    private FacturaDetalleRepository facturaDetalleRepository;

    @InjectMocks
    private FacturaDetalleServicelmpl facturaDetalleService;

    private FacturaDetalle facturaDetalle;
    private Libro libro;
    private Factura factura;
    private Autor autor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("SAC");
        autor.setApellido("XD");

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setDescripcion("Describe");

        libro = new Libro(1,"PEUS","XD",25,"2da edicion","Español",new Date(),
                "Dura","es pues XD","254",35,"Llamativa","XDD",123.0,categoria, autor);

        Cliente cliente = new Cliente(1,"125444","Snaider","Doe","Av,Nav","069252","xd@gmail.com");

        factura = new Factura(1,new Date(),"FAC-002",12.0,5.0,17.0,cliente);

        facturaDetalle = new FacturaDetalle(1,15,25.0,libro,factura,autor);

    }

    @Test
    public void testFindAll(){
        when(facturaDetalleRepository.findAll()).thenReturn(Arrays.asList(facturaDetalle));
        List<FacturaDetalle> detalles = facturaDetalleService.findAll();
        assertNotNull(detalles);
        assertEquals(1,detalles.size());
        verify(facturaDetalleRepository,times(1)).findAll();

    }

    @Test
    public void testFindOne(){
        when(facturaDetalleRepository.findById(1)).thenReturn(Optional.of(facturaDetalle));
        FacturaDetalle detalle = facturaDetalleService.findOne(1);
        assertNotNull(detalle);
        assertEquals(15,detalle.getCantidad());
        assertEquals(25.0,detalle.getSubtotal());
        verify(facturaDetalleRepository,times(1)).findById(1);

    }


    @Test
    public void testSave(){
        when(facturaDetalleRepository.save(facturaDetalle)).thenReturn(facturaDetalle);
        FacturaDetalle saved =  facturaDetalleService.save(facturaDetalle);

        assertNotNull(saved);
        assertEquals(2,saved.getCantidad());
        verify(facturaDetalleRepository,times(1)).save(facturaDetalle);

    }

    @Test
    public void testUpdate(){
        FacturaDetalle detalleActualizado = new FacturaDetalle(1,4,80.0,libro,factura,autor);
        when(facturaDetalleRepository.findById(1)).thenReturn(Optional.of(facturaDetalle));
        when(facturaDetalleRepository.save(any(FacturaDetalle.class))).thenReturn(detalleActualizado);

        FacturaDetalle Fac = facturaDetalleService.update(1,detalleActualizado);
        assertNotNull(Fac);
        assertEquals(3,Fac.getCantidad());
        assertEquals(85.0,Fac.getSubtotal());

        verify(facturaDetalleRepository).save(any(FacturaDetalle.class));

    }

    @Test
    public void testDelete() {
        when(facturaDetalleRepository.existsById(1)).thenReturn(false);
        facturaDetalleService.delete(1);
        verify(facturaDetalleRepository, times(0)).deleteById(1);

    }
}
