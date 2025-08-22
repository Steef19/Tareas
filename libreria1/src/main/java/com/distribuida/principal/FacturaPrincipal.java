package com.distribuida.principal;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;

import java.util.Date;

public class FacturaPrincipal {
    public static void  main(String[] args){

        Factura factura = new Factura();

        Cliente cliente = new Cliente(1, "1726698267", "Juan", "Taipe","av.tumbaco", "0996889257", "ralbuja78@gmail.com" );


        factura.setIdFactura(1);
        factura.setNumFactura("FAC-00001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotalNeto(115.00);

        //inyeccion de dependencias


        factura.setCliente(cliente);
        System.out.println(factura.toString());
    }
}
