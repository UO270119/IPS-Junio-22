package business.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import business.EnviosService;
import business.exception.BusinessException;
import db.EnviosGateway;
import db.PersistenceFactory;
import db.exception.PersistenceException;
import db.util.Jdbc;
import dto.EnvioDto;

public class EnviosServiceImpl implements EnviosService {

	@Override
	public void add(EnvioDto envio) throws BusinessException {
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.save(envio);
			System.out.println("Envío: " + envio.id + "añadido correctamente");
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EnvioDto find(Long idEnvio) throws BusinessException {
		EnvioDto envio = null;
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			envio = eg.find(idEnvio);
		} catch (SQLException e) {
			throw new BusinessException("Problems occured creating a connection to the DB. Check everything is well connected.");
		} catch (PersistenceException e) {
			throw new BusinessException(e);
		}
		return envio;
	}

	@Override
	public void update(EnvioDto envio) {
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.save(envio);
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(EnvioDto idEnvio) {
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.delete(idEnvio);
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EnvioDto> getAll() throws BusinessException {
		List<EnvioDto> result = new LinkedList<EnvioDto>();
		try (Connection con = Jdbc.getConnection()) {
			for (EnvioDto dto : PersistenceFactory.getEnviosGateway(con).listAll())
				result.add(dto);
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int count() {
		int count = 0;
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			count = eg.count();
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return count;
	}

}
