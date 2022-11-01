package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.repository.CompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CompraService implements ICompraService{
    @Autowired
    private CompraRepo repoCompra;
    public CompraService() {
    }

    public CompraService(CompraRepo repoCompra) {
        this.repoCompra = repoCompra;
    }

    public CompraRepo getRepoCompra() {
        return repoCompra;
    }

    public void setRepoCompra(CompraRepo repoCompra) {
        this.repoCompra = repoCompra;
    }

    @Override
    public List<Compra> listarComprasCliente(Long idCliente) {
        List <Compra> lista = repoCompra.findAll();
        List <Compra> listaResultado = lista.stream()
                .filter(compra-> idCliente.equals(compra.getPersona().getId()))
                .collect(Collectors.toList());
        return listaResultado;
    }

    @Override
    public List<Compra> listarComprasClienteTarjeta(Long idCliente, Long idTarjeta) {
        List <Compra> lista = repoCompra.findAll();
        List <Compra> listaResultado = lista.stream()
                .filter(compra-> idCliente.equals(compra.getPersona().getId())&&idTarjeta.equals(compra.getTarjeta().getId()))
                .collect(Collectors.toList());
        return listaResultado;
    }


}
