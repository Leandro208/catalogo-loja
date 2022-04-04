package com.catalogoweb.CatalogoLoja.controller;


import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catalogoweb.CatalogoLoja.dominio.Produto;
import com.catalogoweb.CatalogoLoja.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class BuscaProduto {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/buscar")
	public String entrarBusca() {
		return "buscaProduto";
	}
	
	
	@GetMapping("/mostrar")
	public String mostrar(
			@RequestParam(name="nome", required = false) String nome,
			@RequestParam(name="mostrarTodosDados", required = false) Boolean mostrarTodosDados,
			HttpSession sessao,
			ModelMap model
			) {
		
		
		
		List<Produto> produtosEncontrados = produtoRepository.findByTitulo(nome.toUpperCase());
		
		model.addAttribute("produtosEncontrados", produtosEncontrados);
		if(mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		} else {
			model.addAttribute("mostrarTodosDados", false);
		}
		
		
		if(nome != null ) {
		model.addAttribute("nomeBuscado", nome);
		} 
		
		return "buscaProduto";
	}
	
	
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer idProduto,
							HttpSession sessao,
							RedirectAttributes attr) {
		
		produtoRepository.deleteById(idProduto);
		
		attr.addFlashAttribute("msgSucesso", "Produto removido com sucesso");
		return "redirect:/produtos/mostrar";
	}
	
}
