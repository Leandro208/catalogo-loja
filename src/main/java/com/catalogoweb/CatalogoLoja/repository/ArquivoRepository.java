package com.catalogoweb.CatalogoLoja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogoweb.CatalogoLoja.dominio.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
	
}
