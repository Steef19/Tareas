package com.distribuida.test;

import com.distribuida.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriaTest {
    public Categoria categoria;

    @BeforeEach

    public void setup(){
        categoria = new Categoria(1,"Aventura","libro de aventuras");

    }

    @Test
    public void TestCategoriaAndGetter(){
        assertAll("Validar datos de categoria",
                () -> assertEquals(1, categoria.getIdCategoria()),
                () -> assertEquals("Aventura",categoria.getCategoria()),
                () -> assertEquals("libro de aventuras", categoria.getDescripcion())

                );

    }
    @Test
    public void TestCategoriaSetters(){
        categoria.setIdCategoria(1);
        categoria.setCategoria("historia");
        categoria.setDescripcion("mucha historia");

        assertAll("Validar datos de Categoria",
                () -> assertEquals(1,categoria.getIdCategoria()),
                () -> assertEquals("historia",categoria.getCategoria()),
                () -> assertEquals("mucha historia",categoria.getDescripcion())

                );

    }



    @Test
    public void TestCategoriaToString(){
        String str = categoria.toString();
        assertAll("Validar datos categoria",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Aventura")),
                () -> assertTrue(str.contains("libro de aventuras"))

                );



    }






}
