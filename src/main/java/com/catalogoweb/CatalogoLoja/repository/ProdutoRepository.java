package com.catalogoweb.CatalogoLoja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.catalogoweb.CatalogoLoja.dominio.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Query("select p from Produto p where UPPER(p.titulo) like %:titulo%")
	List<Produto> findByTitulo(@Param("titulo") String titulo);
	

}
