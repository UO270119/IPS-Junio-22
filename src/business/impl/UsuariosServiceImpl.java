package business.impl;

import java.sql.Connection;
import java.sql.SQLException;

import business.UsuariosService;
import business.exception.BusinessException;
import db.PersistenceFactory;
import db.UsuariosGateway;
import db.exception.PersistenceException;
import db.util.Jdbc;
import dto.UsuarioDto;

public class UsuariosServiceImpl implements UsuariosService {

	@Override
	public void registrarUsuario(UsuarioDto usuario) throws BusinessException {
		try (Connection con = Jdbc.getConnection()) {
			UsuariosGateway ug = PersistenceFactory.getUsuariosGateway(con);
			ug.save(usuario);
			System.out.println("Usuario: " + usuario.id + " añadido correctamente");
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UsuarioDto login(String dni, String contrasena) throws BusinessException {
		UsuarioDto dto = null;
		try (Connection con = Jdbc.getConnection()) {
			UsuariosGateway ug = PersistenceFactory.getUsuariosGateway(con);
			dto = ug.findByDniContrasena(dni, contrasena);
			System.out.println("Usuario: " + dto.id + " logueado correctamente");
		} catch (SQLException | PersistenceException e) {
			//e.printStackTrace();
			throw new BusinessException(e);
		}
		return dto;
	}
	
	
	@Override
	public int count() {
		int count = 0;
		try (Connection con = Jdbc.getConnection()) {
			UsuariosGateway ug = PersistenceFactory.getUsuariosGateway(con);
			count = ug.count();
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return count;
	}

}
