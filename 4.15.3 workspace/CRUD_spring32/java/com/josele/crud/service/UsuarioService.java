package com.josele.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josele.crud.model.Usuario;
import com.josele.crud.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository repository;

	@Autowired
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Usuario obtener(Long id) {
		Usuario usuario = repository.findById(id);
		if (usuario == null) {
			throw new RuntimeException("Usuario no encontrado con id: " + id);
		}
		return usuario;
	}

	@Transactional
	public Usuario crear(Usuario usuario) {
		Long id = repository.insert(usuario);
		return repository.findById(id);
	}

	@Transactional
	public Usuario actualizar(Long id, Usuario datos) {
		Usuario usuario = obtener(id);
		usuario.setNombre(datos.getNombre());
		repository.update(usuario);
		return repository.findById(id);
	}

	@Transactional
	public void eliminar(Long id) {
		repository.delete(id);
	}

}