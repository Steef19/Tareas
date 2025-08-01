package com.distribuida.test;

import com.distribuida.entities.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutorTest {
    public Autor autor;

    @BeforeEach
    public void setup(){
        autor = new Autor(1,"Miguel","Hernandez","España","Av.nose","09123456","Hernan@gmail.com");

    }
    @Test
    public void TestAutorConstructorAndGetter(){
        assertAll("Validar datos de autor",
                () -> assertEquals(1,autor.getIdAutor()),
                () -> assertEquals("Miguel",autor.getNombre()),
                () -> assertEquals("Hernandez", autor.getApellido()),
                () -> assertEquals("España",autor.getPais()),
                () -> assertEquals("Av.nose", autor.getDireccion()),
                () -> assertEquals("09123456", autor.getTelefono()),
                () -> assertEquals("Hernan@gmail.com",autor.getCorreo())

                );

    }

    @Test
    public void testAutorSetters(){
        autor.setIdAutor(1);
        autor.setNombre("Paulina");
        autor.setApellido("Silva");
        autor.setPais("Colombia");
        autor.setDireccion("Av. Siempre Viva");
        autor.setTelefono("0987654321");

        autor.setCorreo("paulina@ejemplo.com");
        assertAll("Validar datos Autor, Setters",
                () -> assertEquals(1, autor.getIdAutor()),
                () -> assertEquals("Paulina", autor.getNombre()),
                () -> assertEquals("Silva", autor.getApellido()),
                () -> assertEquals("Colombia", autor.getPais()),
                () -> assertEquals("Av. Siempre Viva", autor.getDireccion()),
                () -> assertEquals("0987654321", autor.getTelefono()),
                () -> assertEquals("paulina@ejemplo.com", autor.getCorreo())
        );



    }

    @Test
    public void testAutorToString(){
        String str = autor.toString();
        assertAll("Validar datos Autor",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Miguel")),
                () -> assertTrue(str.contains("Hernandez")),
                () -> assertTrue(str.contains("España")),
                () -> assertTrue(str.contains("Av.nose")),
                () -> assertTrue(str.contains("09123456")),
                () -> assertTrue(str.contains("Hernan@gmail.com"))

                );
    }




}
