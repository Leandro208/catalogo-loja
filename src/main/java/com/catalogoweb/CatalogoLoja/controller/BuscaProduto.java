package com.catalogoweb.CatalogoLoja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuscaProduto {

	@GetMapping("/buscar")
	public String inicio() {
		return "buscaProduto";
	}
}
