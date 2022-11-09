package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService implements IMovimientoService{

    @Autowired
    private MovimientoRepo movimientoRepo;
    private PersonaRepo personaRepo;

    @Override
    public List<Movimiento> listarMovimientoCliente(Long idCliente) {
        Persona p= personaRepo.getReferenceById(idCliente);
        return p.getMovimientosCobro();
    }

    @Override
    public List<Movimiento> listarMovimientoClienteTarjeta(Long idCliente, Long idTarjeta) {
    return null;
    }
}
