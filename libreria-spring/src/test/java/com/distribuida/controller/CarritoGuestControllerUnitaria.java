package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.service.CarritoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarritoGuestControllerUnitaria {
    @Mock
    private CarritoService carritoService;

    @InjectMocks
    private CarritoGuestController carritoGuestController;

    private Carrito testCarrito;

    @BeforeEach
    void setUp(){
        testCarrito = new Carrito();
        testCarrito.setIdCarrito(1L);
        testCarrito.setToken("test-token");
        testCarrito.setSubtotal(BigDecimal.valueOf(100.0));
        testCarrito.setTotal(BigDecimal.valueOf(115.0));
    }

    @Test
    void testCreateOrGet_Success(){
        when(carritoService.getOrCreateByToken("token")).thenReturn(testCarrito);

        ResponseEntity<Carrito> response = carritoGuestController.createOrGet("token");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getIdCarrito());

        verify(carritoService, times(1)).getOrCreateByToken("token");
    }


}
