package business;

import business.exception.BusinessException;
import dto.RutaDto;

public interface RutasService {

	public void add(RutaDto ruta) throws BusinessException;
	
	public int count();

	public void deleteForEnvio(Long idEnvio) throws BusinessException;

}
