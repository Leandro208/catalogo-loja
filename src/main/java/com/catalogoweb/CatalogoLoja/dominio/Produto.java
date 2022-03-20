package com.catalogoweb.CatalogoLoja.dominio;

public class Produto {

	private int id;
	
	private String titulo;
	
	private String fabricante;
	
	private String categoria;
	
	private String precoVenda;
	
	private String precoCusto;
	
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(String precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(String precoCusto) {
		this.precoCusto = precoCusto;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
