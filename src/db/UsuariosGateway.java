package db;

import db.exception.PersistenceException;
import dto.UsuarioDto;

public interface UsuariosGateway extends Gateway<UsuarioDto> {

	public UsuarioDto findByDni(String dni) throws PersistenceException;

	public UsuarioDto findByDniContrasena(String dni, String contrasena) throws PersistenceException;

}
