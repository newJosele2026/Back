package com.josele.crud.usuario;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService service;

	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping
	public List<Usuario> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Usuario obtener(@PathVariable Long id) {
		return service.obtener(id);
	}

	@PostMapping
	public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
		Usuario nuevo = service.crear(usuario);
		return ResponseEntity.created(URI.create("/api/usuarios/" + nuevo.getId())).body(nuevo);
	}

	@PutMapping("/{id}")
	public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		return service.actualizar(id, usuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}