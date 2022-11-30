package com.martinacode.sistemaBBVA.model;

import com.martinacode.sistemaBBVA.service.CodigoQrService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class QrTest {
    @InjectMocks
    CodigoQrService service;

    @Mock
    Qr qrCode;

    @Before
    void setup(){
        qrCode.setNombreQr("Codigo");
        qrCode.setId(3L);
    }

    @Test
    void generarCodigoQr() {
    }

    @Test
    void generarImagenQr() {
    }

    @Test
    void getPath() throws IOException {
        String mockFile= "This is my file";
        InputStream is=new ByteArrayInputStream(mockFile.getBytes());
        ResourceLoader resourceLoader= mock(ResourceLoader.class);
        Resource mockResource= mock(Resource.class);

        service.setResourceloader(resourceLoader);

        doReturn(mockResource).when(resourceLoader).getResource(anyString());
        doReturn(new File(mockFile)).when(mockResource).getFile();
        String idPath= service.getIdPath("3");
        assertNotEquals(null, idPath);

    }
}