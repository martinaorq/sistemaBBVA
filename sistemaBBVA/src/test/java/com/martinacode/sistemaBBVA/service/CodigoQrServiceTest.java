package com.martinacode.sistemaBBVA.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.repository.QrRepo;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.google.zxing.client.j2se.MatrixToImageWriter.writeToPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CodigoQrServiceTest {

    @InjectMocks
    @Spy
    CodigoQrService service;

    @Mock
    Qr qrCode;

    @Mock
    QrRepo qrRepo;


    String mockFile;
    InputStream is;
    ResourceLoader resourceLoader;
    Resource mockResource;

    @Before
    void setup(){
        qrCode.setNombreQr("CodigoQR");
        qrCode.setId(1L);
        qrCode.setImporte(203.3);
        qrCode.setNombreMercado("Mastercard");
    }

    @BeforeEach
    void setUp() {
        mockFile= "This is my file";
        is=new ByteArrayInputStream(mockFile.getBytes());
        resourceLoader= mock(ResourceLoader.class);
        mockResource= mock(Resource.class);

        service.setResourceloader(resourceLoader);
        service.setQrRepo(qrRepo);
    }

    @Test
    void nuevoCodigoQr() {
        Qr nuevoQr= service.nuevoCodigoQr(203.3,"pepe","Mastercard");
        assertNotEquals(null, nuevoQr);
        assertEquals(nuevoQr.getNombreMercado(),"Mastercard");
    }

    @Test
    void generarCodigoQr() {
        doReturn("NombreQr").when(qrCode).getNombreQr();
        byte[] resultado= service.generarCodigoQr(qrCode);
        assertNotEquals(null,resultado);
    }

    @Test
    void generarImagenQr() throws IOException, WriterException {
        doReturn("NombreQr").when(qrCode).getNombreQr();
        doReturn("path").when(service).getIdPath(anyString());
        String resultado= service.generarImagenQr(qrCode);
        assertNotEquals(0,resultado.length());
    }

    @Test
    void generarImagenQrThrowsWriterException() throws WriterException {
        doReturn("NombreQr").when(qrCode).getNombreQr();
        doReturn("path").when(service).getIdPath(anyString());
        QRCodeWriter qrCodeWriter=spy(QRCodeWriter.class);
        service.setQrCodeWriter(qrCodeWriter);
        doThrow((WriterException.class)).when(qrCodeWriter).encode(anyString(),any(),anyInt(),anyInt());
        assertThrows(EncodeException.class,()->service.generarImagenQr(qrCode));
    }

    @Test
    void generarImagenQrThrowsIOException() throws IOException, WriterException {
        /*doReturn("NombreQr").when(qrCode).getNombreQr();
        doReturn("path").when(service).getIdPath(anyString());

        BitMatrix bm=new BitMatrix(1);
        QRCodeWriter qrCodeWriter=spy(QRCodeWriter.class);
        service.setQrCodeWriter(qrCodeWriter);
        doReturn(bm).when(qrCodeWriter).encode(anyString(),any(),anyInt(),anyInt());
        MatrixToImageWriter matrixToImageWriter=mock(MatrixToImageWriter.class);

        doThrow(IOException.class).when(matrixToImageWriter).writeToPath(any(),any(),any());
        assertThrows(WriteToPathException.class,()->service.generarImagenQr(qrCode));*/
    }

    @Test
    void getIdPathDevuelveValor() throws IOException {
        doReturn(mockResource).when(resourceLoader).getResource(anyString());
        doReturn(new File(mockFile)).when(mockResource).getFile();
        String idPath= service.getIdPath("3");
        assertNotEquals(null, idPath);
    }

    @Test
    void getIdPathTiraException() throws IOException {
        doReturn(mockResource).when(resourceLoader).getResource(anyString());
        doThrow(IOException.class).when(mockResource).getFile();

        assertThrows(RuntimeException.class,()-> service.getIdPath("3"));
    }


    @Test
    void consultarDatosQr() {
        String resultado = service.consultarDatosQr(qrCode);
        assertNotEquals(0,resultado.length());
    }

}