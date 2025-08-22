package com.distribuida.principal;

import com.distribuida.entities.Autor;
import com.distribuida.entities.Factura;
import com.distribuida.entities.FacturaDetalle;
import com.distribuida.entities.Libro;

public class FacturaDetallePrincipal {
    public static void main(String[] args) {
        FacturaDetalle facturaDetalle = new FacturaDetalle();

        Libro libro = new Libro();
        Factura factura = new Factura();
        Autor autor = new Autor();

        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(23.23);

        facturaDetalle.setLibro(libro);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setAutor(autor);

        System.out.println(facturaDetalle.toString());
    }
}
