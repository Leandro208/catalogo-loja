package com.catalogoweb.CatalogoLoja.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.catalogoweb.CatalogoLoja.repository.ProdutoRepository;
import com.catalogoweb.CatalogoLoja.service.ProdutoService;

@Controller
public class InicioController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/")
	public String inicio(ModelMap model) {
		model.addAttribute("produtosEncontrados", service.buscarTodos());
		return "index";
	}

}
