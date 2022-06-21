package business.impl;

import java.sql.Connection;
import java.sql.SQLException;

import business.RutasService;
import business.exception.BusinessException;
import db.PersistenceFactory;
import db.RutasGateway;
import db.exception.PersistenceException;
import db.util.Jdbc;
import dto.RutaDto;

public class RutasServiceImpl implements RutasService {

	@Override
	public void add(RutaDto ruta) throws BusinessException {
		try (Connection con = Jdbc.getConnection()) {
			RutasGateway rg = PersistenceFactory.getRutasGateway(con);
			rg.save(ruta);
			System.out.println("Ruta: " + ruta.id + " añadida correctamente");
		} catch (SQLException | PersistenceException e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public int count() {
		int count = 0;
		try (Connection con = Jdbc.getConnection()) {
			RutasGateway rg = PersistenceFactory.getRutasGateway(con);
			count = rg.count();
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void deleteForEnvio(Long idEnvio) throws BusinessException {
		try (Connection con = Jdbc.getConnection()) {
			RutasGateway rg = PersistenceFactory.getRutasGateway(con);
			rg.deleteForEnvio(idEnvio);
		} catch (SQLException | PersistenceException e) {
			throw new BusinessException(e);
		}
	}

}
