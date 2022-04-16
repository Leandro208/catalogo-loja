package com.catalogoweb.CatalogoLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.catalogoweb.CatalogoLoja.dominio.Produto;
import com.catalogoweb.CatalogoLoja.repository.ProdutoRepository;



@Controller
@RequestMapping("/produtos")
public class Detalhes {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/detalhes/{id}")
	public String entrarDetalhe(@PathVariable("id") Integer idProduto,
								ModelMap model) {
		
		
		Produto produto = produtoRepository.findById(idProduto).get();
		
		model.addAttribute("produto", produto);
		
		return "produtoDetalhe";
	}

}
