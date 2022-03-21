package com.catalogoweb.CatalogoLoja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.catalogoweb.CatalogoLoja.dominio.Produto;

@Controller
@RequestMapping("/produtos")
public class BuscaProduto {

	@GetMapping("/buscar")
	public String entrarBusca() {
		return "buscaProduto";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/mostrar")
	public String mostrar(
			@RequestParam(name="nome", required = false) String nome,
			@RequestParam(name="mostrarTodosDados", required = false) Boolean mostrarTodosDados,
			HttpSession sessao,
			ModelMap model
			) {
		
		//Captura a lista com todos os usuarios cadastrados em memoria
		
		List<Produto> produtosCadastrados = (List<Produto>) sessao.getAttribute("produtosCadastrados");
		
		List<Produto> produtosEncontrados = new ArrayList<>();
		
		if(nome == null || nome.isEmpty()) {
			//se nao estiver digitado nada na busca sera exibido tudo
			produtosEncontrados = produtosCadastrados;
		} else {
			// se tiver buscado algo
			
			//se tiver produtos cadastrado
			if(produtosCadastrados != null) {
				
				//Pegando pelo valor digitado
				
				produtosEncontrados = produtosCadastrados.stream().filter(
						p -> p.getTitulo().toLowerCase().contains(
								nome.toLowerCase())).collect(Collectors.toList());
				
			}
		}
		
		model.addAttribute("produtosEncontros", produtosEncontrados);
		if(mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		}
		
		model.addAttribute("nomeBuscado", nome);
		
		return "buscaProduto";
	}
}
