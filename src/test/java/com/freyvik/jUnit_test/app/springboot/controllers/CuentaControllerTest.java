package com.freyvik.jUnit_test.app.springboot.controllers;

import com.freyvik.jUnit_test.app.springboot.services.CuentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static com.freyvik.jUnit_test.app.springboot.models.Datos.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Test
    void testDetalle() throws Exception {
        // Given
        when(cuentaService.findById(1L)).thenReturn(crearCuenta001().orElseThrow());

        // When
        mockMvc.perform(get("/api/cuentas/detalle/1").contentType(MediaType.APPLICATION_JSON))

        // Then
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.persona").value("Frey"))
            .andExpect(jsonPath("$.saldo").value("1500"));

        verify(cuentaService).findById(1L);
    }
}