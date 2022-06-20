package db;

import db.exception.PersistenceException;
import dto.EnvioDto;

public interface EnviosGateway extends Gateway<EnvioDto> {

	public EnvioDto find(Long idEnvio) throws PersistenceException;
	
}
