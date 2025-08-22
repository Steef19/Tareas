package com.distribuida.service;


import com.distribuida.dao.AutorRepository;
import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibroServicioTestUnitario {
    @Mock
    private LibroRepository libroRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private LibroServicelmpl libroService;

    private Libro libro;
    private Categoria categoria;
    private Autor autor;


    @BeforeEach
    private void setUp(){
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria(1,"Ciencia ficcion","Expectacular");
        autor = new Autor();
        libro = new Libro(1,"Funciones","XD",200,"1ra","Inglés",new Date(),"Obra maestra","Fuerte","98",5,
                "portada","Tapa negra",25.0,categoria,autor);

    }

    @Test
    public void testFindAll(){
        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro));
        List<Libro> libros = libroService.findAll();
        assertNotNull(libros);
        assertEquals(1,libros.size());
        verify(libroRepository,times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        Libro resultado = libroService.findOne(1);
        assertNotNull(resultado);
        assertEquals("Funciones",resultado.getTitulo());
        verify(libroRepository,times(1)).findById(1);

    }


    @Test
    public void testSave(){
        when(libroRepository.save(libro)).thenReturn(libro);
        Libro Guardar = libroService.save(libro);
        assertNotNull(Guardar);
        assertEquals("Funciones",Guardar.getTitulo());
        verify(libroRepository,times(1)).save(libro);

    }

    @Test
    public void testUpdate(){
        Libro libroActualizado = new Libro(1,"La ley","XCD",200,"1ra","Inglés",new Date(),"Texturizado","Buen libro","0987",1000,"Elegante","Unica",25.2,categoria,autor);

        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        when(libroRepository.save(any(Libro.class))).thenReturn(libroActualizado);
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));

        Libro actualizado =libroService.update(1,1,1,libroActualizado);

        assertNotNull(actualizado);
        assertEquals("La ley",actualizado.getTitulo());
        assertEquals(25.2,actualizado.getPrecio());
        verify(libroRepository,times(1)).save(any(Libro.class));


    }

    @Test
    public void testDelete(){
        when(libroRepository.existsById(1)).thenReturn(false);
        libroService.delete(1);
        verify(libroRepository,times(0)).deleteById(1);
    }


}
