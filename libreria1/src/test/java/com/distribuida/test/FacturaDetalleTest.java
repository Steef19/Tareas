package com.distribuida.test;

import com.distribuida.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleTest {

    private FacturaDetalle facturaDetalle;
    private Libro libro;
    private Factura factura;
    private Autor autor;

    @BeforeEach
    public void setup(){
        Categoria categoria = new Categoria(1,"Miedo","Libro de terror");
        autor = new Autor(1,"Miguel","king","EEUU","Av.Siempreviva","0998000","king@gmail.com");
        libro = new Libro(1,"El rey","MEX",500,"la cabaña","Español",new Date(),"Negro","Libro de miedo",
                "XD",500,"De carton","Elegante",9.55, categoria ,autor);

        factura = new Factura(1,"FAC-01",new Date(),10.00,1.25,11.25,new Cliente());
        facturaDetalle = new FacturaDetalle(1,2,90.00,libro,factura,autor);

    }


    @Test
    public void testFacturaDetalleConstructorAndGetter(){
        assertAll("Validar datos FacturaDetalle",
                () -> assertEquals(1, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(2,facturaDetalle.getCantidad()),
                () -> assertEquals(90.00,facturaDetalle.getSubtotal()),
                () -> assertEquals(libro, facturaDetalle.getLibro()),
                () -> assertEquals(factura, facturaDetalle.getFactura()),
                () -> assertEquals(autor,facturaDetalle.getAutor())

                );
    }


    @Test
    public void testFacturaDetalleSetters(){
        Libro libro1 = new Libro(1,"Python","San",300,"2da","Español",new Date(),"Amarilla",
                "Aprende rapido","22",60,"Llamativa","Elegante",9.55,new Categoria(2,"Programacion","libor de dev"),
                new Autor(2,"Mateo","Lopez","España","Av.soso","099867","NAV@gmail.com"));

        Factura factura1 = new Factura(2,"FAC-03",new Date(),56.00,20.00,64.00,new Cliente());
        Autor autor1 = new Autor(4,"Sac","NAV","Honduras","Av.sisisi","0258976","sac@gamil.com");

        facturaDetalle.setIdFacturaDetalle(3);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(150.0);
        facturaDetalle.setLibro(libro1);
        facturaDetalle.setFactura(factura1);
        facturaDetalle.setAutor(autor1);


        assertAll("Validar datos FacturaDetalle",
                () -> assertEquals(3,facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(2,facturaDetalle.getCantidad()),
                () -> assertEquals(150.0,facturaDetalle.getSubtotal()),
                () -> assertEquals(libro1,facturaDetalle.getLibro()),
                () -> assertEquals(factura1,facturaDetalle.getFactura()),
                () -> assertEquals(autor1,facturaDetalle.getAutor())

                );

    }


    @Test
    public void testFacturaDetalleToString() {
        String str = facturaDetalle.toString();
        assertAll("Validar FacturaDetalle - ToString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("90.0")),
                () -> assertTrue(str.contains("El rey")),
                () -> assertTrue(str.contains("Miguel")),
                () -> assertTrue(str.contains("king@gmail.com"))
        );
    }






}
