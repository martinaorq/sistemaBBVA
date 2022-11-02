package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.Estado;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PagoQrService implements IPagoQrService {
    @Autowired
    private MovimientoRepo repo;

    @Autowired
    private ICodigoQrService service;
    private static final String METODO_PAGO= "QR";

    @Override
    public String pagoConQr(Tarjeta tarjetaPago, Persona emisorPago, Persona receptorPago, Double importe) {
        Movimiento mov=new Movimiento();
        mov.setTipoDeMovimiento("Pago con QR");
        mov.setMetodoPago(METODO_PAGO);
        mov.setTarjetaPago(tarjetaPago);
        mov.setEmisorPago(emisorPago);
        mov.setReceptorPago(receptorPago);
        mov.setImporte(importe);
        mov.setFecha(LocalDate.now());
        mov.setCodigoQr(service.nuevoCodigoQr(importe, mov.getReceptorPago().getNombre(),METODO_PAGO));
        mov.getCodigoQr().setEstado(Estado.CERRADO);
        repo.save(mov);
        return mov.toString();
    }
}
