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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catalogoweb.CatalogoLoja.dominio.Produto;

import com.catalogoweb.CatalogoLoja.service.ProdutoService;
import com.catalogoweb.CatalogoLoja.utils.Pages;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/cadastrar")
	public String entrarCadastro(ModelMap model) {
		model.addAttribute("produto", new Produto());
		return Pages.TELA_CADASTRO;
	}

	@ModelAttribute("categorias")
	public List<String> getCategorias() {
		return Arrays.asList("Celular", "fones", "Cabos", "Carregador", "caixa de som");
	}

	@PostMapping("/salvar")
	public String salvar(Produto produto, RedirectAttributes attr, @RequestParam("file") MultipartFile arquivo) {
		produtoService.cadastrar(produto, arquivo);
		attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
		return "redirect:/produtos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String edicao(@PathVariable("id") Integer idProduto, ModelMap model) {
		model.addAttribute("produto", produtoService.bucarPorId(idProduto));
		return Pages.TELA_CADASTRO;
	}

	@GetMapping("/buscar")
	public String entrarBusca() {
		return Pages.TELA_BUSCA;
	}

	@GetMapping("/mostrar")
	public String mostrar(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "mostrarTodosDados", required = false) Boolean mostrarTodosDados, ModelMap model) {
		produtoService.buscar(model, nome, mostrarTodosDados);
		return Pages.TELA_BUSCA;
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer idProduto, HttpSession sessao, RedirectAttributes attr) {
		produtoService.removerPorId(idProduto.intValue());
		attr.addFlashAttribute("msgSucesso", "Produto removido com sucesso");
		return Pages.TELA_BUSCA;
	}

	@GetMapping("/detalhes/{id}")
	public String entrarDetalhe(@PathVariable("id") Integer idProduto, ModelMap model) {
		model.addAttribute("produto", produtoService.bucarPorId(idProduto.intValue()));
		return Pages.TELA_DETALHES_PRODUTO;
	}

}
