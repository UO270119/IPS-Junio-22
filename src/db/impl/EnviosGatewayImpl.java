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
	private static String SQL_SELECT_ENVIO = Conf.getInstance().getProperty("SQL_SELECT_ENVIO");


	private Connection con;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}


	@Override
	public EnvioDto find(Long idEnvio) throws PersistenceException {
		ResultSet rs;
		EnvioDto envio;
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_ENVIO)) {
			ps.setLong(1, idEnvio);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				throw new PersistenceException("No existe envío con ID: " + idEnvio);
			}
			envio = DtoFactory.getEnvio(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		return envio;
	}
	
	@Override
	public void save(EnvioDto dto) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_ENVIO)) {
			
			ps.setLong(1, dto.id);
			ps.setLong(2, dto.idEmisor);
			ps.setTimestamp(3, Timestamp.from(dto.fechaEmision.toInstant()));
			ps.setString(4, dto.estado);
			
			ps.executeUpdate();
		} catch (SQLException sqle) {
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
	public List<EnvioDto> listAll() throws PersistenceException {
		List<EnvioDto> all = new LinkedList<EnvioDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_LIST_ENVIOS); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				all.add(DtoFactory.getEnvio(rs));
			}
		} catch (SQLException sqle) {
			throw new PersistenceException(sqle);
		}
		return all;
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

}
