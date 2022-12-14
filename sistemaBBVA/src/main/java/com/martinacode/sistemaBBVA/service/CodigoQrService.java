package com.martinacode.sistemaBBVA.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.repository.QrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class CodigoQrService implements ICodigoQrService {
    @Autowired
    ResourceLoader resourceloader;

    @Autowired
    private QrRepo qrRepo;

    Resource imagen;
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = new BitMatrix(1);



    @Override
    public Qr nuevoCodigoQr(double importe, String nombrePersona, String nombreMercado) {
        Qr nuevoQr=new Qr();
        nuevoQr.setImporte(importe);
        nuevoQr.setNombreMercado(nombreMercado);
        nuevoQr.setNombreQr(Double.toString(importe)+nombrePersona+nombreMercado+"QR");
        nuevoQr.setCodigoQR(generarCodigoQr(nuevoQr));
        qrRepo.save(nuevoQr);
        return nuevoQr;
    }

    public byte[] generarCodigoQr(Qr qr){
        String nombreQr=qr.getNombreQr();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(nombreQr, BarcodeFormat.QR_CODE, 250, 250);
        } catch (WriterException e) { throw new RuntimeException(e); }
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = pngOutputStream.toByteArray();
        return byteArray;
    }

    public String generarImagenQr(Qr qr) {
        String idQr=Long.toString(qr.getId());
        String absolutePath= getIdPath(idQr);

        try {
            bitMatrix = qrCodeWriter.encode(qr.getNombreQr(), BarcodeFormat.QR_CODE, 250, 250);
        } catch (WriterException e) {
            throw new EncodeException("El codigo fall?? porque "+e.getMessage());
        }
        Path path = FileSystems.getDefault().getPath(absolutePath);
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (IOException e) {
            throw new WriteToPathException(e.getMessage());
        }
        return absolutePath;
    }

    public String getIdPath(String idQr){
        imagen= resourceloader.getResource("classpath:static/qrcode.png");
        File file = null;
        try {
            file = imagen.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String firstPath = file.getAbsolutePath();
        String absolutePath= firstPath.replace("qrcode.png","qrcode"+idQr+".png");
        return absolutePath;
    }

    public void setQrRepo(QrRepo qrRepo) {
        this.qrRepo = qrRepo;
    }

    @Override
    public String consultarDatosQr(Qr codigoQr) {
        return codigoQr.toString();
    }

    public void setResourceloader(ResourceLoader resourceloader) {
        this.resourceloader = resourceloader;
    }

    public void setQrCodeWriter(QRCodeWriter qrCodeWriter) {
        this.qrCodeWriter = qrCodeWriter;
    }

    public void setBitMatrix(BitMatrix bitMatrix) {
        this.bitMatrix = bitMatrix;
    }
}


