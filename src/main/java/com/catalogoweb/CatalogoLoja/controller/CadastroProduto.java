package com.catalogoweb.CatalogoLoja.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String salvar (Produto produto, RedirectAttributes attr, HttpSession sessao) {
		
		//pegando valores da sessao
		
		Integer id = (Integer) sessao.getAttribute("id");
		List<Produto> produtosCadastrados = 
				(List<Produto>) sessao.getAttribute("produtosCadastrados");
		if(id == null) {
			id = 1;
		}
		if(produtosCadastrados == null) {
			produtosCadastrados = new ArrayList<>();
		}
		
		//Verificando se é cadastro
		if(produto.getId() == 0) {
			
			produto.setId(id);
			produtosCadastrados.add(produto);
			
			id++;
			sessao.setAttribute("id", id);
			sessao.setAttribute("produtosCadastrados", produtosCadastrados);
			
			attr.addFlashAttribute("msgSucesso", "Cadastro realizado com sucesso!");
		}
		
		else {
			produtosCadastrados.remove(produto);
			produtosCadastrados.add(produto);
			attr.addFlashAttribute("msgSucesso", "Produto editado com sucesso!");
		}
		return "redirect:/produtos/cadastrar";
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/editar/{id}")
	public String edicao(
			@PathVariable("id") Integer idProduto,
			ModelMap model,
			HttpSession sessao
			) {
		
		List<Produto> produtosCadastrados = (List<Produto>) sessao.getAttribute("produtosCadastrados");
		
		Produto produto = new Produto();
		
		produto.setId(idProduto);
		
		int posicao = produtosCadastrados.indexOf(produto);
		produto = produtosCadastrados.get(posicao);
		
		model.addAttribute("produto", produto);
		
		
		return "cadastroProduto";
	}
}
