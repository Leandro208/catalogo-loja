package com.catalogoweb.CatalogoLoja.enums;

public enum PagesEnum {

	TELA_BUSCA("buscaProduto"),
	TELA_CADASTRO("cadastroProduto"),
	TELA_INICIAL("index"),
	TELA_DETALHES_PRODUTO("produtoDetalhes");
	
	
	private final String valor;
	
	private PagesEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
