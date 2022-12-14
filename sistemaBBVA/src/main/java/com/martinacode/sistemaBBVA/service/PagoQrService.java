package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.Estado;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class PagoQrService implements IPagoQrService {

    @Autowired
    private MovimientoRepo movimientoRepo;
    @Autowired
    private TarjetaRepo tarjetaRepo;
    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    private CodigoQrService service;
    private static final String METODO_PAGO= "QR";

    @Override
    public String pagoRealizadoConQr(Long tarjetaPago,Long emisorPago,Long receptorPago, Double importe) {
        Movimiento mov=new Movimiento();

        Persona emisor=personaRepo.getReferenceById(emisorPago);
        Persona receptor=personaRepo.getReferenceById(receptorPago);
        Tarjeta tarjeta=tarjetaRepo.getReferenceById(tarjetaPago);

        emisor.setMovimientosPago(mov);
        receptor.setMovimientosCobro(mov);
        tarjeta.setMovimientos(mov);

        mov.setTipoDeMovimiento("Pago con QR");
        mov.setMetodoPago(METODO_PAGO);
        mov.setTarjetaPago(tarjeta);
        mov.setEmisorPago(emisor);
        mov.setReceptorPago(receptor);
        mov.setImporte(importe);
        mov.setFecha(LocalDate.now());
        mov.setCodigoQr(service.nuevoCodigoQr(importe, receptor.getNombre(),METODO_PAGO));
        mov.getCodigoQr().setEstado(Estado.CERRADO);
        movimientoRepo.save(mov);
        return "Pago QR realizado!";
    }

    public Movimiento iniciarPagoQr(Long tarjetaPago, Long emisorPago, Long receptorPago, Double importe) throws IOException {
        Movimiento mov=new Movimiento();

        Persona emisor=personaRepo.getReferenceById(emisorPago);
        Persona receptor=personaRepo.getReferenceById(receptorPago);
        Tarjeta tarjeta=tarjetaRepo.getReferenceById(tarjetaPago);

        emisor.setMovimientosPago(mov);
        receptor.setMovimientosCobro(mov);
        tarjeta.setMovimientos(mov);

        mov.setTipoDeMovimiento("Pago con QR");
        mov.setMetodoPago(METODO_PAGO);
        mov.setTarjetaPago(tarjeta);
        mov.setEmisorPago(emisor);
        mov.setReceptorPago(receptor);
        mov.setImporte(importe);
        mov.setFecha(LocalDate.now());
        mov.setCodigoQr(service.nuevoCodigoQr(importe, receptor.getNombre(),METODO_PAGO));

        String path= service.generarImagenQr(mov.getCodigoQr());
        mov.setDescripcion(path);
        movimientoRepo.save(mov);
        return mov;
    }

    public Movimiento finalizarPagoQr(Long idMovimiento){
        Movimiento mov= movimientoRepo.getReferenceById(idMovimiento);
        mov.getCodigoQr().setEstado(Estado.CERRADO);
        movimientoRepo.save(mov);
        return mov;
    }

    @Override
    public String eliminarPagoQr(Long idMovimiento) {
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

    public void setMovimientoRepo(MovimientoRepo movimientoRepo) {
        this.movimientoRepo = movimientoRepo;
    }
}
