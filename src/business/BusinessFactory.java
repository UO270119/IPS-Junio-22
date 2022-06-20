package business;

import business.impl.EnviosServiceImpl;
import business.impl.UsuariosServiceImpl;

public class BusinessFactory {

	public static EnviosService getEnviosService() {
		return new EnviosServiceImpl();
	}

	public static UsuariosService getUsuariosService() {
		return new UsuariosServiceImpl();
	}

}
