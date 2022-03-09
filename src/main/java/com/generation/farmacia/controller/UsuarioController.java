package com.generation.farmacia.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Usuario;
import com.generation.farmacia.model.UsuarioLogin;
import com.generation.farmacia.repository.UsuarioRepository;
import com.generation.farmacia.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAllUsuario() {

		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getByIdUsuario(@PathVariable Long id) {

		return usuarioRepository.findById(id).map(res -> ResponseEntity.ok(res))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario){
		
		return usuarioService.cadastrarUsuario(usuario)
				.map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}


	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> logarUsuario(@Valid @RequestBody Optional<UsuarioLogin> user){
		
		return usuarioService.autenticarUsuario(user)
				.map(res -> ResponseEntity.ok(res))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}	
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario){
		
		return usuarioService.atualizarUsuario(usuario)
				.map(res -> ResponseEntity.status(HttpStatus.OK).body(res))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

}
