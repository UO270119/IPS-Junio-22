package db;

import java.util.List;

import business.exception.BusinessException;
import db.exception.PersistenceException;
import dto.EnvioDto;
import dto.RutaDto;

public interface RutasGateway extends Gateway<RutaDto> {

	public List<EnvioDto> findEnviosForRepartidor(Long idRepartidor) throws PersistenceException;

	public void deleteForEnvio(Long envioId) throws PersistenceException;
}
