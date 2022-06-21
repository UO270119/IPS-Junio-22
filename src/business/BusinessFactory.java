package business;

import business.impl.EnviosServiceImpl;
import business.impl.EstadosEnvioServiceImpl;
import business.impl.RutasServiceImpl;
import business.impl.UsuariosServiceImpl;

public class BusinessFactory {

	public static EnviosService getEnviosService() {
		return new EnviosServiceImpl();
	}

	public static EstadosEnvioService getEstadosEnvioService() {
		return new EstadosEnvioServiceImpl();
	}

	public static UsuariosService getUsuariosService() {
		return new UsuariosServiceImpl();
	}

	public static RutasService getRutasService() {
		return new RutasServiceImpl();
	}

}
