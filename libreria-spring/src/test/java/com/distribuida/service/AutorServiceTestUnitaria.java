package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.model.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class AutorServiceTestUnitaria {
    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorServicelmpl autorService;
    private Autor autor;

    @BeforeEach
    public void setUp(){
        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("XD");
        autor.setApellido("Tap");
        autor.setPais("España");
        autor.setDireccion("Av. sisi");
        autor.setTelefono("09876525");
        autor.setCorreo("TP@gamil.com");

    }

    @Test
    public void testFindAll(){
       when(autorRepository.findAll()).thenReturn(List.of(autor));
       List<Autor> autors = autorService.findAll();
       assertNotNull(autors);
       assertEquals(1, autors.size());
       verify(autorRepository, times(1)).findAll();

    }

    @Test
    public void testFindOneExistente(){
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        Autor resultado = autorService.findOne(1);
        assertNotNull(resultado);
        assertEquals("XD",resultado.getNombre());

    }

    @Test
    public void testFindOneNoExistente(){
        when(autorRepository.findById(2)).thenReturn(Optional.empty());
        Autor autor1 = autorService.findOne(2);
        assertNull(autor1);

    }
    @Test
    public void testSave() {
        when(autorRepository.save(autor)).thenReturn(autor);
        Autor resultado = autorService.save(autor);
        assertNotNull(resultado);
        assertEquals("XD", resultado.getNombre());
    }

    @Test
    public void testUpdateExistente(){
        Autor autorActualizado = new Autor();
        autorActualizado.setNombre("NAME");
        autorActualizado.setApellido("NAV");
        autorActualizado.setPais("Chile");
        autorActualizado.setDireccion("Av.null");
        autorActualizado.setTelefono("0998761");
        autorActualizado.setCorreo("nav@gmail.com");

        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        when(autorRepository.save(any())).thenReturn(autorActualizado);

        Autor restultado = autorService.update(1,autorActualizado);
        assertNotNull(restultado);
        assertEquals("NAME", restultado.getNombre());
        verify(autorRepository, times(1)).save(autor);


    }

    @Test
    public void testUpdateNoExisitente(){
        Autor nuevoAutor = new Autor();
        when(autorRepository.findById(2)).thenReturn(Optional.empty());

        Autor autor1 = autorService.update(2,nuevoAutor);
        assertNull(autor1);
        verify(autorRepository,never()).save(any());

    }

    @Test
    public void testDeleteExistente(){
        when(autorRepository.existsById(1)).thenReturn(true);
        autorService.delete(1);
        verify(autorRepository).deleteById(1);

    }

    @Test
    public void testDeleteNoExistente(){
        when(autorRepository.existsById(2)).thenReturn(false);
        autorService.delete(2);
        verify(autorRepository,never()).deleteById(anyInt());
    }

}
