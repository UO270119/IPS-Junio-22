package db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import db.EnviosGateway;
import db.exception.PersistenceException;
import db.util.Conf;
import dto.DtoFactory;
import dto.EnvioDto;

public class EnviosGatewayImpl implements EnviosGateway {
		
	private static String SQL_SAVE_ENVIO = Conf.getInstance().getProperty("SQL_SAVE_ENVIO");
	private static String SQL_DELETE_ENVIO = Conf.getInstance().getProperty("SQL_DELETE_ENVIO");
	private static String SQL_LIST_ENVIOS = Conf.getInstance().getProperty("SQL_LIST_ENVIOS");
	private static String SQL_COUNT_ENVIOS = Conf.getInstance().getProperty("SQL_COUNT_ENVIOS");
	private static String SQL_SELECT_ENVIO_CODIGO = Conf.getInstance().getProperty("SQL_SELECT_ENVIO_CODIGO");
	private static String SQL_SELECT_ENVIO_ESTADO = Conf.getInstance().getProperty("SQL_SELECT_ENVIO_ESTADO");
	private static String SQL_UPDATE_ENVIO_ESTADO = Conf.getInstance().getProperty("SQL_UPDATE_ENVIO_ESTADO");

	private Connection con;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}


	@Override
	public EnvioDto findByCodigo(String codigoEnvio) throws PersistenceException {
		ResultSet rs;
		EnvioDto envio;
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_ENVIO_CODIGO)) {
			ps.setString(1, codigoEnvio);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				throw new PersistenceException("No existe envío con código: " + codigoEnvio);
			}
			envio = DtoFactory.getEnvio(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		return envio;
	}

	@Override
	public List<EnvioDto> listEnviosWithEstado(String estado) throws PersistenceException {
		List<EnvioDto> all = new LinkedList<EnvioDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_ENVIO_ESTADO)) {
			ps.setString(1, estado);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				all.add(DtoFactory.getEnvio(rs));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
			throw new PersistenceException(sqle);
		}
		return all;
	}
	
	
	@Override
	public List<EnvioDto> listAll() throws PersistenceException {
		List<EnvioDto> all = new LinkedList<EnvioDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_LIST_ENVIOS); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				all.add(DtoFactory.getEnvio(rs));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
			throw new PersistenceException(sqle);
		}
		return all;
	}
	
	@Override
	public void save(EnvioDto dto) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_ENVIO)) {
			
			//id, codigo, id_emisor, fecha_emision, estado, nombre_destinatario, apellidos_destinatario, direccion, peso, precio
			ps.setLong(1, dto.id);
			ps.setString(2, dto.codigo);
			ps.setLong(3, dto.idEmisor);
			ps.setTimestamp(4, Timestamp.from(dto.fechaEmision.toInstant()));
			ps.setString(5, dto.estado);
			ps.setString(6, dto.nombreDestinatario);
			ps.setString(7, dto.apellidoDestinatario);
			ps.setString(8, dto.direccion);
			ps.setDouble(9, dto.peso);
			ps.setDouble(10, dto.precio);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println(sqle);
			throw new PersistenceException(sqle);
		}
	}

	@Override
	public void delete(EnvioDto dto) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_DELETE_ENVIO)) {
			ps.setLong(1, dto.id);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new PersistenceException(sqle);
		}
	}

	@Override
	public int count() throws PersistenceException {
		int count = 0;
		try (PreparedStatement ps = con.prepareStatement(SQL_COUNT_ENVIOS); ResultSet rs = ps.executeQuery()) {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException sqle) {
			throw new PersistenceException(sqle);
		}
		return count;
	}


	@Override
	public void updateEstado(EnvioDto envio) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE_ENVIO_ESTADO)) {
			ps.setString(1, envio.estado);
			ps.setLong(2, envio.id);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new PersistenceException(sqle);
		}
	}

}
