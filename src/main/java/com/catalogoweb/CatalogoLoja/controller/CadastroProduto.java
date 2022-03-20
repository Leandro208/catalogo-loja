package com.catalogoweb.CatalogoLoja.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.catalogoweb.CatalogoLoja.dominio.Produto;

@Controller
@RequestMapping("/produtos")
public class CadastroProduto {

	@GetMapping("/cadastrar")
	public String entrarCadastro(ModelMap model) {
		model.addAttribute("produto", new Produto());
		return "cadastroProduto";
	}
	
	@ModelAttribute("categorias")
	public List<String> getCategorias(){
		return Arrays.asList("Celular", "fones", "Cabos", "Carregador", "caixa de som");
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/salvar")
	public String salvar (Produto produto, HttpSession sessao) {
		
		//pegando valores da sessao
		
		Integer id = (Integer) sessao.getAttribute("id");
		List<Produto> produtosCadastrados = 
				(List<Produto>) sessao.getAttribute("usuariosCadastrados");
		if(id == null) {
			id = 1;
		}
		if(produtosCadastrados == null) {
			produtosCadastrados = new ArrayList<>();
		}
		
		produto.setId(id);
		produtosCadastrados.add(produto);
		
		id++;
		sessao.setAttribute("id", id);
		sessao.setAttribute("produtosCadastrados", produtosCadastrados);
		
		return "cadastroProduto";
		
	}
}
