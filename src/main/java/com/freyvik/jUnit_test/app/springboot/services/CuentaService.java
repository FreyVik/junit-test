package com.freyvik.jUnit_test.app.springboot.services;

import com.freyvik.jUnit_test.app.springboot.models.Cuenta;

import java.math.BigDecimal;

public interface CuentaService {
    Cuenta findById(Long id);
    int resvisarTotalTransferencias(Long bancoId);
    BigDecimal revisarSaldo(Long cuentaId);
    void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto, Long bancoId);
}
