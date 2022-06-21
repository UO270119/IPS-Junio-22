package business.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import business.EnviosService;
import business.EstadosEnvioService;
import business.exception.BusinessException;
import db.EnviosGateway;
import db.EstadosEnvioGateway;
import db.PersistenceFactory;
import db.exception.PersistenceException;
import db.util.Jdbc;
import dto.EnvioDto;
import dto.EstadoEnvioDto;

public class EstadosEnvioServiceImpl implements EstadosEnvioService {

	@Override
	public void add(EstadoEnvioDto estado) throws BusinessException {
		try (Connection con = Jdbc.getConnection()) {
			EstadosEnvioGateway eg = PersistenceFactory.getEstadosEnvioGateway(con);
			eg.save(estado);
			System.out.println("Estado de envío: " + estado.id + " añadido correctamente");
		} catch (SQLException | PersistenceException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<EstadoEnvioDto> getForEnvio(Long idEnvio) throws BusinessException {
		List<EstadoEnvioDto> result = null;
		try (Connection con = Jdbc.getConnection()) {
			EstadosEnvioGateway eg = PersistenceFactory.getEstadosEnvioGateway(con);
			result = eg.findForEnvio(idEnvio);
		} catch (SQLException e) {
			throw new BusinessException("Problems occured creating a connection to the DB. Check everything is well connected.");
		} catch (PersistenceException e) {
			throw new BusinessException(e);
		}
		return result;
	}


	@Override
	public List<EstadoEnvioDto> getAll() throws BusinessException {
		List<EstadoEnvioDto> result = new LinkedList<EstadoEnvioDto>();
		try (Connection con = Jdbc.getConnection()) {
			result = PersistenceFactory.getEstadosEnvioGateway(con).listAll();
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int count() {
		int count = 0;
		try (Connection con = Jdbc.getConnection()) {
			EstadosEnvioGateway eg = PersistenceFactory.getEstadosEnvioGateway(con);
			count = eg.count();
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return count;
	}

}
