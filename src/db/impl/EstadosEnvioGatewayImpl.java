package db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import db.EnviosGateway;
import db.EstadosEnvioGateway;
import db.exception.PersistenceException;
import db.util.Conf;
import dto.DtoFactory;
import dto.EnvioDto;
import dto.EstadoEnvioDto;

public class EstadosEnvioGatewayImpl implements EstadosEnvioGateway {
		
	private static String SQL_SAVE_ESTADO_ENVIO = Conf.getInstance().getProperty("SQL_SAVE_ESTADO_ENVIO");
	private static String SQL_DELETE_ESTADO_ENVIO = Conf.getInstance().getProperty("SQL_DELETE_ESTADO_ENVIO");
	private static String SQL_LIST_ESTADO_ENVIOS = Conf.getInstance().getProperty("SQL_LIST_ESTADO_ENVIOS");
	private static String SQL_COUNT_ESTADO_ENVIOS = Conf.getInstance().getProperty("SQL_COUNT_ESTADO_ENVIOS");
	private static String SQL_SELECT_ESTADO_ENVIO = Conf.getInstance().getProperty("SQL_SELECT_ESTADO_ENVIO");


	private Connection con;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}


	@Override
	public List<EstadoEnvioDto> findForEnvio(Long idEnvio) throws PersistenceException {
		List<EstadoEnvioDto> all = new LinkedList<EstadoEnvioDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_ESTADO_ENVIO)) {
			ps.setLong(1, idEnvio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				all.add(DtoFactory.getEstadoEnvio(rs));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
			throw new PersistenceException(sqle);
		}
		return all;
	}
	
	
	@Override
	public List<EstadoEnvioDto> listAll() throws PersistenceException {
		List<EstadoEnvioDto> all = new LinkedList<EstadoEnvioDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_LIST_ESTADO_ENVIOS); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				all.add(DtoFactory.getEstadoEnvio(rs));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
			throw new PersistenceException(sqle);
		}
		return all;
	}
	
	@Override
	public void save(EstadoEnvioDto dto) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_ESTADO_ENVIO)) {
			
			//id, id_envio, fecha, old_estado, new_estado
			ps.setLong(1, dto.id);
			ps.setLong(2, dto.idEnvio);
			ps.setTimestamp(3, Timestamp.from(dto.fecha.toInstant()));
			ps.setString(4, dto.oldEstado);
			ps.setString(5, dto.newEstado);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println(sqle);
			throw new PersistenceException(sqle);
		}
	}

	@Override
	public void delete(EstadoEnvioDto dto) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_DELETE_ESTADO_ENVIO)) {
			ps.setLong(1, dto.id);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new PersistenceException(sqle);
		}
	}

	@Override
	public int count() throws PersistenceException {
		int count = 0;
		try (PreparedStatement ps = con.prepareStatement(SQL_COUNT_ESTADO_ENVIOS); ResultSet rs = ps.executeQuery()) {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException sqle) {
			throw new PersistenceException(sqle);
		}
		return count;
	}

}
