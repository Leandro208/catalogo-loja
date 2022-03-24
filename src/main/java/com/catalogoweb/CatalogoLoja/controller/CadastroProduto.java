package com.catalogoweb.CatalogoLoja.controller;




import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catalogoweb.CatalogoLoja.dominio.Produto;
import com.catalogoweb.CatalogoLoja.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class CadastroProduto {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/cadastrar")
	public String entrarCadastro(ModelMap model) {
		model.addAttribute("produto", new Produto());
		return "cadastroProduto";
	}
	
	@ModelAttribute("categorias")
	public List<String> getCategorias(){
		return Arrays.asList("Celular", "fones", "Cabos", "Carregador", "caixa de som");
	}
	
	
	@PostMapping("/salvar")
	public String salvar (Produto produto, RedirectAttributes attr, HttpSession sessao) {
		
		//cadastro e edição
		produtoRepository.save(produto);
		
		attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
		
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
