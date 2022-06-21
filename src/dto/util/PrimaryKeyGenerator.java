package dto.util;

import business.BusinessFactory;
import business.EnviosService;
import business.EstadosEnvioService;
import business.RutasService;
import business.UsuariosService;

public class PrimaryKeyGenerator {

	public static long newEnvioPK() {
		EnviosService es = BusinessFactory.getEnviosService();
		return es.count() + 1;
	}

	public static long newEstadoEnvioPK() {
		EstadosEnvioService es = BusinessFactory.getEstadosEnvioService();
		return es.count() + 1;
	}
	
	public static long newUsuarioPK() {
		UsuariosService us = BusinessFactory.getUsuariosService();
		return us.count() + 1;
	}
	
	public static long newRutaPK() {
		RutasService rs = BusinessFactory.getRutasService();
		return rs.count() + 1;
	}
}
