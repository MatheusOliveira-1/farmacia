package com.generation.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")	
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O atributo nome é obrigatório")
	@Size(max = 255, message = "A nome deve ter no máximo 255 caractéres")
	private String nome;
	
	@NotBlank (message = "O atributo email é obrigatório")
	@Size(max = 255, message = "O email deve ter no máximo 255 caractéres")
	private String usuario;
	
	@NotBlank (message = "O atributo senha é obrigatório")
	@Size(min = 8, message = "O senha deve ter no mínimo 8 caractéres")
	private String senha;
	
	private String foto;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE )
	@JsonIgnoreProperties("usuario")	
	private List<Produto> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
	
}
