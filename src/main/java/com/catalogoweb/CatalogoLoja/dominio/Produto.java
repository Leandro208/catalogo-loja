package com.catalogoweb.CatalogoLoja.dominio;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private String fabricante;
	
	@Column(nullable = false)
	private String categoria;
	
	@Column(nullable = false)
	private String precoVenda;
	
	@Column(nullable = false)
	private String precoCusto;
	
	@Column(nullable = false)
	private String descricao;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Arquivo foto;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return id == other.id;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Arquivo getFoto() {
		return foto;
	}

	public void setFoto(Arquivo foto) {
		this.foto = foto;
	}

	
	
	
}
