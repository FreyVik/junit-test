package com.freyvik.jUnit_test.app.springboot.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import com.freyvik.jUnit_test.app.springboot.models.TransaccionDTO;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@Tag("WebClient")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CuentaControllerWebTestClientTest {

    @Autowired
    private WebTestClient client;

    @Test
    void testTransferir() {
        // Given
        TransaccionDTO dto = new TransaccionDTO();
        dto.setCuentaOrigenId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setBancoId(1L);
        dto.setMonto(new BigDecimal("100"));

        // When
        client.post().uri("http://localhost:8080/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.mensaje").isNotEmpty()
                .jsonPath("$.mensaje").value(is("Transferencia realizada con exito"))
                .jsonPath("$.mensaje").isEqualTo("Transferencia realizada con exito")
                .jsonPath("$.mensaje").value(valor -> {
                    assertEquals("Transferencia realizada con exito", valor);
                });
    }
}
