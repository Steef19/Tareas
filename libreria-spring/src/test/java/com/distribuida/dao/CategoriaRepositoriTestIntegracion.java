package com.distribuida.dao;

import com.distribuida.model.Categoria;
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
public class CategoriaRepositoriTestIntegracion {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void finAll(){
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 0);

        for (Categoria item : categorias){
            System.out.println(item.toString());
        }
    }
    @Test
    public void findOne(){
    Optional<Categoria> categoria = categoriaRepository.findById(1);
    assertTrue(categoria.isPresent(),"La categoria con id = 1 deberia existir");
    System.out.println(categoria.toString());

    }

    @Test
    public void save(){
        Categoria categoria = new Categoria(0,"Tecnologia","Electronica");
        categoriaRepository.save(categoria);
        assertNotNull(categoria.getCategoria(),"La categoira guardada debe tener un id");
        assertEquals("Tecnologia",categoria.getCategoria());

    }



    @Test
    public void update(){
        Optional<Categoria> categoria = categoriaRepository.findById(35);
        assertTrue(categoria.isPresent(),"La categoria con id =35 deberia existir para ser acutalizada ");
        categoria.orElse(null).setCategoria("Casa");
        categoria.orElse(null).setDescripcion("Productosxd");

        Categoria categoriaAcualizada = categoriaRepository.save(categoria.orElse(null));
        assertEquals("Casa",categoriaAcualizada.getCategoria());
        assertEquals("Productosxd",categoriaAcualizada.getDescripcion());


    }


    @Test
    public void delete(){
        if (categoriaRepository.existsById(35)){
            categoriaRepository.deleteById(35);
        }
        assertFalse(categoriaRepository.existsById(35),"La categoria con id = 35 deberia haberse eliminado");
    }



}
