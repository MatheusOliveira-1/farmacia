package com.generation.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired // P maíusculo = Interface, p minúsculo = Objeto da Interface.
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {	
		if (categoriaRepository.existsById(produto.getCategoria().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(produtoRepository.save(produto));				
		} 
			return ResponseEntity.badRequest().build();
		
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto){
			if(produtoRepository.existsById(produto.getId())){
				return produtoRepository.findById(produto.getCategoria().getId())			
						.map(result -> ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produto)))
						.orElse(ResponseEntity.notFound().build());
			
		} 
			return ResponseEntity.badRequest().build();
		
	}

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProduto() {
		return ResponseEntity.ok(produtoRepository.findAll());

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Produto> getByIdProduto(@PathVariable Long id) {
		return produtoRepository.findById(id)
			   .map(result -> ResponseEntity.ok(result))
			   .orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNomeProduto(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
				
	}
	
	@GetMapping("/nome-lab/{nome}-{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeAndLaboratorioProduto(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findByNomeContainingIgnoreCaseAndLaboratorioContainingIgnoreCase(nome, laboratorio));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id){
		if (produtoRepository.existsById(id)) {
			return produtoRepository.findById(id)
					.map(result -> {
						produtoRepository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
		}
		
		return ResponseEntity.notFound().build();
	}	

}
