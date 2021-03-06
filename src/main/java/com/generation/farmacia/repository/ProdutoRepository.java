package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {
	
	public List <Produto> findAllByNomeContainingIgnoreCase (String nome);
	public List <Produto> findByNomeContainingIgnoreCaseAndLaboratorioContainingIgnoreCase(String nome, String laboratorio);
	public List <Produto> findByPrecoBetweenOrderByPreco (BigDecimal inicio, BigDecimal fim);
	
}
