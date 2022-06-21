package db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.RutasGateway;
import db.exception.PersistenceException;
import db.util.Conf;
import dto.DtoFactory;
import dto.EnvioDto;
import dto.RutaDto;

public class RutasGatewayImpl implements RutasGateway {
		
	private static String SQL_SAVE_RUTA = Conf.getInstance().getProperty("SQL_SAVE_RUTA");
	private static String SQL_LIST_RUTAS = Conf.getInstance().getProperty("SQL_LIST_RUTAS");
	private static String SQL_COUNT_RUTAS = Conf.getInstance().getProperty("SQL_COUNT_RUTAS");
	private static String SQL_SELECT_ENVIOS_FOR_REPARTIDOR = Conf.getInstance().getProperty("SQL_SELECT_ENVIOS_FOR_REPARTIDOR");


	private Connection con;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}


	@Override
	public List<EnvioDto> findEnviosForRepartidor(Long idRepartidor) throws PersistenceException {
		List<EnvioDto> all = new LinkedList<EnvioDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_ENVIOS_FOR_REPARTIDOR)) {
			ps.setLong(1, idRepartidor);
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
	public List<RutaDto> listAll() throws PersistenceException {
		List<RutaDto> all = new LinkedList<RutaDto>();
		try (PreparedStatement ps = con.prepareStatement(SQL_LIST_RUTAS); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				all.add(DtoFactory.getRuta(rs));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
			throw new PersistenceException(sqle);
		}
		return all;
	}
	
	@Override
	public void save(RutaDto dto) throws PersistenceException {
		try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_RUTA)) {
			ps.setLong(1, dto.id);
			ps.setLong(2, dto.idEnvio);
			ps.setLong(3, dto.idRepartidor);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println(sqle);
			throw new PersistenceException(sqle);
		}
	}

	@Override
	public void delete(RutaDto dto) throws PersistenceException {
		// TODO?
	}

	@Override
	public int count() throws PersistenceException {
		int count = 0;
		try (PreparedStatement ps = con.prepareStatement(SQL_COUNT_RUTAS); ResultSet rs = ps.executeQuery()) {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException sqle) {
			throw new PersistenceException(sqle);
		}
		return count;
	}

}
