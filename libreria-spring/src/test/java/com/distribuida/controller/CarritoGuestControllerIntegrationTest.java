package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.service.CarritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarritoGuestController.class)
public class CarritoGuestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarritoService carritoService;

    private Carrito createTestCarrito(){
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setToken("test-token");
        carrito.setSubtotal(BigDecimal.valueOf(115.0));
        carrito.setTotal(BigDecimal.valueOf(100.0));
        return carrito;
    }

    @Test
    void testCreateOrGet() throws Exception{
        Carrito carrito = createTestCarrito();
        when(carritoService.getOrCreateByToken("Test-Token")).thenReturn(carrito);
        mockMvc.perform(post("/api/guest/cart")
                .param("token","Test-Token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCarrito").value(1))
                .andExpect(jsonPath("$.token").value("test-token"));
    }

}
