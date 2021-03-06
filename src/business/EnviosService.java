package business;

import java.util.List;

import business.exception.BusinessException;
import dto.EnvioDto;

public interface EnviosService {

	public EnvioDto findByCodigo(String codigoEnvio) throws BusinessException;

	public List<EnvioDto> findForRepartidor(Long idRepartidor) throws BusinessException;
	
	public void updateEstado(EnvioDto envio, String newEstado) throws BusinessException;

	public void add(EnvioDto envio) throws BusinessException;

	public int count();

	public void delete(EnvioDto idEnvio);

	public List<EnvioDto> getEnviosEnCentroDistribucion()  throws BusinessException;
	
	public List<EnvioDto> getAll() throws BusinessException;

	public double calculatePrice(String provinciaOrigen, String provinciaDestino, double peso, String recogida);

}
