package com.catalogoweb.CatalogoLoja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E,ID> extends JpaRepository<E, ID> {

}
