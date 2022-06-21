package db;

import java.util.List;

import db.exception.PersistenceException;
import dto.EnvioDto;
import dto.EstadoEnvioDto;

public interface EstadosEnvioGateway extends Gateway<EstadoEnvioDto> {

	public List<EstadoEnvioDto> findForEnvio(Long idEnvio) throws PersistenceException;
	
}
