package com.catalogoweb.CatalogoLoja.controller;




import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catalogoweb.CatalogoLoja.dominio.Arquivo;
import com.catalogoweb.CatalogoLoja.dominio.Produto;
import com.catalogoweb.CatalogoLoja.repository.ArquivoRepository;
import com.catalogoweb.CatalogoLoja.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class CadastroProduto {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
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
	@Transactional(readOnly = false)
	public String salvar (Produto produto, RedirectAttributes attr, 
			HttpSession sessao,
			@RequestParam("file") MultipartFile arquivo) {
		
		try{
			
			if(arquivo != null && !arquivo.isEmpty()) {
				//normalizando nome do arquivo
				String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
				
				Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(),
						arquivo.getBytes());
				
				arquivoRepository.save(arquivoBD);
				
				if(produto.getFoto() != null && produto.getFoto().getId() != null 
						&& produto.getFoto().getId() > 0) {
					
					arquivoRepository.delete(produto.getFoto());
					
				}
				
				produto.setFoto(arquivoBD);
			} else {
				produto.setFoto(null);
			}
			
	    //cadastro e edição
		produtoRepository.save(produto);
		
		attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/produtos/cadastrar";
		
	}
	
	
	@GetMapping("/editar/{id}")
	public String edicao(
			@PathVariable("id") Integer idProduto,
			ModelMap model,
			HttpSession sessao
			) {
		
		Produto produto = produtoRepository.findById(idProduto).get();
		
		
		model.addAttribute("produto", produto);
		
		
		return "cadastroProduto";
	}
}
