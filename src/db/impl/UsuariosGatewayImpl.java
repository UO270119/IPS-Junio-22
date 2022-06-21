package db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.UsuariosGateway;
import db.exception.PersistenceException;
import db.util.Conf;
import dto.DtoFactory;
import dto.EnvioDto;
import dto.UsuarioDto;

public class UsuariosGatewayImpl implements UsuariosGateway {
		
	private static String SQL_SAVE_USUARIO = Conf.getInstance().getProperty("SQL_SAVE_USUARIO");
	private static String SQL_COUNT_USUARIOS = Conf.getInstance().getProperty("SQL_COUNT_USUARIOS");
	private static String SQL_SELECT_USUARIO_TIPO = Conf.getInstance().getProperty("SQL_SELECT_USUARIO_TIPO");
	private static String SQL_SELECT_USUARIO_DNI = Conf.getInstance().getProperty("SQL_SELECT_USUARIO_DNI");
	private static String SQL_SELECT_USUARIO_DNI_CONTRASENA = Conf.getInstance().getProperty("SQL_SELECT_USUARIO_DNI_CONTRASENA");


	private Connection con;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}


	@Override
	public UsuarioDto findByDni(String dni) throws PersistenceException {
		ResultSet rs;
		UsuarioDto usuario;
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_USUARIO_DNI)) {
			ps.setString(1, dni);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				throw new PersistenceException("No existe usuario con dni: " + dni);
			}
			usuario = DtoFactory.getUsuario(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		return usuario;
	}


	@Override
	public UsuarioDto findByDniContrasena(String dni, String contrasena) throws PersistenceException {
		ResultSet rs;
		UsuarioDto usuario;
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_USUARIO_DNI_CONTRASENA)) {
			ps.setString(1, dni);
			ps.setString(2, contrasena);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				throw new PersistenceException("No existe usuario con dni: " + dni + " y contraseña " + contrasena);
			}
			usuario = DtoFactory.getUsuario(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		return usuario;
	}
	
	@Override
	public void save(UsuarioDto dto) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_USUARIO)) {

			//INSERT INTO Usuarios(id, nombre, apellido, dni, email, contrasena, tipo) values (?,?,?,?,?,?,?)
			ps.setLong(1, dto.id);
			ps.setString(2, dto.nombre);
			ps.setString(3, dto.apellido);
			ps.setString(4, dto.dni);
			ps.setString(5, dto.email);
			ps.setString(6, dto.contrasena);
			ps.setString(7, dto.tipo);
			
			ps.executeUpdate();
		} catch (SQLException sqle) {
			System.err.println(sqle);
			throw new PersistenceException(sqle);
		}
	}

	@Override
	public void delete(UsuarioDto dto) throws PersistenceException {
		//Not implemented
	}

	@Override
	public List<UsuarioDto> listAll() throws PersistenceException {
		// Not implemented
		return null;
	}

	@Override
	public int count() throws PersistenceException {
		int count = 0;
		try (PreparedStatement ps = con.prepareStatement(SQL_COUNT_USUARIOS); ResultSet rs = ps.executeQuery()) {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException sqle) {
			throw new PersistenceException(sqle);
		}
		return count;
	}


	@Override
	public List<UsuarioDto> findByTipo(String tipoRepartidor) throws PersistenceException {
		List<UsuarioDto> all = new LinkedList<UsuarioDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_USUARIO_TIPO)) {
			ps.setString(1, tipoRepartidor);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				all.add(DtoFactory.getUsuario(rs));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
			throw new PersistenceException(sqle);
		}
		return all;
	}

}
