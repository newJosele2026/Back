package com.josele.crud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.josele.crud.model.Usuario;

@Repository
public class UsuarioRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UsuarioRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SELECT_SQL =
			"SELECT id, nombre, fecha_de_creacion FROM usuario";

	private static final class UsuarioRowMapper implements RowMapper<Usuario> {
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getLong("id"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setFechaDeCreacion(rs.getTimestamp("fecha_de_creacion"));
			return usuario;
		}
	}

	public List<Usuario> findAll() {
		return jdbcTemplate.query(SELECT_SQL + " ORDER BY id",
				new Object[0], new UsuarioRowMapper());
	}

	public Usuario findById(Long id) {
		List<Usuario> resultado = jdbcTemplate.query(SELECT_SQL + " WHERE id = ?",
				new Object[] { id }, new UsuarioRowMapper());
		if (resultado.isEmpty()) {
			return null;
		}
		return resultado.get(0);
	}

	public Long insert(Usuario usuario) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String nombre = usuario.getNombre();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO usuario (nombre) VALUES (?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nombre);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	public int update(Usuario usuario) {
		return jdbcTemplate.update("UPDATE usuario SET nombre = ? WHERE id = ?",
				usuario.getNombre(), usuario.getId());
	}

	public int delete(Long id) {
		return jdbcTemplate.update("DELETE FROM usuario WHERE id = ?", id);
	}

}