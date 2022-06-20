package db;

import db.exception.PersistenceException;
import dto.EnvioDto;

public interface EnviosGateway extends Gateway<EnvioDto> {

	public EnvioDto findByCodigo(String codigoEnvio) throws PersistenceException;
	
}
