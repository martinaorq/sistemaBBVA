package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.CompraRepo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CompraRepoStub implements CompraRepo {
    @Override
    public List<Compra> findAll() {
        List <Compra> lista= new ArrayList<>();
        lista.add(new Compra(1L,"Chocolate","550","Milka", LocalDate.now(),new Persona(1L,23L,"pepe"),new Tarjeta(1L,"23","Visa")));
        lista.add(new Compra(2L,"Uvas","700","Granja", LocalDate.now(),new Persona(1L,23L,"pepe"),new Tarjeta(1L,"23","Visa")));
        lista.add(new Compra(3L,"Pasas","70","Granja", LocalDate.now(),new Persona(1L,23L,"pepe"),new Tarjeta(2L,"2336","Mastercard")));
        lista.add(new Compra(4L,"Cepita","300","Durazno", LocalDate.now(),new Persona(2L,2333L,"juana"),new Tarjeta(3L,"888","Visa")));
        return lista;
    }

    @Override
    public List<Compra> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Compra> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Compra> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Compra entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Compra> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Compra> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Compra> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Compra> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Compra> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Compra> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Compra> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Compra getOne(Long aLong) {
        return null;
    }

    @Override
    public Compra getById(Long aLong) {
        return null;
    }

    @Override
    public Compra getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Compra> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Compra> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Compra> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Compra> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Compra> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Compra> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Compra, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
