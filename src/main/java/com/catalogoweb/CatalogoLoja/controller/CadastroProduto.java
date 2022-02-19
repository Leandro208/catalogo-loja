package com.catalogoweb.CatalogoLoja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadastroProduto {

	@GetMapping("/cadastrar")
	public String inicio() {
		return "cadastroProduto";
	}
}
