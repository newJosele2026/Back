package com.josele.crud.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josele.crud.model.Usuario;
import com.josele.crud.service.UsuarioService;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService service;

	@Autowired
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Usuario> listar() {
		return service.listar();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Usuario obtener(@PathVariable Long id) {
		return service.obtener(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
		Usuario nuevo = service.crear(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/api/usuarios/" + nuevo.getId()));

		return new ResponseEntity<Usuario>(nuevo, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario actualizado = service.actualizar(id, usuario);
		return new ResponseEntity<Usuario>(actualizado, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}