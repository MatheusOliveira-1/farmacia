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

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
		if (categoriaRepository.existsAllByNomeContainingIgnoreCase(categoria.getNome())){
			return new ResponseEntity("A cateogoria '" + categoria.getNome() + "' já existe.", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(categoria));
	} 
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
		return categoriaRepository.findById(categoria.getId())
				.map(result -> ResponseEntity.status(HttpStatus.OK)
				.body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategoria(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Categoria> getByIdCategoria(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(result -> ResponseEntity.ok(result))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<Categoria>> getByNomeCategoria(@PathVariable String nome){
		return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		if (categoriaRepository.existsById(id)){
			return categoriaRepository.findById(id)
					.map(result -> {
						categoriaRepository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
					
		}
		return ResponseEntity.notFound().build();
	}

}
