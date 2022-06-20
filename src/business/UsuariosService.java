package business;

import business.exception.BusinessException;
import dto.UsuarioDto;

public interface UsuariosService {
	
	public void registrarUsuario(UsuarioDto usuario) throws BusinessException;
	
	public UsuarioDto login(String dni, String contrasena) throws BusinessException;

	public int count();

}
