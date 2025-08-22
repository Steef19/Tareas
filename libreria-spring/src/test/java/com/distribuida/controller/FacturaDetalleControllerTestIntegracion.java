package com.distribuida.controller;


import com.distribuida.model.Autor;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import com.distribuida.service.FacturaDetalleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SuppressWarnings("removal")
@WebMvcTest(FacturaDetalleController.class)
public class FacturaDetalleControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturaDetalleService facturaDetalleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws  Exception{
        FacturaDetalle facturaDetalle = new FacturaDetalle(1,12,12.25,new Libro(),new Factura(),new Autor());
        Mockito.when(facturaDetalleService.findAll()).thenReturn(List.of(facturaDetalle));

        mockMvc.perform(get("/api/factura-detalle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value(12));
    }

    @Test
    public void testSave() throws Exception{
        FacturaDetalle facturaDetalle = new FacturaDetalle(1,12,12.25,new Libro(),new Factura(),new Autor());
        Mockito.when(facturaDetalleService.save(any(FacturaDetalle.class))).thenReturn(facturaDetalle);

        mockMvc.perform(post("/api/factura-detalle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(facturaDetalle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(12));


    }
    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete("/api/factura-detalle/2")).andExpect(status().isNoContent());
    }

}