package com.catalogoweb.CatalogoLoja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.catalogoweb.CatalogoLoja.dominio.Produto;
import com.catalogoweb.CatalogoLoja.repository.ProdutoRepository;

@Controller
public class InicioController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/")
	public String inicio(ModelMap model) {
		
		List<Produto> produtosEncontrados = produtoRepository.findByTitulo("");
		model.addAttribute("produtosEncontrados", produtosEncontrados);
		return "index";
	}

}
