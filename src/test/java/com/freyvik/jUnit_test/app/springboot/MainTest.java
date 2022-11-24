package com.freyvik.jUnit_test.app.springboot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.freyvik.jUnit_test.app.springboot.models.Datos.*;

import com.freyvik.jUnit_test.app.springboot.exceptions.InsufficientMoneyException;
import com.freyvik.jUnit_test.app.springboot.models.Banco;
import com.freyvik.jUnit_test.app.springboot.models.Cuenta;
import com.freyvik.jUnit_test.app.springboot.repositories.BancoRepository;
import com.freyvik.jUnit_test.app.springboot.repositories.CuentaRepository;
import com.freyvik.jUnit_test.app.springboot.services.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
public class MainTest {
    @Autowired
    CuentaService cuentaService;

    @MockBean
    CuentaRepository cuentaRepository;
    @MockBean
    BancoRepository bancoRepository;

    // Using Mockito
    /*
    @InjectMocks
    CuentaServiceImp cuentaService;

    @Mock
    CuentaRepository cuentaRepository;
    @Mock
    BancoRepository bancoRepository;
     */


    @BeforeEach
    void setUp() {
        // Alternativo of use @InjectMocks & @Mock
        /*
        cuentaRepository = mock(CuentaRepository.class);
        bancoRepository = mock(BancoRepository.class);

        cuentaService = new CuentaServiceImp(cuentaRepository, bancoRepository);
         */
    }

    @Test
    void contextLoads() {
        when(cuentaRepository.findById(1L)).thenReturn(crearCuenta001());
        when(cuentaRepository.findById(2L)).thenReturn(crearCuenta002());
        when(bancoRepository.findById(1L)).thenReturn(crearBanco());

        BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
        BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);

        assertEquals("1500", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        cuentaService.transferir(1L, 2L, new BigDecimal("100"), 1L);
        saldoOrigen = cuentaService.revisarSaldo(1L);
        saldoDestino = cuentaService.revisarSaldo(2L);

        assertEquals("1400", saldoOrigen.toPlainString());
        assertEquals("2100", saldoDestino.toPlainString());

        verify(cuentaRepository, times(3)).findById(1L);
        verify(cuentaRepository, times(3)).findById(2L);
        verify(cuentaRepository, times(2)).save(any(Cuenta.class));

        verify(bancoRepository).findById(1L);
        verify(bancoRepository).save(any(Banco.class));
    }

    @Test
    void testException_MoneyInsufficient() {
        when(cuentaRepository.findById(1L)).thenReturn(crearCuenta001());
        when(cuentaRepository.findById(2L)).thenReturn(crearCuenta002());
        when(bancoRepository.findById(1L)).thenReturn(crearBanco());

        BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
        BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);

        assertThrows(InsufficientMoneyException.class, () -> {
            cuentaService.transferir(1L, 2L, new BigDecimal("1600"), 1L);
        });

        saldoOrigen = cuentaService.revisarSaldo(1L);
        saldoDestino = cuentaService.revisarSaldo(2L);

        assertEquals("1500", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());
    }
}
