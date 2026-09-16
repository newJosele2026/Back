package com.josele.crud.usuario;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	private final UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Usuario obtener(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
	}

	@Transactional
	public Usuario crear(Usuario usuario) {
		return repository.save(usuario);
	}

	@Transactional
	public Usuario actualizar(Long id, Usuario datos) {
		Usuario usuario = obtener(id);
		usuario.setNombre(datos.getNombre());
		return repository.save(usuario);
	}

	@Transactional
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

}