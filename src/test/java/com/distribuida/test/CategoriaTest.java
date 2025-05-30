package com.distribuida.test;

import com.distribuida.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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



}
