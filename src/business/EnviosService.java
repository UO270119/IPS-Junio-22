package business;

import java.util.List;

import business.exception.BusinessException;
import dto.EnvioDto;

public interface EnviosService {

	public EnvioDto find(Long idEnvio) throws BusinessException;

	public void update(EnvioDto envio);

	public void add(EnvioDto envio) throws BusinessException;

	public int count();

	public void delete(EnvioDto idEnvio);

	public List<EnvioDto> getAll() throws BusinessException;
}
