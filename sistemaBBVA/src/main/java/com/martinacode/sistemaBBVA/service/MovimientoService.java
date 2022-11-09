package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.Estado;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService implements IMovimientoService{

    @Autowired
    private MovimientoRepo movimientoRepo;
    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    private TarjetaRepo tarjetaRepo;


    @Override
    public String insertarMovimiento(Movimiento m) {
        movimientoRepo.save(m);
        return "Movimiento realizado!"+ m.toString();
    }

    @Override
    public List<Movimiento> listarMovimientoCliente(Long idCliente) {
        List <Movimiento> lista = movimientoRepo.findAll();
        List <Movimiento> listaResultado = lista.stream()
                .filter(movimiento->
                        idCliente.equals(movimiento.getEmisorPago().getId())||
                        idCliente.equals(movimiento.getReceptorPago().getId()))
                        .collect(Collectors.toList());
        return listaResultado;
    }

    @Override
    public List<Movimiento> listarMovimientoClienteTarjeta(Long idCliente, Long idTarjeta) {
        List <Movimiento> lista = movimientoRepo.findAll();
        List <Movimiento> listaResultado = lista.stream()
                .filter(movimiento->
                        idCliente.equals(movimiento.getEmisorPago().getId())||
                                idCliente.equals(movimiento.getReceptorPago().getId())&&
                                        idTarjeta.equals(movimiento.getTarjetaPago().getId()))
                .collect(Collectors.toList());
        return listaResultado;
    }

    @Override
    public String eliminarMovimiento(Long idMovimiento) {
        Movimiento mov= movimientoRepo.getReferenceById(idMovimiento);
        Persona emisor=mov.getEmisorPago();
        Persona receptor=mov.getReceptorPago();
        Tarjeta tarjeta=mov.getTarjetaPago();
        emisor.borrarMovimientoPago(idMovimiento);
        receptor.borrarMovimientoCobro(idMovimiento);
        tarjeta.borrarMovimiento(idMovimiento);
        movimientoRepo.deleteById(idMovimiento);
        return "Se ha eliminado el movimiento por completo!";
    }
}
