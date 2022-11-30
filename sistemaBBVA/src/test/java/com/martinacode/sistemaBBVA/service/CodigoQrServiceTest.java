package com.martinacode.sistemaBBVA.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.martinacode.sistemaBBVA.model.Qr;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    CodigoQrService service;

    @Mock
    Qr qrCode;

    String mockFile;
    InputStream is;
    ResourceLoader resourceLoader;
    Resource mockResource;

    @Before
    void setup(){
        qrCode.setNombreQr("Codigo");
        qrCode.setId(3L);
    }

    @BeforeEach
    void setUp() {
        mockFile= "This is my file";
        is=new ByteArrayInputStream(mockFile.getBytes());
        resourceLoader= mock(ResourceLoader.class);
        mockResource= mock(Resource.class);

        service.setResourceloader(resourceLoader);
    }

    @Test
    void nuevoCodigoQr() {
        Qr nuevoQr= service.nuevoCodigoQr(203.3,"pepe","Mastercard");
        assertNotEquals(null, nuevoQr);
        assertEquals(nuevoQr.getNombreMercado(),"Martercard");
    }

    @Test
    void generarCodigoQr() {
    }

    @Test
    void generarImagenQr() throws IOException, WriterException {


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
    }

}