package db;

import java.util.List;

import db.exception.PersistenceException;
import dto.EnvioDto;

public interface EnviosGateway extends Gateway<EnvioDto> {

	public EnvioDto findByCodigo(String codigoEnvio) throws PersistenceException;

	public List<EnvioDto> listEnviosWithEstado(String estado) throws PersistenceException;
	
	public void updateEstado(EnvioDto envio) throws PersistenceException;
	
}
