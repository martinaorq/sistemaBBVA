package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.Estado;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoQrService implements IPagoQrService {
    @Autowired
    private MovimientoRepo movimientoRepo;
    private static final String TIPO_DE_MOVIMIENTO= "QR";

    @Override
    public String pagoConQr(String metodoPago, Tarjeta tarjetaPago, Persona emisorPago, Persona receptorPago, Double importe) {
        Movimiento movimiento=new Movimiento();
        movimiento.setTipoDeMovimiento(TIPO_DE_MOVIMIENTO);
        movimiento.setMetodoPago(metodoPago);
        movimiento.setCodigoQr(new CodigoQrService().crearCodigoQr(importe, movimiento.getReceptorPago().getNombre(),metodoPago));
        movimiento.setTarjetaPago(tarjetaPago);
        movimiento.setEmisorPago(emisorPago);
        movimiento.setReceptorPago(receptorPago);
        movimiento.getCodigoQr().setEstado(Estado.CERRADO);
        movimientoRepo.save(movimiento);
        return movimiento.toString();
    }
}
