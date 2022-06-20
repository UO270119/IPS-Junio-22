package dto.util;

import business.BusinessFactory;
import business.EnviosService;
import business.UsuariosService;

public class PrimaryKeyGenerator {

	public static long newEnvioPK() {
		EnviosService es = BusinessFactory.getEnviosService();
		return es.count() + 1;
	}
	
	
	public static long newUsuarioPK() {
		UsuariosService us = BusinessFactory.getUsuariosService();
		return us.count() + 1;
	}
}
