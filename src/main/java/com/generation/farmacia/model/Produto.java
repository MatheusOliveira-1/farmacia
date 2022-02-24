package com.generation.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório!")
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória!")
	private String descricao;
	
	private int quantidade;
	
	private String laboratorio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "Preço é obrigatório!")
	@Positive(message = "Digite um valor maior do que zero")
	private BigDecimal preco;
	
	private String foto;
	

}
