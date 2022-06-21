package business;

import java.util.List;

import business.exception.BusinessException;
import dto.EstadoEnvioDto;

public interface EstadosEnvioService {
	
	public void add(EstadoEnvioDto envio) throws BusinessException;

	public int count();

	public List<EstadoEnvioDto> getAll() throws BusinessException;

	public List<EstadoEnvioDto> getForEnvio(Long idEnvio) throws BusinessException;
}
