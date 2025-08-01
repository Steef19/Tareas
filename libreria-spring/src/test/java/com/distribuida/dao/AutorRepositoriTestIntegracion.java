package com.distribuida.dao;

import com.distribuida.model.Autor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.jar.JarOutputStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class AutorRepositoriTestIntegracion {
    @Autowired
    private AutorRepository autorRepository;



    @Test
    public void findAll(){
        List<Autor> autores = autorRepository.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() > 0);

        for (Autor autor : autores){
            System.out.println(autor.toString());
        }


    }

    @Test
    public void findOne(){
        Optional<Autor> autor = autorRepository.findById(1);
        assertTrue(autor.isPresent(),"El autor con id=1 deberia existir");
        System.out.println(autor.toString());

    }

    @Test
    public void save(){
        Autor autor = new Autor(0,"Gabriel","Garcia","Colombia","Av.Sdf","09986254","gabriel@gmail.com");
        autorRepository.save(autor);
        assertNotNull(autor.getIdAutor(),"El autor guardado debe tener un ID");
        assertEquals("Gabriel",autor.getNombre());
    }

    @Test
    public void update(){
      Optional<Autor> autor = autorRepository.findById(35);
      assertTrue(autor.isPresent(),"El autor con id= 35 deberia existir para ser acturalizado");

      autor.orElse(null).setNombre("Mario");
      autor.orElse(null).setApellido("Vargas");
        autor.orElse(null).setPais("España");
        autor.orElse(null).setDireccion("Lima");
        autor.orElse(null).setTelefono("0998755");
        autor.orElse(null).setCorreo("mar@gmail.com");

        Autor autorActualizado = autorRepository.save(autor.orElse(null));
        assertEquals("Mario",autorActualizado.getNombre());
        assertEquals("Vargas",autorActualizado.getApellido());


    }


    @Test
    public void delete(){
        if(autorRepository.existsById(39)){
            autorRepository.existsById(39);
        }
        assertFalse(autorRepository.existsById(39),"El autor con id = 39 deberia haverse eliminado");
    }

}
