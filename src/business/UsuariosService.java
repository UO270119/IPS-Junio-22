package business;

import java.util.List;

import business.exception.BusinessException;
import dto.UsuarioDto;

public interface UsuariosService {
	
	public void registrarUsuario(UsuarioDto usuario) throws BusinessException;
	
	public UsuarioDto login(String dni, String contrasena) throws BusinessException;

	public List<UsuarioDto> getAllRepartidores() throws BusinessException;
	
	public int count();

}
