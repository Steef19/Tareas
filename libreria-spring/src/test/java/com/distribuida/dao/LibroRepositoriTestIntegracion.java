package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class LibroRepositoriTestIntegracion {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void findAll(){
        List<Libro> libros = libroRepository.findAll();
        for (Libro libro : libros){
            System.out.println(libro.toString());
        }
    }

    @Test
    public void finOne(){
        Optional<Libro> libro = libroRepository.findById(1);
        System.out.println(libro.orElse(null));
    }


    @Test
    public void save(){
       Libro libro = new Libro();
        Optional<Categoria> categoria = categoriaRepository.findById(1);
        Optional<Autor> autor = autorRepository.findById(1);

        libro.setTitulo("New book");
        libro.setEditorial("Editoral XD");
        libro.setNumPaginas(300);
        libro.setEdicion("2da");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("un libro");
        libro.setTipoPasta("dura");
        libro.setiSBN("11111");
        libro.setNumEjemplares(20);
        libro.setProtada("portada XD");
        libro.setPresntacion("Presentacion de libro");
        libro.setPrecio(25.00);
        libro.setCategoria(categoria.orElse(null));
        libro.setAutor(autor.orElse(null));

        libroRepository.save(libro);


    }

    @Test
    public void update(){
        Optional<Libro> libroExistente = libroRepository.findById(1);
        Optional<Categoria> categoria = categoriaRepository.findById(2);
        Optional<Autor> autor = autorRepository.findById(2);

        if (libroExistente.isPresent()) {
            Libro libro = libroExistente.get();
            libro.setTitulo("Libro Actualizado");
            libro.setEditorial("Editorial Actualizada");
            libro.setNumPaginas(250);
            libro.setEdicion("2da");
            libro.setIdioma("Inglés");
            libro.setFechaPublicacion(new Date());
            libro.setDescripcion("Descripción actualizada");
            libro.setTipoPasta("Blanda");
            libro.setiSBN("0987654321");
            libro.setNumEjemplares(15);
            libro.setProtada("portada_actualizada.jpg");
            libro.setPresntacion("Presentación actualizada");
            libro.setPrecio(300.0);
            libro.setCategoria(categoria.orElse(null));
            libro.setAutor(autor.orElse(null));

            libroRepository.save(libro);
        }
    }

    @Test
    public void delete() {
        int id = 5; // Cambia el id según sea necesario
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        }
    }



}
