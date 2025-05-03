package com.catalogoweb.CatalogoLoja.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.catalogoweb.CatalogoLoja.dominio.Arquivo;
import com.catalogoweb.CatalogoLoja.dominio.Produto;
import com.catalogoweb.CatalogoLoja.repository.ArquivoRepository;
import com.catalogoweb.CatalogoLoja.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ArquivoRepository arquivoRepository;
	
	public Produto bucarPorId(int id) {
		return produtoRepository.findById(id).get();
	}
	
	public List<Produto> buscarTodos(){
		return produtoRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void cadastrar(Produto produto, MultipartFile arquivo) {
		try {
			if (arquivo != null && !arquivo.isEmpty()) {
				String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
				Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(), arquivo.getBytes());
				arquivoRepository.save(arquivoBD);
				if (produto.getFoto() != null && produto.getFoto().getId() != null && produto.getFoto().getId() > 0) {
					arquivoRepository.delete(produto.getFoto());
				}
				produto.setFoto(arquivoBD);
			} else {
				produto.setFoto(null);
			}
			produtoRepository.save(produto);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buscar(ModelMap model, String nome, Boolean mostrarTodosDados) {		
		model.addAttribute("produtosEncontrados", produtoRepository.findByTitulo(nome.toUpperCase()));
		if(mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		} else {
			model.addAttribute("mostrarTodosDados", false);
		}
		if(nome != null ) {
		model.addAttribute("nomeBuscado", nome);
		} 
	}
	
	@Transactional
	public void removerPorId(int id) {
		produtoRepository.deleteById(id);
	}
}
