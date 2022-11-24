package com.freyvik.jUnit_test.app.springboot.services;

import com.freyvik.jUnit_test.app.springboot.models.Banco;
import com.freyvik.jUnit_test.app.springboot.models.Cuenta;
import com.freyvik.jUnit_test.app.springboot.repositories.BancoRepository;
import com.freyvik.jUnit_test.app.springboot.repositories.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CuentaServiceImp implements CuentaService {
    private final CuentaRepository cuentaRepository;
    private final BancoRepository bancoRepository;

    public CuentaServiceImp(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.bancoRepository = bancoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public int resvisarTotalTransferencias(Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
        return banco.getTotalTransfers();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow();
        return cuenta.getSaldo();
    }

    @Override
    @Transactional
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto, Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId).orElseThrow();

        int totalTransferencias = banco.getTotalTransfers();
        banco.setTotalTransfers(++totalTransferencias);
        bancoRepository.save(banco);

        Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen).orElseThrow();
        cuentaOrigen.debito(monto);
        cuentaRepository.save(cuentaOrigen);

        Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino).orElseThrow();
        cuentaDestino.credito(monto);
        cuentaRepository.save(cuentaDestino);
    }
}
